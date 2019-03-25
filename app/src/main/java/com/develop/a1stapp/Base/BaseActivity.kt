package com.develop.a1stapp.Base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.develop.a1stapp.Resource.LoadingView

open class BaseActivity: AppCompatActivity() {

    lateinit var loadingView: LoadingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingView = LoadingView(this)
    }

    fun showLoadingView() {
        loadingView.show()
    }

    fun dismissLoadingView() {
        loadingView.close()
    }
}