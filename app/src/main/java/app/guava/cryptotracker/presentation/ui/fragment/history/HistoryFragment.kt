package app.guava.cryptotracker.presentation.ui.fragment.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.guava.cryptotracker.R
import app.guava.cryptotracker.data.database.models.CryptoRange
import app.guava.cryptotracker.databinding.FragmentRatesRangeHistoryBinding
import app.guava.cryptotracker.domain.model.other.Rate
import app.guava.cryptotracker.presentation.adapter.RatesAdapter
import app.guava.cryptotracker.presentation.adapter.RatesHistoryAdapter
import app.guava.cryptotracker.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentRatesRangeHistoryBinding, HistoryViewModel>() {

    private lateinit var binding: FragmentRatesRangeHistoryBinding
    private lateinit var viewModel: HistoryViewModel
    private lateinit var cryptoName: String
    private var adapter: RatesHistoryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding!!
        binding.backArrow.setOnClickListener { findNavController().popBackStack() }

        arguments?.let {
            val args = HistoryFragmentArgs.fromBundle(requireArguments())
            cryptoName = args.type
            binding.coinHistoryTitle.text = cryptoName
            viewModel.getCryptoData(cryptoName)
        }


        viewModel.rangeHistory.observe(viewLifecycleOwner) {
            if (it != null && !it.isEmpty()) {
                initRecyclerView(it)
            } else {
                binding.emptyHistory.visibility = View.VISIBLE
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_rates_range_history
    }

    override fun getViewModel(): HistoryViewModel {
        viewModel = ViewModelProvider(this)[HistoryViewModel::class.java]
        return viewModel
    }

    private fun initRecyclerView(coinRange: List<CryptoRange>?) {
        binding.recyclerView.visibility = View.VISIBLE
        val layoutManager = LinearLayoutManager(context)
        adapter = RatesHistoryAdapter(coinRange!!)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

}