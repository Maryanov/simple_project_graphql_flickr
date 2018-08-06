package com.andreymaryanov.myapplication.contracts.service

interface ICommand<T> {
    fun execute()
    fun execute(parameter: T)
}