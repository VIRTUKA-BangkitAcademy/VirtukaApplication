package com.example.firtuka2.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.firtuka2.data.repo.GlassesRepository
import com.example.firtuka2.data.response.FramesItem
import com.example.firtuka2.data.response.GlassesResponse
import com.example.firtuka2.data.retrofit.ApiConfig
import retrofit2.Response

class HomeViewModel(private val repository: GlassesRepository) : ViewModel() {

    private val _listGlasses = MutableLiveData<GlassesResponse>()
    val listGlasses : LiveData<GlassesResponse> = _listGlasses
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val glassesPaging: LiveData<PagingData<FramesItem>> =
        repository.getPagingGlasses().cachedIn(viewModelScope).asLiveData()

    fun getAll(){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getAllFrames()
        client.enqueue(object : retrofit2.Callback<GlassesResponse> {
            override fun onResponse(call: retrofit2.Call<GlassesResponse>, response: Response<GlassesResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listGlasses.value = response.body()
                } else {
                    _listGlasses.value = response.body()
                    if (response.code() == 401){
                        _listGlasses.value = GlassesResponse(status = response.message(), message = response.message() )
                    }
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<GlassesResponse>, t: Throwable) {
                _isLoading.value = false
                _listGlasses.value = GlassesResponse(status = t.message.toString(), message = t.message.toString())
                Log.e(TAG, "onFailure Fatal: ${t.message.toString()}")
            }
        })
    }

    companion object{
        private const val TAG = "USER_VIEW_MODEL"
    }
}