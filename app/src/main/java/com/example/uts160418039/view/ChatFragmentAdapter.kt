package com.example.uts160418039.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.uts160418039.R
import com.example.uts160418039.model.Chat
import com.example.uts160418039.model.ChatRoom
import com.example.uts160418039.model.Friend
import com.example.uts160418039.util.loadImage
import kotlinx.android.synthetic.main.chatbox_item.view.*

class ChatFragmentAdapter(val chatboxs:ArrayList<ChatRoom>):RecyclerView.Adapter<ChatFragmentAdapter.ChatBoxHolder>() {
    class ChatBoxHolder(val view:View) : RecyclerView.ViewHolder(view)

    fun updateChatBox(newChatBoxList: List<ChatRoom>) {
        chatboxs.clear()
        chatboxs.addAll(newChatBoxList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatBoxHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.chatbox_item, parent, false)
        return ChatBoxHolder(v)
    }

    override fun onBindViewHolder(holder: ChatBoxHolder, position: Int) {
        val friend = chatboxs[position].friend as Friend
        val url = friend.image_url
        holder.view.profile_image.loadImage(url, holder.view.progressBar4)
        var lastChat = ""
            for (item in chatboxs[position].chats) {
                lastChat = item.text.toString()
            }
        holder.view.txtSenderName.setText(friend.name)
        holder.view.txtLastChat.setText(lastChat)
        holder.view.setOnClickListener {
            val action = ChatFragmentDirections.actionToChatRoom(chatboxs[position].id.toString())
            Navigation.findNavController(holder.view).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return chatboxs.size
    }
}