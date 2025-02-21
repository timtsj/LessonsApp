package com.tsdreamdeveloper.app.presentation.base.adapter.regular

import android.util.SparseArray
import com.tsdreamdeveloper.app.presentation.base.adapter.base.BaseCompositeDelegateAdapter
import com.tsdreamdeveloper.app.presentation.base.adapter.base.IDelegateAdapter
import com.tsdreamdeveloper.app.presentation.base.adapter.model.IAdapterItem

open class CompositeAdapter(
    typeToAdapterMap: SparseArray<IDelegateAdapter<*, *>>
) : BaseCompositeDelegateAdapter<IAdapterItem>(typeToAdapterMap) {

    class Builder {

        private var count: Int = 0
        private val typeToAdapterMap: SparseArray<IDelegateAdapter<*, *>> = SparseArray()

        fun add(delegateAdapter: IDelegateAdapter<*, out IAdapterItem>): Builder {
            typeToAdapterMap.put(count++, delegateAdapter)
            return this
        }

        fun build(): CompositeAdapter {
            require(count != 0) { "Register at least one adapter" }
            return CompositeAdapter(typeToAdapterMap)
        }
    }
}