package com.tsdreamdeveloper.app.utils

interface Mapper<SRC, DST> {
    fun transform(data: SRC): DST
    fun transform(data: List<SRC>): List<DST>
}