package com.example.uts160418039.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uts160418039.model.Friend
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FriendListViewModel(application: Application) : AndroidViewModel(application) {
    val friendLD = MutableLiveData<List<Friend>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingDoneLd = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        loadingDoneLd.value = false
        loadingErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://api.npoint.io/89f95d69dd28d20c4faf"
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<List<Friend>>() {}.type
                val result = Gson().fromJson<List<Friend>>(response, sType)
                friendLD.value = result
                loadingDoneLd.value = true
                Log.d("showvoley", response.toString())
            },
            {
                Log.d("showvoley", it.toString())
                loadingErrorLD.value = true
                loadingDoneLd.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}