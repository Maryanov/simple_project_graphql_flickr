package com.andreymaryanov.myapplication.models.feedGitHub

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Organization {
    @SerializedName("members")
    @Expose
    var members: Members? = null
}