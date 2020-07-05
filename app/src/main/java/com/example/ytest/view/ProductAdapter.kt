package com.example.ytest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ytest.data.local.Product
import com.example.ytest.databinding.BasicDataItemBinding
import com.example.ytest.viewmodel.MainViewModel

class ProductAdapter(private val answersViewModel: MainViewModel) :
    PagedListAdapter<Product, RecyclerView.ViewHolder>(AccountDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = BasicDataItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        binding.viewModel = answersViewModel

        return ResultViewHolder(
            binding, answersViewModel
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as ResultViewHolder).bind(it)
        }
    }

    class ResultViewHolder(
        private val binding: BasicDataItemBinding,
        private val answersViewModel : MainViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {

            binding.apply {
                viewModel = answersViewModel
                imageUrl = item.thumbnail
                primaryText = item.name
                secondaryText = item.description.price.toString()
                scoreText = item.rate.toString()
                isFavorite = item.favoriteStatus

                favoriteSwitch.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        answersViewModel.toggleFavorite(item)
                    } else {
                        answersViewModel.deleteFavorite(item.id)
                    }
                }

                root.setOnClickListener {
                    answersViewModel.showDetailView(item.id)
                }

                executePendingBindings()
            }
        }
    }

    private class AccountDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}