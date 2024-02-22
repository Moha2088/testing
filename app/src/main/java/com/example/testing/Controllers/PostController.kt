package com.example.testing.Controllers

import android.app.AlertDialog
import com.example.testing.models.Post
import com.google.gson.Gson
import com.google.gson.JsonParseException
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request.Builder
import okhttp3.Response
import java.io.IOException

class PostController {

    var deserializedList: MutableList<Post> = mutableListOf()
    private lateinit var dialog: AlertDialog

    fun getPost(uri: String) {
        val client = OkHttpClient
            .Builder()
            .build()

        val request = Builder()
            .url(uri)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val gson = Gson()
                    response.body?.string().let { jsonString ->
                        try {
                            val desObj = gson.fromJson(jsonString, Post::class.java)
                            val objArray = arrayOf(desObj)
                            for (post in objArray){
                                deserializedList.add(post)
                            }

                        } catch (jsonEx: JsonParseException) {
                            dialog.setTitle("ERROR")
                            dialog.setMessage("${jsonEx.message}")
                        }
                    }
                }
            }
        })
    }
}