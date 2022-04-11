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

class ImageViewModel(
    private val repository: Repository,
    private val id: String
) : ViewModel() {

    private val _post = MutableLiveData<PublicationTable>()
    val post: LiveData<PublicationTable> = _post

    init {
        subscribe()
        getSomePost()
    }

    private fun subscribe() {
        viewModelScope.launch {
            repository.post.collect {
                _post.value = it
            }
        }
    }

    private fun getSomePost() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getSomePost(id)
            } catch (e: Exception) {
                Log.d("test", e.toString())
            }
        }
    }
}