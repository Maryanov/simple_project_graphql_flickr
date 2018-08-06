package com.andreymaryanov.myapplication.contracts

import com.andreymaryanov.myapplication.contracts.service.ICommand

class RelayCommand<T>
(private val executeFunc : (parameter : T?) -> Unit, private val canExecute : () -> Boolean = {true}) : ICommand<T> {

    override fun execute() {
        if (canExecute())
            executeFunc(null)
    }

    override fun execute(parameter: T) {
        if (canExecute())
            executeFunc(parameter)
    }
}