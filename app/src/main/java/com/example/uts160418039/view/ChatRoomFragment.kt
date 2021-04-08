package com.example.uts160418039.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uts160418039.R
import com.example.uts160418039.viewmodel.ChatRoomListViewModel
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_chat_room.*

class ChatRoomFragment : Fragment() {
    private lateinit var viewModel: ChatRoomListViewModel
    private var chatRoomAdapter = ChatBoxAdapter(arrayListOf(), "")
    var id = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChatRoomListViewModel::class.java)
        viewModel.refresh()
        recView2.layoutManager = LinearLayoutManager(context)
        recView2.adapter = chatRoomAdapter
        observeViewModel()
        btnSend.setOnClickListener {
            txtInputChat.setText("")
        }
    }

    fun observeViewModel() {
        viewModel.chatRoomLD.observe(viewLifecycleOwner, Observer {
            if (arguments != null) {
                chatRoomAdapter.updateChatRoom(
                    it,
                    ChatRoomFragmentArgs.fromBundle(requireArguments()).id
                )
            }
        })

        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer {
            if (it) {
                txtError5.visibility = View.VISIBLE
            } else {
                txtError5.visibility = View.GONE
            }
        })

        viewModel.loadingDoneLd.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressLoad3.visibility = View.GONE
                recView2.visibility = View.VISIBLE
            } else {
                progressLoad3.visibility = View.VISIBLE
                recView2.visibility = View.GONE
            }
        })
    }
}