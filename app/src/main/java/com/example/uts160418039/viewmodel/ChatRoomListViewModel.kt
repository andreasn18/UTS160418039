package com.example.uts160418039.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uts160418039.model.Chat
import com.example.uts160418039.model.ChatRoom
import com.example.uts160418039.model.Friend
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ChatRoomListViewModel(application: Application) : AndroidViewModel(application) {
    val chatRoomLD = MutableLiveData<List<ChatRoom>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingDoneLd = MutableLiveData<Boolean>()

    fun refresh() {
        val chats1 = arrayListOf<Chat>()
        val chats2 = arrayListOf<Chat>()
        val chats3 = arrayListOf<Chat>()
        val chat1 = Chat("1", "Krishnah", "1", "Hai")
        val chat2 = Chat("1", "Tester", "2", "Hai juga")
        val chat3 = Chat("1", "Krishnah", "3", "Apa Kabar?")
        val chat4 = Chat("1", "Tester", "4", "Baik")
        val chat5 = Chat("2", "Obed", "5", "Main Skuy")
        val chat6 = Chat("2", "Tester", "6", "Kuy")
        val chat7 = Chat("2", "Obed", "7", "Kutunggu di lobby")
        val chat8 = Chat("3", "Tester", "8", "Minta Uang gan")
        val chat9 = Chat("3", "Ugo", "9", "Hah? Jarang banget lu minta uang bro!")
        val chat10 = Chat("3", "Tester", "10", "Iya, lagi seret nih")
        chats1.add(chat1)
        chats1.add(chat2)
        chats1.add(chat3)
        chats1.add(chat4)
        chats2.add(chat5)
        chats2.add(chat6)
        chats2.add(chat7)
        chats3.add(chat8)
        chats3.add(chat9)
        chats3.add(chat10)
        val krishnah = Friend(
            "Krishnah",
            "Krishnah Tripean",
            "201-546-0562",
            "https://robohash.org/inestcupiditate.png?size=100x75\u0026set=set1",
            "true"
        )
        val obed = Friend(
            "Obed",
            "Obed Bearsmore",
            "314-842-3048",
            "https://robohash.org/impeditnatusvoluptatem.png?size=100x75\u0026set=set1",
            "false"
        )
        val ugo = Friend(
            "Ugo",
            "Ugo Brodeur",
            "930-782-8816",
            "https://robohash.org/eosofficiisquia.png?size=100x75\u0026set=set1",
            "false"
        )
        val chatRoom1 = ChatRoom(krishnah,"1", chats1)
        val chatRoom2 = ChatRoom(obed,"2", chats2)
        val chatRoom3 = ChatRoom(ugo,"3", chats3)
        val chatRoomList = arrayListOf<ChatRoom>(chatRoom1, chatRoom2, chatRoom3)
        chatRoomLD.value = chatRoomList
        loadingDoneLd.value = true
        loadingErrorLD.value = false

    }
}