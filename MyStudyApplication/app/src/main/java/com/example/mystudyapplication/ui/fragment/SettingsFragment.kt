package com.example.mystudyapplication.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.work.WorkInfo
import com.example.mystudyapplication.R
import com.example.mystudyapplication.databinding.FragmentSettingsBinding
import com.example.mystudyapplication.ui.viewmodel.SearchViewModel
import com.example.mystudyapplication.ui.viewmodel.SettingsViewModel
import com.example.mystudyapplication.util.Sort
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment
    : BaseFragment<FragmentSettingsBinding>({ FragmentSettingsBinding.inflate(it)}) {
    private val settingsViewModel by viewModels<SettingsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveSettings()
        loadSettings()
        showWorkStatus()
    }

    private fun saveSettings() {
        binding.rgSort.setOnCheckedChangeListener { _, checkedId ->
            val value = when (checkedId) {
                R.id.rb_accuracy -> Sort.ACCURACY.value
                R.id.rb_latest -> Sort.LATEST.value
                else -> return@setOnCheckedChangeListener
            }

            settingsViewModel.saveSortMode(value)
        }

        binding.swCacheDelete.setOnCheckedChangeListener { _, isChecked ->
            Log.d("hyun", "is checked = $isChecked")
            if (isChecked) {
                settingsViewModel.setWorker()
            } else {
                settingsViewModel.deleteWork()
            }
        }
    }

    private fun loadSettings() {
        lifecycleScope.launch {
            val buttonId = when (settingsViewModel.getSortMode()) {
                Sort.ACCURACY.value -> R.id.rb_accuracy
                Sort.LATEST.value -> R.id.rb_latest
                else -> return@launch
            }
            binding.rgSort.check(buttonId)
        }
    }

    private fun showWorkStatus() {
        settingsViewModel.getWorkStatus().observe(viewLifecycleOwner) { workInfo ->
            if (workInfo.isEmpty()) {
                binding.tvDeleteWorkStatus.text = "No Works"
            } else {
                if (binding.tvDeleteWorkStatus.text.equals("Status") && workInfo[0].state == WorkInfo.State.ENQUEUED) {
                    // textView의 초기 text인 Status로 초기 값인지 체크
                    // 초기 값인데 작업이 있는 경우 스위치 체크 변경
                    binding.swCacheDelete.isChecked = true
                }

                binding.tvDeleteWorkStatus.text = workInfo[0].state.toString()

                Log.d("hyun", workInfo[0].state.toString())
            }
        }
    }
}