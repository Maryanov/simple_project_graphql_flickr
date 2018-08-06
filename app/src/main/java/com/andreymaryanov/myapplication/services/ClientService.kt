package com.andreymaryanov.myapplication.services

import android.os.AsyncTask
import com.andreymaryanov.myapplication.common.AppConst.TYPE_ITEM_FLICK
import com.andreymaryanov.myapplication.common.AppConst.TYPE_ITEM_GITHUB
import com.andreymaryanov.myapplication.common.FlickrConst
import com.andreymaryanov.myapplication.common.RgaphQLConst
import com.andreymaryanov.myapplication.contracts.service.IWebInterface
import com.andreymaryanov.myapplication.contracts.service.IClientService
import com.andreymaryanov.myapplication.models.ListFeed
import com.andreymaryanov.myapplication.models.RequesrServerResult
import com.andreymaryanov.myapplication.models.feedGitHub.Edges
import com.andreymaryanov.myapplication.models.feedGitHub.Query
import com.andreymaryanov.myapplication.models.feedflickr.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ClientService : IClientService {

    override fun getData(clientGitHub: IWebInterface, clientFlickr: IWebInterface, onComplete : (RequesrServerResult) -> Unit) : RequesrServerResult {
        GetDataApi(clientGitHub, clientFlickr, onComplete).execute()
        val repoItems : ArrayList<ListFeed> = arrayListOf()
        return RequesrServerResult(false, repoItems)
    }

    private class GetDataApi(var clientGitHub: IWebInterface, var clientFlickr: IWebInterface, val onComplete :
    (RequesrServerResult) -> Unit) : AsyncTask<Void, Void, RequesrServerResult>() {

        override fun doInBackground(vararg params: Void?): RequesrServerResult? {
            val queryBody = Query()
            var result : RequesrServerResult? = null
            var listGitHubResult : List<Edges>? = null
            var listFlickrResult : List<Photo>? = null

            try {

                queryBody.query = RgaphQLConst.query

                 clientGitHub.getInfo(queryBody)
                         .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ result ->
                            if (result==null) listGitHubResult = listOf()
                            listGitHubResult = result!!.data!!.organization!!.members!!.edges!!
                        }, { error->
                            val exception = error.message
                            listGitHubResult = listOf()
                        })

                while (listGitHubResult == null)  {}

                clientFlickr.getPhotos(FlickrConst.query)

                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ result ->
                            if (result==null) listFlickrResult = listOf()
                            listFlickrResult = result!!.photos!!.photo!!
                        }, {error ->
                            val exception = error.message
                            listFlickrResult = listOf()
                        })

                while (listFlickrResult == null)  {}
                result = RequesrServerResult( true, mappingMainList(listGitHubResult!!, listFlickrResult!!))

            } catch (e: Exception) {
                result = RequesrServerResult( true, arrayListOf(), e)
            }
            return result
        }

        private fun mappingMainList(listGitHub: List<Edges>, listFlickr: List<Photo>): ArrayList<ListFeed> {
            val listFeed : ArrayList<ListFeed> = arrayListOf()

            try {
                for (i in 0..49) {
                    try {
                        listFeed.add(ListFeed(TYPE_ITEM_GITHUB, listGitHub[i].node!!.name!!, listGitHub[i].node!!.avatarUrl!!, listGitHub[i].node!!.url!!))
                    } catch (e: Exception){
                        listFeed.add(ListFeed(TYPE_ITEM_GITHUB, "no name", "", ""))
                    }
                    try {
                        listFeed.add(ListFeed(TYPE_ITEM_FLICK, listFlickr[i].title!!, listFlickr[i].url_m!!, listFlickr[i].url_m!!))
                    } catch (e: Exception){
                        listFeed.add(ListFeed(TYPE_ITEM_FLICK, "no name", "", ""))
                    }
                }
            } catch (e: Exception){
                return listFeed
            }
            return listFeed
        }

        override fun onPostExecute(result: RequesrServerResult) {
            onComplete(result)
        }
    }

}