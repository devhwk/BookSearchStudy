package com.example.mystudyapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/*
    기본적인 binding처리를 중복하지 않기위한 base fragment

    BaseFragment의 onCreateView에서 binding을 처리했기 때문에
    onCreateView에서 추가 작업할 것이 있는게 아니라면 진행하지 않음.
    onDestroyView도 마찬가지.
 */
open class BaseFragment<B: ViewBinding>(val factory: (LayoutInflater) -> B)
    : Fragment() {
    private var _binding: B? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = factory(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}