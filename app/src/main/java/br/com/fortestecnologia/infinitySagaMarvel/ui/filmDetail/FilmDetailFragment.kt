package br.com.fortestecnologia.infinitySagaMarvel.ui.filmDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import br.com.fortestecnologia.infinitySagaMarvel.data.entities.FilmEntity
import br.com.fortestecnologia.infinitySagaMarvel.databinding.FilmDetailFragmentBinding
import br.com.fortestecnologia.infinitySagaMarvel.utils.Resource
import br.com.fortestecnologia.infinitySagaMarvel.utils.autoCleared
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmDetailFragment : Fragment() {

    private var binding: FilmDetailFragmentBinding by autoCleared()
    private val viewModel: FilmDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FilmDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.film.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindFilm(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.filmCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.filmCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindFilm(film: FilmEntity) {
        binding.title.text = film.title
        binding.year.text = film.year
        binding.director.text = film.director
        binding.genre.text = film.genre
        binding.plot.text = film.plot
        Glide.with(binding.root)
            .load(film.poster)
            .transform(CircleCrop())
            .into(binding.image)
    }



}