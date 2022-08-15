package com.samar.hitmovies.di

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.samar.hitmovies.R
import com.samar.hitmovies.common.Constants.BASE_URL
import com.samar.hitmovies.data.remote.MovieApi
import com.samar.hitmovies.data.repository.MoviesRepositoryImp
import com.samar.hitmovies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Singleton
    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    @Singleton
    @Provides
    fun getOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @ApplicationContext context: Context
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .callTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(httpLoggingInterceptor)
            httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader("X-RapidAPI-Host", context.resources.getString(R.string.Host))
                .addHeader("X-RapidAPI-Key",context.resources.getString(R.string.Key))
                .build()
            val response: Response = chain.proceed(request)
            response
        })

        return httpClient.build()
    }


    @Singleton
    @Provides
    fun getGroceryApi(httpClient: OkHttpClient): MovieApi {
        val gson = GsonBuilder()
            .setLenient()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create()
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .client(httpClient)
            .build()
        return retrofit.create(MovieApi::class.java)
    }

    @Singleton
    @Provides
    fun getMovieRepository(movieApi: MovieApi): MovieRepository {
        return MoviesRepositoryImp(movieApi)
    }

}