package com.example.uts160418039.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uts160418039.R
import com.example.uts160418039.model.Friend
import com.example.uts160418039.util.loadImage
import kotlinx.android.synthetic.main.create_chat_item.view.*

class CreateChatAdapter(val friendList: ArrayList<Friend>) :
    RecyclerView.Adapter<CreateChatAdapter.ChatViewHolder>() {
    class ChatViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun updateFriendList(newFriendList: List<Friend>) {
        friendList.clear()
        friendList.addAll(newFriendList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.create_chat_item, parent, false)
        return ChatViewHolder(v)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.view.txtFriendName.text = friendList[position].name
        holder.view.profile_image.loadImage(friendList[position].image_url, holder.view.progressBar)
    }

    override fun getItemCount(): Int {
        return friendList.size
    }
}