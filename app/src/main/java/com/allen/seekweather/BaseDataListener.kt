package com.allen.seekweather

/**
 * Created by Allen on 2016/12/13.
 */

interface BaseDataListener<in T> {
    fun onSucceed(callbackData: T)

    fun onError(throwable: Throwable)
}
