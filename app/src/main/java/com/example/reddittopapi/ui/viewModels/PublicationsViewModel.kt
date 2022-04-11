package com.example.reddittopapi.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reddittopapi.data.entity.PublicationTable
import com.example.reddittopapi.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class PublicationsViewModel(private val repository: Repository) : ViewModel() {

    private val _kind = MutableLiveData<List<PublicationTable>>()
    val kind: LiveData<List<PublicationTable>> = _kind

    init {
        subscribe()
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getTop()
            } catch (e: Exception) {
                Log.d("test", e.toString())
            }
        }
    }

    private fun subscribe() {
        viewModelScope.launch {
            repository.posts.collect {
                _kind.value = it
            }
        }
    }

//    fun getTop() {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                repository.getTop()
//            } catch (e: Exception) {
//                Log.d("test", e.toString())
//            }
//        }
//    }
}