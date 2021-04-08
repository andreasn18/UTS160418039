package com.example.uts160418039.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uts160418039.R
import com.example.uts160418039.viewmodel.FriendListViewModel
import kotlinx.android.synthetic.main.fragment_create_chat.*


class CreateChatFragment : Fragment() {
    private lateinit var viewModel: FriendListViewModel
    private val createChatAdapter = CreateChatAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(FriendListViewModel::class.java)
        viewModel.refresh()
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = createChatAdapter
        observeViewModel("")
    }

    fun observeViewModel(filter:String) {
        viewModel.friendLD.observe(viewLifecycleOwner, {
            var listFriend = it
            createChatAdapter.updateFriendList(listFriend.filter {
                it.name!!.toLowerCase().contains(filter)
            })
        })
        viewModel.loadingErrorLD.observe(viewLifecycleOwner, {
            if (it) {
                txtError.visibility = View.VISIBLE
            } else {
                txtError.visibility = View.GONE
            }
        })
        viewModel.loadingDoneLd.observe(viewLifecycleOwner, {
            if (it) {
                progressLoad.visibility = View.GONE
                recView.visibility = View.VISIBLE
            } else {
                progressLoad.visibility = View.VISIBLE
                recView.visibility = View.GONE
            }
            refreshLayout.setOnRefreshListener {
                recView.visibility = View.GONE
                txtError.visibility = View.GONE
                progressLoad.visibility = View.VISIBLE
                viewModel.refresh()
                refreshLayout.isRefreshing = false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val menuItem = menu.findItem(R.id.searchbar)
        val search = menuItem.actionView as SearchView
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("Search", query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                observeViewModel(newText.toString())
                Log.d("Search", newText.toString())
                return true
            }
        })
    }


}