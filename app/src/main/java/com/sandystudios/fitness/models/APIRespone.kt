package com.sandystudios.fitness.models

import com.google.gson.annotations.SerializedName

data class APIResponse(

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

