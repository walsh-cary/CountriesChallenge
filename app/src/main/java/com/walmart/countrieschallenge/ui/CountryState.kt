package com.walmart.countrieschallenge.ui

import com.walmart.countrieschallenge.model.Countries

data class CountryState(
    val loading: Boolean = false,
    val countries: Countries = emptyList()
)
