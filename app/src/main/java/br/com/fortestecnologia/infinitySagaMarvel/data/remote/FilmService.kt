package br.com.fortestecnologia.infinitySagaMarvel.data.remote

import br.com.fortestecnologia.infinitySagaMarvel.data.entities.FilmEntity
import br.com.fortestecnologia.infinitySagaMarvel.data.entities.FilmList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmService {

    @GET("saga")
    suspend fun getAllFilms() : Response<List<FilmEntity>>

    @GET("saga/{id}")
    suspend fun getFilm(@Path("id") id: Int): Response<FilmEntity>
}