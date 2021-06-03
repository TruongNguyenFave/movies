package com.madison.client.movies.feature.home.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madison.client.movies.data.model.Movie
import com.madison.client.movies.databinding.ItemMovieBinding

class MovieAdapter(private val onMovieClickListener: (Movie) -> Unit) :
    ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffUtil()) {
    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.getInstance(parent, onMovieClickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bindData(movie)
    }

    class MovieViewHolder private constructor(
        private val binding: ItemMovieBinding,
        val onMovieClickListener: (Movie) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private var movie: Movie? = null

        companion object {
            fun getInstance(
                parent: ViewGroup,
                onMovieClickListener: (Movie) -> Unit
            ): MovieViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemMovieBinding.inflate(inflater, parent, false)
                return MovieViewHolder(
                    binding,
                    onMovieClickListener
                )
            }
        }

        fun bindData(movie: Movie) {
            this.movie = movie
            binding.movie = movie
            binding.viewHolder = this@MovieViewHolder
            binding.executePendingBindings()
        }
    }

    class MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

    fun setClickLister(clickListener: OnClickListener) {
        onClickListener = clickListener
    }

    interface OnClickListener {
        fun clickItem(movie: Movie)
    }
}
