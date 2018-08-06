package com.andreymaryanov.myapplication.activities

import android.support.v7.app.AppCompatActivity
import com.andreymaryanov.myapplication.MainApplication
import com.andreymaryanov.myapplication.contracts.IViewModelResolver

open class BaseActivity : AppCompatActivity() {
    protected val viewModelResolver : IViewModelResolver
        get() = (application as MainApplication).bootstrapper.viewModelResolver
}