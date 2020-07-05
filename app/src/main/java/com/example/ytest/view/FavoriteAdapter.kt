package com.example.ytest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ytest.data.local.Favorite
import com.example.ytest.databinding.FavoriteDataItemBinding
import com.example.ytest.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

class FavoriteAdapter(private val answersViewModel: MainViewModel) :
    ListAdapter<Favorite, RecyclerView.ViewHolder>(AccountDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = FavoriteDataItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        binding.viewModel = answersViewModel

        return ResultViewHolder(
            binding, answersViewModel
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as ResultViewHolder).bind(plant)
    }

    class ResultViewHolder(
        private val binding: FavoriteDataItemBinding,
        private val answersViewModel : MainViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        fun formatTimeStamp(timestamp: Long) : String{
            val tempCalendar = Calendar.getInstance()
            tempCalendar.timeInMillis = timestamp
            val dateFormat = SimpleDateFormat("YYYY-MM-dd hh:mm")

            return dateFormat.format(tempCalendar.time)
        }

        fun bind(item: Favorite) {

            binding.apply {
                viewModel = answersViewModel
                imageUrl = item.thumbnail
                primaryText = item.name
                secondaryText = item.description.price.toString()
                scoreText = item.rate.toString()
                addedTimeString = formatTimeStamp(item.savedTime)
                favoriteSwitch.isChecked = true

                // TODO 스위치 선택시 데이터 삭제 및 데이터 컬럼 업데이트
                favoriteSwitch.setOnCheckedChangeListener { _, _ ->
//                    answersViewModel.toggleFavorite(item)
                    answersViewModel.deleteFavorite(item.id)
                }

                root.setOnClickListener {
                    answersViewModel.showDetailView(item.id)
                }

                executePendingBindings()
            }
        }

    }



    private class AccountDiffCallback : DiffUtil.ItemCallback<Favorite>() {
        override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem == newItem
        }
    }
}