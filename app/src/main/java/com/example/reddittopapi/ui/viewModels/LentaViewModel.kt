package com.example.reddittopapi.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reddittopapi.data.entity.Response
import com.example.reddittopapi.data.repository.Repository
import kotlinx.coroutines.launch

class LentaViewModel(private val repository: Repository) : ViewModel() {

    private val _kind = MutableLiveData<Response>()
    val kind: LiveData<Response> = _kind

    fun fetchData() {
        viewModelScope.launch {
            Log.d("test", "viewMOdel")
            _kind.value = repository.getResponse()
        }
    }
}