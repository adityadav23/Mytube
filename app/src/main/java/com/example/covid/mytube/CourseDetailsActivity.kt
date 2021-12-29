package com.example.covid.mytube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CourseDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_details)

        val navBarTitle = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = navBarTitle
    }

    private class CourseDetailAdapter: RecyclerView.Adapter<CourseDetailViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseDetailViewHolder {
            return CourseDetailViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.course_lesson_row,
                parent, false))

        }

        override fun onBindViewHolder(holder: CourseDetailViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

        override fun getItemCount() = 3

    }

    private class CourseDetailViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }
}