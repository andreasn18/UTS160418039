package com.example.uts160418039.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uts160418039.R
import com.example.uts160418039.model.ChatRoom
import com.example.uts160418039.model.Friend
import com.example.uts160418039.util.loadImage
import kotlinx.android.synthetic.main.chat_item.view.*

class ChatBoxAdapter(val chatRooms: ArrayList<ChatRoom>, var id: String) :
    RecyclerView.Adapter<ChatBoxAdapter.ChatBoxHolder>() {
    class ChatBoxHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun updateChatRoom(newChats: List<ChatRoom>, id: String) {
        chatRooms.clear()
        chatRooms.addAll(newChats)
        this.id = id
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatBoxHolder {
        var v: View = View(parent.context)
        val inflater = LayoutInflater.from((parent.context))
        v = inflater.inflate(R.layout.chat_item, parent, false)
        return ChatBoxHolder(v)
    }

    override fun onBindViewHolder(holder: ChatBoxHolder, position: Int) {
        val chat = chatRooms[id.toInt()-1].chats
        if (chat[position].pengirim == "Tester") {
            holder.view.txtSenderChat.setText(chat[position].text)
            holder.view.cardReceiver.visibility = View.GONE
            holder.view.profile_image.visibility = View.GONE
            holder.view.progressBar5.visibility = View.GONE
        } else {
            val friend = chatRooms[id.toInt()-1].friend as Friend
            holder.view.profile_image.loadImage(friend.image_url, holder.view.progressBar5)
            holder.view.txtReceiverChat.setText(chat[position].text)
            holder.view.cardSender.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return chatRooms.size
    }
}