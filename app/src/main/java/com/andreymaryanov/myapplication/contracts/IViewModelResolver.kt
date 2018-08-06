package com.andreymaryanov.myapplication.contracts

import com.andreymaryanov.myapplication.viewModels.MainViewModel

interface IViewModelResolver {
    fun resolveMainViewModel(): MainViewModel
}