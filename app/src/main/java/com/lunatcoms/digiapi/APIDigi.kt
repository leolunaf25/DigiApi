package com.lunatcoms.digiapi

import retrofit2.Response
import retrofit2.http.GET

interface APIDigi {
    @GET("digimon?name=metal&pageSize=2000")
    suspend fun getDigimon(): Response<DigiResponse>
}