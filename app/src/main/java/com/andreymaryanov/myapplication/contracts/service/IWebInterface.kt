package com.andreymaryanov.myapplication.contracts.service

import com.andreymaryanov.myapplication.common.FlickrConst
import com.andreymaryanov.myapplication.common.RgaphQLConst.TOKEN
import com.andreymaryanov.myapplication.models.feedGitHub.*
import com.andreymaryanov.myapplication.models.feedflickr.ResultFlickr
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.concurrent.Executors
import okhttp3.OkHttpClient




interface IWebInterface {

    @Headers("Authorization:Bearer $TOKEN")
    @POST("graphql")
    fun getInfo(@Body jsonQuery: Query): Observable<ResultGitHub>

    @GET(FlickrConst.query)
    fun getPhotos(@retrofit2.http.Query("text") searchString: String): Observable<ResultFlickr>

    companion object Factory {

        fun create(url: String): IWebInterface {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(url)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .callbackExecutor(Executors.newSingleThreadExecutor())
                    .client(OkHttpClient.Builder().build())
                    .build()

            return retrofit.create(IWebInterface::class.java)

        }
    }
}

