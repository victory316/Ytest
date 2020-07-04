package com.example.ytest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ytest.data.local.Product
import com.example.ytest.databinding.BasicDataItemBinding
import com.example.ytest.viewmodel.MainViewModel

class ProductAdapter(private val answersViewModel: MainViewModel) :
    ListAdapter<Product, RecyclerView.ViewHolder>(AccountDiffCallback()) {

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
        val plant = getItem(position)
        (holder as ResultViewHolder).bind(plant)
    }

    class ResultViewHolder(
        private val binding: BasicDataItemBinding,
        private val answersViewModel : MainViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
//            val questionNumberString = "${item.id}번"
//
//            binding.apply {
//                questionNumber = item.id
//                questionNumberView.text = questionNumberString
//                imageUrl = item.problemUrl
//                isSelectable = item.isSelectable
//                viewModel = answersViewModel
//
//                answerEditText.addTextChangedListener {
//                    if (it.toString().isNotEmpty()) {
//                        answersViewModel.addAnswers(item.id, it.toString().toInt())
//                    }
//                }
//
//                answersViewModel.getLifecycleOwner().let{
//                    answersViewModel.showQuestion.observe(it, Observer { visibility ->
//                        questionVisibility = visibility
//                    })
//                }
//
//                executePendingBindings()
//            }
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