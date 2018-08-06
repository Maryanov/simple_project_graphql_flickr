package com.andreymaryanov.myapplication.infrastructure

import com.andreymaryanov.myapplication.contracts.IViewModelResolver
import com.andreymaryanov.myapplication.models.PlatformValues

class Bootstrapper(platformValues: PlatformValues) {
    private var container : Container = Container(platformValues)

    val viewModelResolver : IViewModelResolver
        get() = container
}
