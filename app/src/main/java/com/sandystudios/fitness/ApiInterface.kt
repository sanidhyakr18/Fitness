package com.sandystudios.fitness

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    //    @GET("all?category_id=14")
    @GET("all")
    suspend fun getCardio(): Response<APIResponse>
}