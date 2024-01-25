package com.walmart.countrieschallenge.ui

import android.accounts.NetworkErrorException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.walmart.countrieschallenge.domain.GetCountriesUseCase
import com.walmart.countrieschallenge.domain.getCountriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryViewModel(
    getCountries: GetCountriesUseCase,
): ViewModel() {
    private val _state: MutableStateFlow<CountryState> by lazy { MutableStateFlow(CountryState()) }
    val state = _state.asStateFlow()

    private fun setState(reduce: CountryState.() -> CountryState) {
        _state.value = state.value.reduce()
    }

    init {
        viewModelScope.launch {
            setState {
                copy(loading = true)
            }
            getCountries().run {
                onSuccess { setState { copy(loading = false, countries = it) } }
                onFailure {
                    setState { copy(loading = false) }
                    throw NetworkErrorException("Error retrieving countries: ${it.message}")
                }
            }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory by lazy {
            viewModelFactory {
                initializer {
                    CountryViewModel(getCountries = getCountriesUseCase())
                }
            }
        }
    }
}