package br.com.fortestecnologia.infinitySagaMarvel.ui.films


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fortestecnologia.infinitySagaMarvel.data.entities.FilmEntity
import br.com.fortestecnologia.infinitySagaMarvel.databinding.ItemFilmBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class FilmsAdapter(private val listener: FilmItemListener) : RecyclerView.Adapter<FilmViewHolder>() {

    interface FilmItemListener {
        fun onClickedFilm(filmId: Int)
    }

    private val items = ArrayList<FilmEntity>()

    fun setItems(items: ArrayList<FilmEntity>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding: ItemFilmBinding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) = holder.bind(items[position])
}

class FilmViewHolder(private val itemBinding: ItemFilmBinding, private val listener: FilmsAdapter.FilmItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var film: FilmEntity

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: FilmEntity) {
        this.film = item
        itemBinding.title.text = item.title
        itemBinding.year.text = """${item.year} - ${item.director}"""
        Glide.with(itemBinding.root)
            .load(item.poster)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
        listener.onClickedFilm(film.id)
    }
}

