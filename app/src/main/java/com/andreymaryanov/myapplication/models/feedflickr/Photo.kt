package com.andreymaryanov.myapplication.models.feedflickr

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photo {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("owner")
    @Expose
    var owner: String? = null
    @SerializedName("secret")
    @Expose
    var secret: String? = null
    @SerializedName("server")
    @Expose
    var server: String? = null
    @SerializedName("farm")
    @Expose
    var farm: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("ispublic")
    @Expose
    var ispublic: Int? = null
    @SerializedName("isfriend")
    @Expose
    var isfriend: Int? = null
    @SerializedName("isfamily")
    @Expose
    var isfamily: Int? = null
    @SerializedName("url_m")
    @Expose
    var url_m: String? = null
    @SerializedName("height_m")
    @Expose
    var height_m: String? = null
    @SerializedName("width_m")
    @Expose
    var width_m: String? = null
    @SerializedName("url_t")
    @Expose
    var url_t: String? = null
    @SerializedName("height_t")
    @Expose
    var height_t: String? = null
    @SerializedName("width_t")
    @Expose
    var width_t: String? = null
    @SerializedName("url_o")
    @Expose
    var url_o: String? = null
    @SerializedName("height_o")
    @Expose
    var height_o: String? = null
    @SerializedName("width_o")
    @Expose
    var width_o: String? = null
}


