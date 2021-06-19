package br.com.fortestecnologia.infinitySagaMarvel.ui.filmDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fortestecnologia.infinitySagaMarvel.R

class FilmDetailFragment : Fragment() {

    companion object {
        fun newInstance() = FilmDetailFragment()
    }

    private lateinit var viewModel: FilmDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.film_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FilmDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}