package com.sytyy.giphytest.model.module

import com.sytyy.giphytest.model.api.GifApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.giphy.com/v1/gifs/"

@Module
@InstallIn(SingletonComponent::class)
object GifApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    @Singleton
    fun provideGifApi(retrofit: Retrofit): GifApi = retrofit.create(GifApi::class.java)

}