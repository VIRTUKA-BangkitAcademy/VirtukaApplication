package com.example.firtuka2.data.retrofit
import android.media.Image
import com.example.firtuka2.data.response.GlassesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Multipart
    @POST("/faceanalize")
    fun faceAnalysis(
        @Part photo: MultipartBody.Part,
        @Part("gender") gender: RequestBody,
        ) : Call<GlassesResponse>

    @GET("/api/frames/{id}")
    fun getFramesById(
        @Path("id") id: String
    ): Call<GlassesResponse>

    @GET("/api/frames/")
    fun getAllFrames(
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
    ): Call<GlassesResponse>

    @GET("/api/frames/")
    suspend fun getAllFramesPaging(
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
    ): GlassesResponse


    @DELETE
    fun deleteFramesById(
        @Path("id") id: String
    )

    @GET("frame/{id}")
    fun getFrame(
        @Path("id") id: String
    ): Call<GlassesResponse>
}