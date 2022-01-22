package com.pravin.fcmandroid.retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface FcmTriggerApiService {
    companion object{
        val SERVER_KEY = "key=_______________________________"
        private val BASE_URL = "https://fcm.googleapis.com/"
        private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        private val fcmTriggerApiService:FcmTriggerApiService = retrofit.create(FcmTriggerApiService::class.java)
        fun getInstance():FcmTriggerApiService = fcmTriggerApiService
    }

    @POST("fcm/send")
    fun createFCMNotification(@Header("Authorization") authorization:String,
                              @Body fcmNotification: FCMNotification): Call<Any?>
}