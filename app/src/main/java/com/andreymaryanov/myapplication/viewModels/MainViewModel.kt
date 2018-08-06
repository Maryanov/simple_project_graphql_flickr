package com.andreymaryanov.myapplication.viewModels

import com.andreymaryanov.myapplication.common.FlickrConst.FLICKR_BASE_URL
import com.andreymaryanov.myapplication.common.RgaphQLConst.GITHUB_BASE_URL
import com.andreymaryanov.myapplication.contracts.RelayCommand
import com.andreymaryanov.myapplication.contracts.service.IClientService
import com.andreymaryanov.myapplication.contracts.service.ICommand
import com.andreymaryanov.myapplication.contracts.service.IWebInterface
import com.andreymaryanov.myapplication.models.*

class MainViewModel (private val clientService: IClientService) {

    private val clientGitHub = IWebInterface.create(GITHUB_BASE_URL)
    private val clientFlickr = IWebInterface.create(FLICKR_BASE_URL)


    val goToGetFeedCommand: ICommand<Any> = RelayCommand<Any>({

        clientService.getData(clientGitHub, clientFlickr) { result ->
            if (result.isSuccessful){
                receiveLoadDataCommand.execute(result.data)
            }
            else {
                showMessageResultCommand.execute("Ошибка! Проблема с сервером!")
            }
        }
    })

    lateinit var receiveLoadDataCommand: ICommand<ArrayList<ListFeed>>
    lateinit var showMessageResultCommand: ICommand<String>
}