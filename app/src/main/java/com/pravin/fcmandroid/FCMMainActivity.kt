package com.pravin.fcmandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
import com.pravin.fcmandroid.databinding.ActivityMainBinding
import com.pravin.fcmandroid.retrofit.FCMNotification
import com.pravin.fcmandroid.retrofit.FcmTriggerApiService
import com.pravin.fcmandroid.retrofit.Notification
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class FCMMainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    lateinit var binding: ActivityMainBinding
    lateinit var token:String
    private val KEY1 = "KEY1"
    private val KEY2 = "KEY2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appTopic.setOnCheckedChangeListener(this)
        binding.freeUserTopic.setOnCheckedChangeListener(this)
        binding.premiumUserTopic.setOnCheckedChangeListener(this)

    }

    override fun onStart() {
        super.onStart()
        if (intent?.hasExtra(KEY1) == true){
            for (key in intent.extras?.keySet()!!){
                Log.e("**", "FCM DATA -> ${key}  ${intent.extras!!.getString(key)}" )
            }
        }

        getToken()

    }

    fun getToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
                if (it.isSuccessful){
                    token = it.getResult()
                    Log.e("**", "FCM getToken: "+token )
                }else{
                    Log.e("**", "FCM getToken: FAILED")
                }
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        val topic:String = buttonView?.text.toString()

        if (isChecked) FirebaseMessaging.getInstance().subscribeToTopic(topic).addOnCompleteListener {
            task->
            if (task.isSuccessful)
                Log.e("**", "FCM TOPIC ${buttonView?.text} SUBSCRIBED" )
            else
                Log.e("**", "FCM TOPIC ${buttonView?.text} SUBSCRIBED FAILED" )
        }
        else
            FirebaseMessaging.getInstance().unsubscribeFromTopic(topic).addOnCompleteListener {
                task->
                if (task.isSuccessful)
                    Log.e("**", "FCM TOPIC ${buttonView?.text} UN SUBSCRIBED" )
                else
                    Log.e("**", "FCM TOPIC ${buttonView?.text} UN SUBSCRIBED FAILED" )
        }
    }

    fun sendnotification(view: android.view.View) {
        triggerNotificationToToken(token)
        Snackbar.make(view, "Notification should not be sent from device", Snackbar.LENGTH_SHORT).show()

    }

    private fun triggerNotificationToToken(token:String) {
        val fcmNotification = FCMNotification(
            Notification(title = "Title", body = "Body"),
            to = token)

        FcmTriggerApiService.getInstance().createFCMNotification(
            FcmTriggerApiService.SERVER_KEY,fcmNotification)
            .enqueue(object:retrofit2.Callback<Any?> {
                override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                    if (response.isSuccessful){
                        Log.e("**", "onResponse: Success "+response.code() )
                    }else{
                        Log.e("**", "onResponse: Not sucess "+response.toString() )
                        Log.e("**", "onResponse: Not sucess "+response.headers() )
                    }
                }

                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    Log.e("**", "onResponse: Failed "+t.message )
                }

            })
    }
}