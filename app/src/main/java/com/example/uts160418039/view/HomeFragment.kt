package com.example.uts160418039.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.uts160418039.R
import com.example.uts160418039.model.Friend
import com.example.uts160418039.viewmodel.FriendListViewModel
import kotlinx.android.synthetic.main.fragment_create_chat.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var viewModel: FriendListViewModel
    val header: MutableList<String> = ArrayList()
    val body: MutableList<List<Friend>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        header.add("Favorite")
        header.add("Friends")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FriendListViewModel::class.java)
        viewModel.refresh()
        expand.setAdapter(ExpandAdapter(view.context, header, body))
        observeViewModel("")
    }

    fun observeViewModel(filter: String) {
        viewModel.friendLD.observe(viewLifecycleOwner, {
            var listFriend = it
            body.add(listFriend.filter {
                it.isFavorite!!.toLowerCase().contains("true")
            })
            body.add(it)
        })
        viewModel.loadingErrorLD.observe(viewLifecycleOwner, {
            if (it) {
                txtError2.visibility = View.VISIBLE
            } else {
                txtError2.visibility = View.GONE
            }
        })
        viewModel.loadingDoneLd.observe(viewLifecycleOwner, {
            if (it) {
                progressLoad2.visibility = View.GONE
                expand.visibility = View.VISIBLE
            } else {
                progressLoad2.visibility = View.VISIBLE
                expand.visibility = View.GONE
            }
        })
    }
}