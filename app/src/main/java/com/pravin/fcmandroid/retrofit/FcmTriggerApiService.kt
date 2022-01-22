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
        val SERVER_KEY = "key=AAAAyPUsUKo:APA91bEOD6UTa4_LiALbiwWm8P8WMeLA5abxVehtwU-zzBMihOZOEa0t_rMcTRk3pYXBLNan4TSo8Gv_pSY4v8yG_bTUanX1D1qk92E2Oy4bGa-50E79IvmqXT4cfWBIEwrRyOPEYcuy"
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