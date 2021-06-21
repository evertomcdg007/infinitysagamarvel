package br.com.fortestecnologia.infinitySagaMarvel.ui.films

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.fortestecnologia.infinitySagaMarvel.data.entities.FilmEntity
import br.com.fortestecnologia.infinitySagaMarvel.data.repository.FilmRepository
import org.w3c.dom.Entity

class FilmsViewModel @ViewModelInject constructor(
    private val repository: FilmRepository
) : ViewModel() {
    val films = repository.getFilms()

    val readData = repository.readFilm().asLiveData()

    fun searchDatabase(searchQuery: String): LiveData<List<FilmEntity>> {
        return repository.searchFilms(searchQuery).asLiveData()
    }



}