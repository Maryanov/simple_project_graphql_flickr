package com.andreymaryanov.myapplication.models.feedGitHub

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Members {
    @SerializedName("edges")
    @Expose
    var edges: List<Edges>? = null
}