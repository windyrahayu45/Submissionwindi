package com.example.submissionwindi.di

import com.example.submissionwindi.data.source.local.StoryPreferences
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module{
    single {
        provideOkhttpClient(get())
    }

    single {
        provideRetrofit(get())
    }
}

private fun provideOkhttpClient(storyPreferences: StoryPreferences): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .writeTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val original = chain.request()
            val token: String = storyPreferences.getToken()

            if (token.isNotEmpty()) {
                val authorized = original.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(authorized)
            } else {
                chain.proceed(original)
            }
        }
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://story-api.dicoding.dev/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}