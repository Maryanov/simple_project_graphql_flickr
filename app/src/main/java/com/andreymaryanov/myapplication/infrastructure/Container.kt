package com.andreymaryanov.myapplication.infrastructure

import com.andreymaryanov.myapplication.contracts.IViewModelResolver
import com.andreymaryanov.myapplication.contracts.service.IClientService
import com.andreymaryanov.myapplication.models.PlatformValues
import com.andreymaryanov.myapplication.services.*
import com.andreymaryanov.myapplication.viewModels.MainViewModel

class Container(private val platformValues: PlatformValues) : IViewModelResolver {

    private val clientService: IClientService by lazy { ClientService() }

    override fun resolveMainViewModel() = MainViewModel(clientService)

}