package com.example.ytest.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.ytest.R
import com.example.ytest.databinding.FragmentFirstTabBinding
import com.example.ytest.util.InjectorUtils
import com.example.ytest.viewmodel.MainViewModel
import timber.log.Timber

/**
 * 첫번째 탭의 UI를 구성하는 FirstTabFragment
 */
class FirstTabFragment : Fragment() {
    private lateinit var binding: FragmentFirstTabBinding

    private val mainViewModel: MainViewModel by viewModels {
        InjectorUtils.provideMainViewModel(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstTabBinding.inflate(inflater, container, false)

        setupViewModel(binding)
        setupUi()

        Timber.tag("queryTest").d("onCreateView!")


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        mainViewModel.testQuery()

        super.onCreate(savedInstanceState)
    }

    private fun setupViewModel(binding: FragmentFirstTabBinding) {
        binding.apply {
            viewModel = mainViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }


    private fun setupUi() {
        mainViewModel.queryList.observe(viewLifecycleOwner) {

        }
    }

//    private fun subscribeUi(adapter) {
//
//    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FirstTabFragment()
    }
}