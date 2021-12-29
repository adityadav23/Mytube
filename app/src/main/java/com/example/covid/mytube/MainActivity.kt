package com.example.covid.mytube

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.covid.mytube.Models.HomeFeed
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var recyclerViewMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         recyclerViewMain = findViewById(R.id.recyclerView_main)
      //  recyclerViewMain.adapter = MainAdapter()

        fetchJson()
    }

    private fun fetchJson() {
        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {

                    Toast.makeText(applicationContext, "Failed to get request response", Toast.LENGTH_SHORT)
                        .show()
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)
                runOnUiThread {
                    recyclerViewMain.adapter = MainAdapter(homeFeed)

                }
            }

        })
    }
}

