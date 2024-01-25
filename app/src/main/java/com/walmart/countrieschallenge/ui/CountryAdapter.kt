package com.walmart.countrieschallenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.walmart.countrieschallenge.databinding.CountryItemBinding
import com.walmart.countrieschallenge.model.Countries

class CountryAdapter(
    var countryList: Countries,
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    inner class CountryViewHolder(val binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CountryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        with(holder) {
            with(countryList[position]) {
                binding.countryNameRegion.text = "${this.name}, ${this.region}"
                binding.countryCapital.text = this.capital
                binding.countryCode.text = this.code
            }
        }
    }
}