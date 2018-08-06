package com.andreymaryanov.myapplication.models.feedGitHub

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Node {
    @SerializedName("name")
    @Expose
    var name: String? = ""
    @SerializedName("avatarUrl")
    @Expose
    var avatarUrl: String? = ""
    @SerializedName("url")
    @Expose
    var url: String? = ""
}