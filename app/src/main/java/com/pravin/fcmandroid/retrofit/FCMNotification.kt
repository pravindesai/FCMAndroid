package com.pravin.fcmandroid.retrofit

import com.google.gson.annotations.SerializedName

data class FCMNotification(

	@field:SerializedName("notification")
	val notification: Notification? = null,

	@field:SerializedName("data")
	val data: List<String?>? = null,

	@field:SerializedName("to")
	val to: String? = null
)

data class Notification(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("color")
	val color: String? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null
)

