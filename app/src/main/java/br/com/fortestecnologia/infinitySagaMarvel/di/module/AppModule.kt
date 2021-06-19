package br.com.fortestecnologia.infinitySagaMarvel.di.module

import android.content.Context
import br.com.fortestecnologia.infinitySagaMarvel.data.local.AppDataBase
import br.com.fortestecnologia.infinitySagaMarvel.data.local.FilmDao
import br.com.fortestecnologia.infinitySagaMarvel.data.remote.FilmRemoteDataSource
import br.com.fortestecnologia.infinitySagaMarvel.data.remote.FilmService
import br.com.fortestecnologia.infinitySagaMarvel.data.repository.FilmRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://private-b34167-rvmarvel.apiary-mock.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideFilmService(retrofit: Retrofit): FilmService = retrofit.create(FilmService::class.java)

    @Singleton
    @Provides
    fun provideFilmRemoteDataSource(filmService: FilmService) = FilmRemoteDataSource(filmService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDataBase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideFilmDao(db: AppDataBase) = db.filmDao()

    @Singleton
    @Provides
    fun provideRepository(filmRemoteDataSource: FilmRemoteDataSource,
                          filmLocalDataSource: FilmDao
    ) = FilmRepository(filmLocalDataSource, filmRemoteDataSource)
}