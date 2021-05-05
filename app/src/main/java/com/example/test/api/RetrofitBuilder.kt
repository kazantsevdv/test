package com.example.test.api

import com.example.test.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.*

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://5e510330f2c0d300147c034c.mockapi.io/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    fun provideGson() = GsonBuilder()
       // .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
       // .excludeFieldsWithoutExposeAnnotation()
        .create()

    fun client(key: String) =
        OkHttpClient.Builder().addInterceptor {
            val original = it.request()
            val url = original.url.newBuilder()
                .addQueryParameter("api_key", key)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build();
            it.proceed(request)

        }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()

    private fun getBasicAuthenticator(userName: String, password: String): Authenticator? {
        return Authenticator { route, response ->
            val credential: String = Credentials.basic(userName, password)
            response.request.newBuilder().header("Authorization", credential).build()
        }
    }
}

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("appid", "hello")
                .addHeader("deviceplatform", "android")
                .removeHeader("User-Agent")
                .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0"
                )
                .build()
        )
    }
}
