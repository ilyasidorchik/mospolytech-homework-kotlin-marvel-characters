package com.example.marvelcharacters.net

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("code")
    var code: String,
    @SerializedName("data")
    var data: CharacterData
)

data class CharacterData(
    @SerializedName("count")
    var count: String,
    @SerializedName("results")
    var results: ArrayList<CharacterResult>
)

data class CharacterResult(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("thumbnail")
    var thumbnail: Thumbnail
)

data class Thumbnail(
    @SerializedName("path")
    var path: String,
    @SerializedName("extension")
    var extension: String
)