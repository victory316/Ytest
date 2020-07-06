package com.example.ytest.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ytest.R
import com.example.ytest.databinding.FragmentSecondBinding
import com.example.ytest.util.Constants.REQUEST_ID
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

    private var currentSpinner = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)

        setupUi()
        return binding.root
    }

    private fun setupUi() {

        val layoutManager = LinearLayoutManager(requireActivity())
        val adapter = FavoriteAdapter(mainViewModel)

        binding.allList.adapter = adapter
        binding.allList.layoutManager = layoutManager

        mainViewModel.favoriteList.observe(viewLifecycleOwner) { favoriteList ->
            Timber.tag("favoriteTest").d("submitting : $favoriteList")

            if (currentSpinner == 0) {
                favoriteList.sortedBy { favorite -> favorite.savedTime }.let {
                    adapter.submitList(it.asReversed())
                }
            } else {
                favoriteList.sortedBy { favorite -> favorite.rate }
                    .let {
                        adapter.submitList(it.asReversed())
                    }
            }
        }

        mainViewModel.detailViewId.observe(viewLifecycleOwner) { clickedItemId ->

            startActivity(
                Intent(requireContext(), DetailActivity::class.java)
                    .putExtra(REQUEST_ID, clickedItemId)
            )
        }

        val spinnerItem = arrayOf(
            resources.getString(R.string.sort_by_latest_string),
            resources.getString(R.string.sort_by_rate)
        )

        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            spinnerItem
        )

        binding.topSpinner.adapter = spinnerAdapter

        binding.topSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                currentSpinner = position

                when (position) {
                    0 -> {
                        adapter.currentList.sortedBy { favorite -> favorite.savedTime }.let {
                            adapter.submitList(it.asReversed())
                        }
                    }
                    1 -> {
                        adapter.currentList.sortedBy { favorite -> favorite.rate }
                            .let {
                                adapter.submitList(it.asReversed())
                            }
                    }
                }
            }

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