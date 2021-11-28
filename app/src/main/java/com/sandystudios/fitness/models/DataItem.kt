package com.sandystudios.fitness.models

import com.google.gson.annotations.SerializedName

data class DataItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("min_calories")
    val minCalories: Int? = null,

    @field:SerializedName("difficulty_level")
    val difficultyLevel: Int? = null,

    @field:SerializedName("category_name")
    val categoryName: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("video_uploadedAt")
    val videoUploadedAt: Any? = null,

    @field:SerializedName("max_calories")
    val maxCalories: Int? = null,

    @field:SerializedName("video")
    val video: String? = null,

    @field:SerializedName("duration")
    val duration: Int? = null,

    @field:SerializedName("difficulty_level_name")
    val difficultyLevelName: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: Int? = null,

    @field:SerializedName("equipments")
    val equipments: List<Any?>? = null,

    @field:SerializedName("workout_plans")
    val workoutPlans: List<WorkoutPlansItem?>? = null,

    @field:SerializedName("category_id")
    val categoryId: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("trainer_name")
    val trainerName: String? = null,

    @field:SerializedName("trainer_id")
    val trainerId: Int? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: Any? = null
)