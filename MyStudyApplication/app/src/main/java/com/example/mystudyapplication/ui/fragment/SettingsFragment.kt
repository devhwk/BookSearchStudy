package com.example.mystudyapplication.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.mystudyapplication.R
import com.example.mystudyapplication.databinding.FragmentSettingsBinding
import com.example.mystudyapplication.ui.activity.MainActivity
import com.example.mystudyapplication.ui.viewmodel.BookSearchViewModel
import com.example.mystudyapplication.util.Sort
import kotlinx.coroutines.launch

class SettingsFragment
    : BaseFragment<FragmentSettingsBinding>({ FragmentSettingsBinding.inflate(it)}) {

    private lateinit var bookSearchViewModel: BookSearchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookSearchViewModel = (activity as MainActivity).bookSearchViewModel

        saveSettings()
        loadSettings()
    }

    private fun saveSettings() {
        binding.rgSort.setOnCheckedChangeListener { _, checkedId ->
            val value = when (checkedId) {
                R.id.rb_accuracy -> Sort.ACCURACY.value
                R.id.rb_latest -> Sort.LATEST.value
                else -> return@setOnCheckedChangeListener
            }

            bookSearchViewModel.saveSortMode(value)
        }
    }

    private fun loadSettings() {
        lifecycleScope.launch {
            val buttonId = when (bookSearchViewModel.getSortMode()) {
                Sort.ACCURACY.value -> R.id.rb_accuracy
                Sort.LATEST.value -> R.id.rb_latest
                else -> return@launch
            }
            binding.rgSort.check(buttonId)
        }
    }
}