package com.andreymaryanov.myapplication.models.feedflickr

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResultFlickr {
    @SerializedName("photos")
    @Expose
    var photos: Photos? = null
    @SerializedName("stat")
    @Expose
    var stat: String? = null
}