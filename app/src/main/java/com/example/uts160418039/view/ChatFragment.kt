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

class ChatFragment : Fragment() {
    private lateinit var viewModel: ChatRoomListViewModel
    private val chatBoxAdapter = ChatFragmentAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChatRoomListViewModel::class.java)
        viewModel.refresh()
        recView1.layoutManager = LinearLayoutManager(context)
        recView1.adapter = chatBoxAdapter
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.chatRoomLD.observe(viewLifecycleOwner, Observer {
            chatBoxAdapter.updateChatBox(it)
        })

        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer {
            if (it){
                txtError1.visibility = View.VISIBLE
            }
            else{
                txtError1.visibility = View.GONE
            }
        })

        viewModel.loadingDoneLd.observe(viewLifecycleOwner, Observer {
            if(it){
                progressLoad1.visibility = View.GONE
                recView1.visibility = View.VISIBLE
            }
            else{
                progressLoad1.visibility = View.VISIBLE
                recView1.visibility = View.GONE
            }
        })
    }
}