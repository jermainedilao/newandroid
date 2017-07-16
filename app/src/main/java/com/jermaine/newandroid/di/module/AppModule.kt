package com.jermaine.newandroid.di.module


import android.app.Application
import android.arch.persistence.room.Room
import android.util.Log
import com.google.gson.Gson
import com.jermaine.newandroid.api.ApiService
import com.jermaine.newandroid.app.App
import com.jermaine.newandroid.datamodel.database.NewAndroidDatabase
import com.jermaine.newandroid.repository.ReposRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(internal var mApplication: Application) {

    @Provides
    @Singleton
    fun providesGson(): Gson = Gson()

    @Provides
    @Singleton
    fun providesRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(provideOkHttpClient())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                message -> Log.d("HttpLogger", "log: " + message)
            }).setLevel(HttpLoggingInterceptor.Level.HEADERS))
            .build()

    @Provides
    @Singleton
    fun providesRetrofitRepository(): ReposRepositoryImpl = ReposRepositoryImpl((mApplication as App).component)

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesNewAndroidDatabase(): NewAndroidDatabase =
            Room.databaseBuilder(mApplication, NewAndroidDatabase::class.java, "newandroid-database").build()
}
