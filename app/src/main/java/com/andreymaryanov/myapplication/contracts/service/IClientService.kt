package com.andreymaryanov.myapplication.contracts.service

import com.andreymaryanov.myapplication.models.RequesrServerResult

interface IClientService {
    fun getData(clientGitHub: IWebInterface, clientFlickr: IWebInterface, onComplete : (RequesrServerResult) -> Unit) : RequesrServerResult
}