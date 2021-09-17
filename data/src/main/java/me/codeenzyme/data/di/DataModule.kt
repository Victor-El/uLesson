package me.codeenzyme.data.di

import android.content.Context
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.codeenzyme.data.database.AppDatabase
import me.codeenzyme.data.remote.LessonsService
import me.codeenzyme.data.repositories.LessonRepositoryImpl
import me.codeenzyme.data.repositories.LessonsRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java,"ulesson.db").build()
    }

    @Provides
    @Singleton
    fun provideRewardService(retrofit: Retrofit): LessonsService = retrofit.create(LessonsService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://mock-live-lessons.herokuapp.com/api/v1/")
            .addConverterFactory(
                GsonConverterFactory.create(
                GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun getOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Singleton
    @Binds
    abstract fun bindLessonRepository(lessonsRepository: LessonRepositoryImpl): LessonsRepository
}