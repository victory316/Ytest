package com.example.ytest.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ytest.databinding.FragmentFirstTabBinding
import com.example.ytest.util.Constants
import com.example.ytest.util.Constants.REQUEST_ID
import com.example.ytest.util.InjectorUtils
import com.example.ytest.view.DetailActivity
import com.example.ytest.view.ProductAdapter
import com.example.ytest.viewmodel.MainViewModel
import timber.log.Timber

/**
 * 첫번째 탭의 UI를 구성하는 FirstTabFragment
 */
class ProductFragment : Fragment() {
    private lateinit var binding: FragmentFirstTabBinding

    private val mainViewModel: MainViewModel by viewModels {
        InjectorUtils.provideMainViewModel(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstTabBinding.inflate(inflater, container, false)

        setupViewModel(binding)
        setupUi()

        return binding.root
    }

    private fun setupViewModel(binding: FragmentFirstTabBinding) {
        binding.apply {
            viewModel = mainViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }


    private fun setupUi() {
        val layoutManager = LinearLayoutManager(requireActivity())
        val adapter = ProductAdapter(mainViewModel)

        binding.allList.adapter = adapter
        binding.allList.layoutManager = layoutManager

        mainViewModel.getPagedList().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        mainViewModel.detailViewId.observe(viewLifecycleOwner) { clickedItemId ->

            startActivity(
                Intent(requireContext(), DetailActivity::class.java)
                    .putExtra(REQUEST_ID, clickedItemId)
            )
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProductFragment()
    }
}