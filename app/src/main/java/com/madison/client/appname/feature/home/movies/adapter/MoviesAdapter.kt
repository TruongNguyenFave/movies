package com.madison.client.appname.feature.home.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.madison.client.appname.data.model.Movie
import com.madison.client.appname.extention.helper.imageloader.loadImage
import com.madison.client.appname.extention.helper.viewextension.safeClick
import com.madison.client.movies.R
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(val context: Context) :
    RecyclerView.Adapter<MoviesAdapter.EntriesViewHolder>() {
    private var movies = ArrayList<Movie>()
    private var onClickListener: OnClickListener? = null

    fun injectData(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    fun injectDataWithLoadMore(entries: List<Movie>) {
        val curSize = itemCount
        this.movies.addAll(entries)
        this.notifyItemRangeInserted(curSize, itemCount)
    }

    fun clearData() {
        this.movies.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntriesViewHolder {
        return EntriesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: EntriesViewHolder, position: Int) {
        val entry = movies[position]
        holder.bindData(entry)
    }

    open inner class EntriesViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var movie: Movie? = null

        init {
            itemView.safeClick(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    movie?.let {
                        onClickListener?.clickItem(it)
                    }
                }
            })
        }

        fun bindData(movie: Movie) {
            this.movie = movie
            with(
                itemView
            ) {
                tvMovieTitle.text = movie.originalTitle
                loadImage(context, imvMovieThumbnail, movie.getFullPosterPath() ?: "")
            }
        }
    }

    fun setClickLister(clickListener: OnClickListener) {
        onClickListener = clickListener
    }

    interface OnClickListener {
        fun clickItem(movie: Movie)
    }
}
