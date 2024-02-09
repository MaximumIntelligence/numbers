package com.mornhouse.numbers.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.mornhouse.numbers.R
import com.mornhouse.numbers.databinding.HomeFragmentBinding
import com.mornhouse.numbers.domain.model.NumberFactModel
import com.mornhouse.numbers.presentation.common.NumberClickListener
import com.mornhouse.numbers.presentation.model.Status
import com.mornhouse.numbers.presentation.ui.description.DescriptionFragment
import com.mornhouse.numbers.presentation.ui.home.adapter.HistoryAdapter
import com.mornhouse.numbers.presentation.ui.home.viewmodel.HomeViewModel
import com.mornhouse.numbers.presentation.util.collectWithLifecycle
import com.mornhouse.numbers.presentation.util.hideKeyboard
import com.mornhouse.numbers.presentation.util.viewLifecycleScoped
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), NumberClickListener {
    private var binding: HomeFragmentBinding by viewLifecycleScoped()
    private val viewModel: HomeViewModel by viewModels()
    private var adapter: HistoryAdapter by viewLifecycleScoped()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        setupAdapter()
        collectData()
    }

    override fun onNumberClicked(model: NumberFactModel) {
        openNumberDescriptionFragment(model)
    }

    private fun initUI() {
        with(binding) {
            btnGetFact.setOnClickListener {
                onGetFact()
            }

            btnGetFactRandom.setOnClickListener {
                onGetFactRandom()
            }

            etNumberInput.addTextChangedListener {
                binding.btnGetFact.isEnabled = !it.isNullOrBlank()
            }
        }
    }

    private fun setupAdapter() {
        adapter = HistoryAdapter(numberClickListener = this)
        binding.rvRequestsHistory.adapter = adapter
    }

    private fun collectData() {
        viewModel.fetchNumberFactStatusFlow.collectWithLifecycle(
            viewLifecycleOwner,
            collector = ::handleGetNumberFactStatus,
        )

        viewModel.observeNumbersFlow.collectWithLifecycle(viewLifecycleOwner) { numberFactModelList ->
            if (numberFactModelList != null) {
                adapter.setList(numberFactModelList)
            } else {
                adapter.setList(emptyList())
            }
        }
    }

    private fun handleGetNumberFactStatus(status: Status) {
        when (status) {
            Status.Loading -> showLoading()
            Status.Success -> {
                hideLoading()
                terminateWorkWithEditText()
                binding.rvRequestsHistory.scrollToPosition(0)
            }

            is Status.Failure -> {
                hideLoading()
                terminateWorkWithEditText()
                Toast.makeText(requireContext(), status.error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onGetFact() {
        val number = binding.etNumberInput.text.toString()
        val finalNumber = Integer.parseInt(number).toLong()
        viewModel.fetchFactAboutNumber(finalNumber)
    }

    private fun onGetFactRandom() {
        viewModel.fetchFactAboutRandomNumber()
    }

    private fun openNumberDescriptionFragment(model: NumberFactModel) {
        parentFragmentManager.commit {
            val bundle = Bundle().apply {
                putString(DESCRIPTION_KEY, model.text)
                putString(NUMBER_KEY, model.number.toString())
            }
            replace<DescriptionFragment>(R.id.fcvHome, args = bundle)
            setReorderingAllowed(true)
            addToBackStack("DescriptionFragment")
        }
    }

    private fun terminateWorkWithEditText() {
        binding.etNumberInput.apply {
            text.clear()
            clearFocus()
            hideKeyboard()
        }
    }

    private fun showLoading() {
        with(binding) {
            pbLoading.isVisible = true
            btnGetFact.isEnabled = false
            btnGetFactRandom.isEnabled = false
        }
    }

    private fun hideLoading() {
        with(binding) {
            pbLoading.isVisible = false
            btnGetFact.isEnabled = false
            btnGetFactRandom.isEnabled = true
        }
    }

    companion object {
        const val DESCRIPTION_KEY = "description"
        const val NUMBER_KEY = "number"
    }
}
