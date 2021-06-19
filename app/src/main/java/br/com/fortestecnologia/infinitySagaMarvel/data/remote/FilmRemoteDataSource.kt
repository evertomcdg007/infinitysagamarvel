package br.com.fortestecnologia.infinitySagaMarvel.data.remote

import javax.inject.Inject

class FilmRemoteDataSource @Inject constructor(
    private val filmService: FilmService
) : BaseDataSource() {

    suspend fun getAllFilms() = getResult { filmService.getAllFilms() }
    suspend fun getFilm(id: Int) = getResult { filmService.getFilm(id) }

}