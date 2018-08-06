package com.andreymaryanov.myapplication.common

object FlickrConst {
    const val FLICKR_BASE_URL = "https://api.flickr.com/services/"
    const val query = "rest/?method=flickr.photos.getRecent&api_key=172be663a1b298ebc558f95a2c46f077&per_page=50&page=1&format=json&nojsoncallback=1&extras=url_m,url_t,url_o"
}