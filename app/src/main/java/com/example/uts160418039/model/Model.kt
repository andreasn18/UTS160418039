package com.example.uts160418039.model

data class ChatRoom(
    val friend:Any?,
    val id:String?,
    val chats:ArrayList<Chat>
)
data class Chat(
    val idRoom:String?,
    val pengirim:String?,
    val id:String?,
    val text:String?
)

data class Friend(
    val id:String?,
    val name:String?,
    val phoneNumber:String?,
    val image_url:String?,
    val isFavorite:String?
)