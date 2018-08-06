package com.andreymaryanov.myapplication.models.feedGitHub

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Edges {
    @SerializedName("node")
    @Expose
    var node: Node? = null

}