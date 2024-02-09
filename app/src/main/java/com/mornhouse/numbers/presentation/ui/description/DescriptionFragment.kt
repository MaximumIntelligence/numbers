package com.mornhouse.numbers.presentation.ui.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mornhouse.numbers.databinding.DescriptionFragmentBinding
import com.mornhouse.numbers.presentation.ui.home.HomeFragment
import com.mornhouse.numbers.presentation.util.viewLifecycleScoped

class DescriptionFragment : Fragment() {
    private var binding: DescriptionFragmentBinding by viewLifecycleScoped()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DescriptionFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        with(binding) {
            tvNumber.text = arguments?.getString(HomeFragment.NUMBER_KEY)
            tvDescription.text = arguments?.getString(HomeFragment.DESCRIPTION_KEY)
            ibBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun onBackPressed() {
        parentFragmentManager.popBackStack()
    }
}