package com.example.uts160418039.model

data class Chat(
    val pengirim:String?,
    val time:String?
)

data class Friend(
    val id:String?,
    val name:String?,
    val phoneNumber:String?,
    val image_url:String?,
    val isFavorite:String?
)