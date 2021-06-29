package com.example.lifeline.networking

import com.example.lifeline.data.Parameters
import com.example.lifeline.data.Personal
import com.example.lifeline.data.Pulse
import com.example.lifeline.networking.post.request.SignInRequest
import com.example.lifeline.networking.post.request.SignUpRequest
import com.example.lifeline.networking.post.response.SignInResponse
import com.example.lifeline.networking.post.response.SignUpResponse
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APIService {
    @POST("auth/login")
    fun signIn(@Body body: SignInRequest):Observable<Response<SignInResponse>>

    @POST("auth/register")
    fun signUp(@Body body: SignUpRequest):Observable<SignUpResponse>

    @PUT("users/personal/{id}")
    fun putPersonal(@Path("id") id:Long, @Body request: Personal):Observable<Personal>

    @PUT("users/parameters/{id}")
    fun putParameters(@Path("id") id:Long, @Body request: Parameters):Observable<Parameters>

    @POST("constant/pulse")
    fun postPulse(@Body request:Pulse):Completable
}