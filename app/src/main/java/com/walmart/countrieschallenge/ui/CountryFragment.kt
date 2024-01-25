package com.walmart.countrieschallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.walmart.countrieschallenge.databinding.CountryFragmentBinding
import kotlinx.coroutines.launch

class CountryFragment(): Fragment() {
    private var _binding: CountryFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CountryAdapter

    private val viewModel by viewModels<CountryViewModel> {
        CountryViewModel.factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CountryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
        binding.countryRecyclerView.layoutManager = layoutManager
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    if (it.loading) { binding.loadingSpinner.visibility = View.VISIBLE }
                    else if (it.countries.isNotEmpty()) {
                        binding.loadingSpinner.visibility = View.GONE
                        adapter = CountryAdapter(it.countries)
                        binding.countryRecyclerView.adapter = adapter
                        binding.countryRecyclerView.addItemDecoration(
                            DividerItemDecoration(
                                context,
                                LinearLayoutManager.VERTICAL
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}