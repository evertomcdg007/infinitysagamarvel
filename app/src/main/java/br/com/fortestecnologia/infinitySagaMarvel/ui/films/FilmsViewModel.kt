package br.com.fortestecnologia.infinitySagaMarvel.ui.films

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import br.com.fortestecnologia.infinitySagaMarvel.data.repository.FilmRepository

class FilmsViewModel @ViewModelInject constructor(
    private val repository: FilmRepository
) : ViewModel() {
    val films = repository.getFilms()
}