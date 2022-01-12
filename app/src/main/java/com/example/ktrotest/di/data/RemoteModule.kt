package com.example.ktrotest.di.data

import com.example.ktrotest.data.dailyBoxOffice.remote.DailyBoxOfficeRemoteDataSource
import com.example.ktrotest.data.dailyBoxOffice.remote.DailyBoxOfficeRemoteDataSourceImpl
import com.example.ktrotest.model.MovieApi
import com.example.ktrotest.util.Api
import dagger.Module
import dagger.Provides
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteModule {
    /*
    @Singleton
    @Provides
    fun providesKtorClient() : HttpClient {
        return HttpClient(CIO){
            install(JsonFeature){
                serializer = GsonSerializer()
            }
        }
    }

     */

    @Singleton
    @Provides
    fun providesBoxOfficeRemoteDataSource(movieApi: MovieApi):DailyBoxOfficeRemoteDataSource{
        return DailyBoxOfficeRemoteDataSourceImpl(movieApi)
    }



    @Singleton
    @Provides
    fun providesMovieApi(okHttpClient: OkHttpClient, factory: Converter.Factory): MovieApi {
        return Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(factory)
            .client(okHttpClient)
            .build()
            .create(MovieApi::class.java)
    }

    @Singleton
    @Provides
    fun providesLoggingInterceptor() : HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    @Singleton
    @Provides
    fun providesOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }


    @Singleton
    @Provides
    fun providesConverterFactory() : Converter.Factory {
        return GsonConverterFactory.create()
    }


}