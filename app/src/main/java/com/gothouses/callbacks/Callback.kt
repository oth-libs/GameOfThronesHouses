package com.gothouses.callbacks

abstract class Callback<T> {
    abstract fun returnResult(t: T)

    // e.g. timeout
    abstract fun returnError()
}