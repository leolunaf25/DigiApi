package com.lunatcoms.digiapi

import com.google.gson.annotations.SerializedName


data class DigiResponse(@SerializedName("content") val content: List<Digimonster>)
data class Digimonster(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
    @SerializedName("image") val image: String
)

data class DigimonsterDataResponse(
    @SerializedName("name") val name: String,
    @SerializedName("xAntibody") val genX: String,
    @SerializedName("images") val images: List<Images>,
    @SerializedName("descriptions") val descriptions: List<Description>,
    @SerializedName("fields") val fields: List<Fields>
)

data class Images(@SerializedName("href") val urlImage: String)
data class Description(@SerializedName("description") val description: String)
data class Fields(@SerializedName("image") val imageField:String)

object ApiContants {
    const val BASE_URL = "https://digi-api.com/api/v1/"
    const val ID_DIGITAL = "ID_DIGITAL"
    const val ERROR = "Error"
}