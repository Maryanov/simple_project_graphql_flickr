package com.andreymaryanov.myapplication.models.feedGitHub

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResultGitHub {
    @SerializedName("data")
    @Expose
    var data: Data? = null
}