package com.example.covid.mytube.Models

import com.example.covid.mytube.Models.Channel

data class Video(val id: Int,
            val name: String,
            val  link: String,
            val imageUrl: String,
            val numberOfViews: Int,
            val channel: Channel
)