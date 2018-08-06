package com.andreymaryanov.myapplication.common

object RgaphQLConst {
    const val GITHUB_BASE_URL = "https://api.github.com/"
    const val TOKEN = "539308e0fef76c82b95d4b0938cd4450bcfb57db"
    private const val login = "google"
    private const val count = 50
    val query = "query {" +
                    "organization(login: \"" + login + "\") {" +
                        "members(first: "+ count.toString() + ") {" +
                            "edges {" +
                                "node {" +
                                    "name " +
                                    "avatarUrl " +
                                    "url" +
                                "}" +
                            "}" +
                        "}" +
                    "}" +
                "}"

}