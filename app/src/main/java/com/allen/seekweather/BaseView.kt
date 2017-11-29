package com.allen.seekweather

/**
* Created by Allen on 2016/12/13.
*/

interface BaseView<T> {
    fun setPresenter(presenter: T)

    fun showSnackBar(message: String)
}
