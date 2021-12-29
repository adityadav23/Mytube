package com.example.covid.mytube

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.covid.mytube.Models.HomeFeed
import com.example.covid.mytube.Models.Video
import com.squareup.picasso.Picasso

class MainAdapter(val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount() = homeFeed.videos.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.video_row,
            parent, false))

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val video = homeFeed.videos.get(position)
        //Initializing video argument in CustomViewHolder
        holder.video = video

        holder.videoTitle.text = video.name
        holder.channelName.text = video.channel.name +" - " + "${video.numberOfViews} views"+"\n 4 days ago"
        val channelImageUri = video.channel.profileImageUrl
        Picasso.get().load(channelImageUri).into(holder.channelImage)

        val videoImageUri = video.imageUrl
        Picasso.get().load(videoImageUri).into(holder.videoImage)

    }
}

class CustomViewHolder(view: View, var video: Video? = null) : RecyclerView.ViewHolder(view) {
   companion object{
       val VIDEO_TITLE_KEY ="VIDEO_TITLE"
       val VIDEO_ID_KEY ="VIDEO_ID"

   }
    val videoImage: ImageView = view.findViewById(R.id.imageView_video_image)
    val channelImage: ImageView = view.findViewById(R.id.imageView_channel_profile_image)
    val videoTitle: TextView = view.findViewById(R.id.textView_video_title)
    val channelName: TextView = view.findViewById(R.id.textView_channel_name)

    init {
        view.setOnClickListener {

            val intent = Intent(view.context, CourseDetailsActivity::class.java)
            intent.putExtra(VIDEO_TITLE_KEY, video?.name)
            intent.putExtra(VIDEO_ID_KEY, video?.id)
            view.context.startActivity(intent)
        }
    }


}

