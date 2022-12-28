package id.rafli.mychecklist.network

import id.rafli.mychecklist.model.*
import id.rafli.mychecklist.network.response.*
import retrofit2.Response
import retrofit2.http.*

interface ApiServiceServer {

    @POST("login")
    suspend fun login(
        @Body user:User
    ): Response<AuthResponse>

    @POST("register")
    suspend fun register(
        @Body user:User
    ): Response<AuthResponse>

    @GET("checklist")
    fun getAllChecklist(
        @Header("Authorization") token: String,
    ): Response<ChecklistResponse>

}