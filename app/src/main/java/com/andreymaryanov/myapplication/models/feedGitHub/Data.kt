package com.andreymaryanov.myapplication.models.feedGitHub

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("organization")
    @Expose
    var organization: Organization? = null
}