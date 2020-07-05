package com.example.ytest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.ytest.databinding.ActivityDetailBinding
import com.example.ytest.util.InjectorUtils
import com.example.ytest.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val detailViewModel: DetailViewModel by viewModels {
        InjectorUtils.provideDetailViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setupUi(binding, intent)

        setContentView(binding.root)
    }

    private fun setupUi(binding: ActivityDetailBinding, intent: Intent) {
        detailViewModel.setRequestId(intent.getIntExtra("requestId", 0))

        binding.apply {
            viewModel = detailViewModel
            favoriteSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    detailViewModel.toggleFavorite()
                } else {
                    detailViewModel.deleteFavorite()
                }
            }
            lifecycleOwner = this@DetailActivity
        }

    }
}