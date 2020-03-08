package com.tsdreamdeveloper.app.utils

import android.system.ErrnoException
import com.tsdreamdeveloper.app.R
import com.tsdreamdeveloper.app.presentation.base.CanHandleError
import com.tsdreamdeveloper.app.utils.delegate.ResourceDelegate
import com.tsdreamdeveloper.app.utils.delegate.SnackbarType
import io.reactivex.exceptions.CompositeException
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.internal.http2.StreamResetException
import retrofit2.HttpException
import timber.log.Timber
import java.lang.ref.WeakReference
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

class ErrorHandler @Inject constructor(
        private val codeParser: HttpErrorCodeParser,
        private val resourceRepository: ResourceDelegate
) {

    private val errorActionMap = mutableMapOf<Class<out Throwable>, (Throwable) -> Unit>()

    private var errorView: WeakReference<CanHandleError>? = null

    fun attachView(view: CanHandleError) {
        errorView = WeakReference(view)
    }

    fun handleError(throwable: Throwable?) {
        when (throwable) {
            is CompositeException -> {
                throwable.exceptions.forEach { handleError(it) }
                return
            }
            else -> {
                throwable?.let {
                    val action = errorActionMap[it.javaClass]
                    if (action != null) {
                        action.invoke(it)
                    } else {
                        errorView?.get()?.showMessage(resourceRepository.getString(R.string.error_unknown), SnackbarType.ERROR)
                    }
                    it.printStackTrace()
                }
            }
        }
    }

    init {
        RxJavaPlugins.setErrorHandler {
            Timber.d("uncaughtException: ${it.localizedMessage}")
        }
    }

    private val unknownHostErrorHandler: (Throwable) -> Unit = {
        errorView?.get()?.showMessage(resourceRepository.getString(R.string.error_no_internet), SnackbarType.ERROR)
    }

    private val socketTimeoutErrorHandler: (Throwable) -> Unit = {
        errorView?.get()?.showMessage(resourceRepository.getString(R.string.error_unable_load_data), SnackbarType.ERROR)
    }

    private val httpErrorHandler: (Throwable) -> Unit = {
        if ((it as HttpException).code() == 500) {
            errorView?.get()?.showMessage(resourceRepository.getString(R.string.error_service_not_available), SnackbarType.ERROR)
        } else {
            parseHttpCodeJson(it)?.let { str ->
                errorView?.get()?.showMessage(str, SnackbarType.ERROR)
            } ?: run {
                errorView?.get()?.showMessage(resourceRepository.getString(R.string.error_unknown), SnackbarType.ERROR)
            }
        }
    }

    init {
        addError(HttpException::class.java, httpErrorHandler)
        addError(ConnectException::class.java, unknownHostErrorHandler)
        addError(ErrnoException::class.java, unknownHostErrorHandler)
        addError(retrofit2.adapter.rxjava2.HttpException::class.java, httpErrorHandler)
        addError(SocketTimeoutException::class.java, socketTimeoutErrorHandler)
        addError(UnknownHostException::class.java, unknownHostErrorHandler)
        addError(ConnectException::class.java, unknownHostErrorHandler)
        addError(StreamResetException::class.java, unknownHostErrorHandler)
        addError(SSLHandshakeException::class.java, unknownHostErrorHandler)
        addError(SSLException::class.java, unknownHostErrorHandler)
    }

    fun addError(clazz: Class<out Throwable>, handleAction: (Throwable) -> Unit) {
        errorActionMap[clazz] = handleAction
    }

    private fun parseHttpCodeJson(httpException: HttpException): String? {
        return httpException.response().errorBody()
                ?.use {
                    return codeParser.parseCode(it)
                }
    }
}
