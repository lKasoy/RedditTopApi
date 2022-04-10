package com.example.reddittopapi.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reddittopapi.data.entity.PublicationTable
import com.example.reddittopapi.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PublicationsViewModel(private val repository: Repository) : ViewModel() {

    private val _kind = MutableLiveData<List<PublicationTable>>()
    val kind: LiveData<List<PublicationTable>> = _kind

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getResponse()
            subscribe()
        }
    }

    private fun subscribe() {
        viewModelScope.launch {
            repository.posts.collect {
                _kind.value = it
            }
        }
    }
}