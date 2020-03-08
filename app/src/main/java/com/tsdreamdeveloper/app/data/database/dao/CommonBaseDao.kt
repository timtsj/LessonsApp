package com.tsdreamdeveloper.app.data.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import java.lang.reflect.ParameterizedType

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(data: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(data: List<T>)
}

abstract class CommonBaseDao<T : Any> : BaseDao<T> {

    open val primaryKeyName: String = "id"

    private val className by lazy {
        var genericSuperClass = javaClass.genericSuperclass

        var parametrizedType: ParameterizedType? = null
        while (parametrizedType == null) {
            if (genericSuperClass is ParameterizedType) {
                parametrizedType = genericSuperClass as ParameterizedType
            } else {
                genericSuperClass = (genericSuperClass as Class<*>).genericSuperclass
            }
        }

        (parametrizedType!!.actualTypeArguments[0] as Class<T>).simpleName
    }

    fun getAll(): List<T> {
        return getRawQuery(SimpleSQLiteQuery("SELECT * FROM $className"))
    }

    fun getById(idValue: Any): T? {
        return getRawQuery(
            SimpleSQLiteQuery(
                "SELECT * FROM $className WHERE $primaryKeyName = ?",
                arrayOf(idValue)
            )
        )
            .firstOrNull()
    }

    fun deleteAll() {
        rawQuery(SimpleSQLiteQuery("DELETE FROM $className"))
    }

    fun deleteById(idValue: Any) {
        rawQuery(
            SimpleSQLiteQuery(
                "DELETE FROM $className WHERE $primaryKeyName = ?",
                arrayOf(idValue)
            )
        )
    }

    @RawQuery
    protected abstract fun rawQuery(query: SupportSQLiteQuery): Int

    @RawQuery
    protected abstract fun getRawQuery(query: SupportSQLiteQuery): List<T>
}