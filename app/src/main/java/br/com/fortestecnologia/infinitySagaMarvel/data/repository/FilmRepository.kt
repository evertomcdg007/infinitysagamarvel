package br.com.fortestecnologia.infinitySagaMarvel.data.repository

import br.com.fortestecnologia.infinitySagaMarvel.data.local.FilmDao
import br.com.fortestecnologia.infinitySagaMarvel.data.remote.FilmRemoteDataSource
import br.com.fortestecnologia.infinitySagaMarvel.utils.performGetOperation
import javax.inject.Inject

class FilmRepository @Inject constructor(
    private val filmLocalDataSource: FilmDao,
    private val filmRemoteDataSource: FilmRemoteDataSource
){


    fun getFilm(id: Int) = performGetOperation(
        databaseQuery = { filmLocalDataSource.get(id) },
        networkCall = { filmRemoteDataSource.getFilm(id) },
        saveCallResult = { filmLocalDataSource.insert(it) }
    )

    fun getFilms() = performGetOperation(
        databaseQuery = { filmLocalDataSource.getAll() },
        networkCall = { filmRemoteDataSource.getAllFilms() },
        saveCallResult = { filmLocalDataSource.insertAll(it) }
    )

}