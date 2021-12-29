package com.example.covid.mytube

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.covid.mytube.Models.CourseFeed
import com.example.covid.mytube.Models.HomeFeed
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException

class CourseDetailsActivity : AppCompatActivity() {

    lateinit var  recyclerViewCourseDetail: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_details)

        recyclerViewCourseDetail = findViewById(R.id.recyclerView_course_detail)
        val navBarTitle = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = navBarTitle

        fetchJson()
    }

    private fun fetchJson() {
        val videoId = intent.getIntExtra(CustomViewHolder.VIDEO_ID_KEY,-1)
        val courseDetailUrl = "https://api.letsbuildthatapp.com/youtube/course_detail?id=${videoId}"

        val request = Request.Builder().url(courseDetailUrl).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {

                Toast.makeText(applicationContext, "Failed to get request response", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()
                val courseFeed = gson.fromJson(body, Array<CourseFeed>::class.java)
                runOnUiThread {
                    recyclerViewCourseDetail.adapter = CourseDetailAdapter(courseFeed)
                }
            }

        })
    }


    private class CourseDetailAdapter(val courseFeed: Array<CourseFeed>): RecyclerView.Adapter<CourseDetailViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseDetailViewHolder {
            return CourseDetailViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.course_lesson_row,
                parent, false))

        }

        override fun onBindViewHolder(holder: CourseDetailViewHolder, position: Int) {
            val data = courseFeed.get(position)
            holder.courseDetailTitle.text = data.name
            holder.courseEpisode.text = "${data.name}" +
                    "\n${data.duration}"
            val courseDetailImageUri = data.imageUrl
            Picasso.get().load(courseDetailImageUri).into(holder.courseImage)

            holder.courseFeed = data
        }

        override fun getItemCount() = courseFeed.size

    }

    private class CourseDetailViewHolder(view: View, var courseFeed: CourseFeed? = null): RecyclerView.ViewHolder(view) {

        val courseImage: ImageView = view.findViewById(R.id.imageView_course_detail)
        val courseDetailTitle: TextView = view.findViewById(R.id.textView_course_lesson_title)
        val courseEpisode: TextView = view.findViewById(R.id.textView_course_info)

        init {
            view.setOnClickListener {
                val intent = Intent(view.context, CourseLesson::class.java)
                intent.putExtra(CustomViewHolder.VIDEO_ID_KEY, courseFeed?.link)
                view.context.startActivity(intent)
            }
        }
    }
}