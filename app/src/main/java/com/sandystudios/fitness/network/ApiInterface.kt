package com.sandystudios.fitness.network

import com.sandystudios.fitness.models.APIResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    //    @GET("all")
    @GET("all?category_id=14")
    suspend fun getCardio(): Response<APIResponse>
}