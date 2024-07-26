package com.example.meritmatch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private val retrofit=Retrofit.Builder().baseUrl("http://10.0.2.2:5000/").addConverterFactory(GsonConverterFactory.create()).build()
val Client= retrofit.create(Login::class.java)
interface Login {
    @POST("create")
    suspend fun postUserDetails(@Body userDetails: UserDetails): createResponse

    @GET("get")
    suspend fun checkIfUserExists(@Query("username") username:String ):checkIfUserExists

    @POST("password")
    suspend fun checkPassWord(@Body password: Password):checkValue

    @GET("karmaPoints")
    suspend fun getKarmaPoints(@Query("username") username:String):karmaValue

    @POST("task")
    suspend fun postTask(@Body postTask: postTask):createResponse

    @GET("fetch")
    suspend fun getTasksAvailable(@Query("username") username:String):returnTasks

    @POST("reserve")
    suspend fun reserveTask(@Body karma: karma):reserve

    @GET("accept")
    suspend fun acceptTaskReserve(@Query("name") name: String):returnTasks1

    @POST("transaction")
    suspend fun transaction(@Body transactionProcess:transactionProcess):message

    @GET("check")
    suspend fun checkUser(@Query("username") username:String):createResponse

    @POST("transactionPossible")
    suspend fun checkTransaction(@Body valid: valid):validResponse

    @POST("accepted")
    suspend fun acceptedCheck(@Body accept: accept):validResponse
}