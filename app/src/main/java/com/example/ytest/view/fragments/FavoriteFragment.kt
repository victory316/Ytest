package com.example.ytest.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ytest.databinding.FragmentSecondBinding
import com.example.ytest.util.InjectorUtils
import com.example.ytest.view.DetailActivity
import com.example.ytest.view.FavoriteAdapter
import com.example.ytest.view.ProductAdapter
import com.example.ytest.viewmodel.MainViewModel
import timber.log.Timber

/**
 * 두번째 탭의 UI를 구성하는 FirstTabFragment
 */
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    private val mainViewModel: MainViewModel by viewModels {
        InjectorUtils.provideMainViewModel(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)

        setupUi()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setupUi() {

        val spinnerItem = arrayOf("최근등록 순", "평점 순")
        val spinnerAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            spinnerItem
        )

        binding.topSpinner.adapter = spinnerAdapter

        val layoutManager = LinearLayoutManager(requireActivity())
        val adapter = FavoriteAdapter(mainViewModel)

        binding.allList.adapter = adapter
        binding.allList.layoutManager = layoutManager

        mainViewModel.favoriteList.observe(viewLifecycleOwner) {
            Timber.tag("favoriteTest").d("submitting : $it")

            adapter.submitList(it)
        }

        mainViewModel.detailViewId.observe(viewLifecycleOwner) { clickedItemId ->
            Timber.tag("Test").d("id : $clickedItemId")

            startActivity(
                Intent(requireContext(), DetailActivity::class.java)
                    .putExtra("requestId", clickedItemId)
            )
        }
    }

    override fun onDestroy() {
        mainViewModel.cleanData()
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteFragment()
    }
}