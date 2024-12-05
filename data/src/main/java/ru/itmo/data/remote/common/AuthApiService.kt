package ru.itmo.data.remote.common

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Field

data class AccessTokenResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Int
)

interface AuthApiService {
    @FormUrlEncoded
    @POST("v1/security/oauth2/token")
    fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Call<AccessTokenResponse>
}

fun createAuthApiService(): AuthApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://test.api.amadeus.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(AuthApiService::class.java)
}
