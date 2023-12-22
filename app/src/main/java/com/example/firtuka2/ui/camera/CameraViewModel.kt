package com.example.firtuka2.ui.camera

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firtuka2.data.helper.reduceFileImage
import com.example.firtuka2.data.response.GlassesResponse
import com.example.firtuka2.data.retrofit.ApiConfig
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class CameraViewModel : ViewModel() {

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _glasses = MutableLiveData<GlassesResponse>()
    val story: LiveData<GlassesResponse> = _glasses

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun getGlassesByPhoto(photo: MultipartBody.Part, gender: RequestBody){
        val client = ApiConfig.getApiService().faceAnalysis(photo, gender)
        Log.d(TAG, "getGlassesByPhoto: $client")
        client.enqueue(object : Callback<GlassesResponse> {
            override fun onResponse(call: Call<GlassesResponse>, response: Response<GlassesResponse>) {
                if (response.isSuccessful) {
                    _glasses.value = response.body()
                    Log.d(TAG, "onResponse: ${_glasses.value}")
                } else {
                    val errorBody = response.errorBody()?.string()
                    _isLoading.value = false
                    Log.e(TAG, "onResponse: $errorBody")
                }
            }

            override fun onFailure(call: Call<GlassesResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

//    fun uploadImage(imageFile: File, gender: String){
//        _isLoading.value = true
//        val genderBox = gender.toRequestBody("text/plain".toMediaType())
//        val requestImageFile = imageFile.reduceFileImage().asRequestBody("image/webp".toMediaType())
//
//        val multipartBody = MultipartBody.Part.createFormData(
//            "photo",
//            imageFile.name,
//            requestImageFile
//        )
//        val client = ApiConfig.getApiService().faceAnalysis(multipartBody, gender = genderBox )
//        client.enqueue(object : Callback<GlassesResponse> {
//            override fun onResponse(
//                call: retrofit2.Call<GlassesResponse>,
//                response: Response<GlassesResponse>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    _glasses.value = response.body()
//                } else {
//                    val failedResponse = response.body()
//                    _glasses.value = GlassesResponse( status = "error", message = "Upload Failed")
//                    Log.e(TAG, "onFailure: ${failedResponse}")
//                }
//            }
//            override fun onFailure(call: retrofit2.Call<GlassesResponse>, t: Throwable) {
//                _isLoading.value = false
//                _glasses.value = GlassesResponse( message = t.message.toString())
//
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//        })
//    }

    companion object {
        private const val TAG = "ADD_STORY_VIEW_MODEL"
    }
}