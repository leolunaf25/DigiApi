package com.lunatcoms.digiapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIDigi {
    @GET("digimon?pageSize=100")
    suspend fun getDigimon(): Response<DigiResponse>

    @GET("digimon/{id}")
    suspend fun getDataDigimon(@Path("id") id: String): Response<DigimonsterDataResponse>
}