package com.example.marvelcharacters.list

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterItem(@SerializedName("characterImage") var characterImage: String = "",
                         @SerializedName("characterImageLarge") var characterImageLarge: String = "",
                         @SerializedName("characterName") var characterName: String = "",
                         @SerializedName("characterDesc") var characterDesc: String = "") : Serializable {

}