package com.example.mystudyapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mystudyapplication.data.model.Book
import com.example.mystudyapplication.databinding.ItemBookListBinding

class BookSearchPagingAdapter : PagingDataAdapter<Book, BookSearchPagingAdapter.ViewHolder>(BookDiffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = getItem(position)
        book?.let {
            holder.bind(book)
            holder.itemView.setOnClickListener {
                onItemClickLister?.let { it(book) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBookListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    private var onItemClickLister: ((Book) -> Unit)? = null
    fun setOnItemClickListener(listener: (Book) -> Unit) {
        onItemClickLister = listener
    }

    companion object {
        private val BookDiffCallback = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book) = oldItem.isbn == newItem.isbn
            override fun areContentsTheSame(oldItem: Book, newItem: Book) = oldItem == newItem
        }
    }

    class ViewHolder(
        private val binding: ItemBookListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            val author = book.authors.toString().removeSurrounding("[","]")
            val publisher = book.publisher
            val date = if (book.datetime.isNotEmpty()) book.datetime.substring(0, 10) else ""

            itemView.apply {
                binding.ivArticle.load(book.thumbnail)
                binding.tvTitle.text = book.title
                binding.tvAuthor.text = "$author | $publisher"
                binding.tvDate.text = date
            }
        }
    }
}