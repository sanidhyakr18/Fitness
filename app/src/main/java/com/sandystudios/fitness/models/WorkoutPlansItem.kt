package com.sandystudios.fitness.models

import com.google.gson.annotations.SerializedName

data class WorkoutPlansItem(

    @field:SerializedName("reps")
    val reps: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)

