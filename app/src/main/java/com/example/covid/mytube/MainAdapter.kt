package com.example.covid.mytube

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount() = homeFeed.videos.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.video_row,
        parent, false))

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
      val video = homeFeed.videos.get(position)

       holder.videoTitle.text = video.name
    }
}

class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      val videoImage: ImageView = view.findViewById(R.id.imageView_video_image)
      val channelImage: ImageView = view.findViewById(R.id.imageView_channel_image)
      val videoTitle: TextView = view.findViewById(R.id.textView_video_title)


}

