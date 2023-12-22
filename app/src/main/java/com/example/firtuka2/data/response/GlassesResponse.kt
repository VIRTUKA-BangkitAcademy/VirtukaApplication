package com.example.firtuka2.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class GlassesResponse(

	@field:SerializedName("frames")
	val frames: List<FramesItem> = listOf(),

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

@Entity(tableName = "glasses")
data class FramesItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("face")
	val face: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@PrimaryKey
	@field:SerializedName("id")
	val id: String
)
