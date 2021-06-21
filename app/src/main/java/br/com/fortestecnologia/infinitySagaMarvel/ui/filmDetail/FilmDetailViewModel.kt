package br.com.fortestecnologia.infinitySagaMarvel.ui.filmDetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import br.com.fortestecnologia.infinitySagaMarvel.data.entities.FilmEntity
import br.com.fortestecnologia.infinitySagaMarvel.data.repository.FilmRepository
import br.com.fortestecnologia.infinitySagaMarvel.utils.Resource

class FilmDetailViewModel @ViewModelInject constructor(
    private val repository: FilmRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _film = _id.switchMap { id ->
        repository.getFilm(id)
    }

    val film: LiveData<Resource<FilmEntity>> = _film


    fun start(id: Int){
        _id.value =id
    }

}