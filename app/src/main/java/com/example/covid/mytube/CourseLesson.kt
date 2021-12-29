package com.example.covid.mytube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class CourseLesson : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_lesson)

        val courseLesson: WebView = findViewById(R.id.course_lesson_web_view)
        val link = intent.getStringExtra(CustomViewHolder.VIDEO_ID_KEY)

        courseLesson.settings.javaScriptEnabled = true
        courseLesson.settings.loadWithOverviewMode = true
        courseLesson.settings.useWideViewPort = true

        if(link!=null) {
            courseLesson.loadUrl(link)
        }
    }
}