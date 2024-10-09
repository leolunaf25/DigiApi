package com.lunatcoms.digiapi

import com.google.gson.annotations.SerializedName


data class DigiResponse(@SerializedName("content") val content: List<Digimonster>)
data class Digimonster(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
    @SerializedName("image") val image: String
)


object ApiContants {
    const val BASE_URL = "https://digi-api.com/api/v1/"
}