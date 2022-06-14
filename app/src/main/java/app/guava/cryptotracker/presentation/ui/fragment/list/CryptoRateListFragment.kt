package app.guava.cryptotracker.presentation.ui.fragment.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import app.guava.cryptotracker.R
import app.guava.cryptotracker.databinding.FragmentCryptoRatesListBinding
import app.guava.cryptotracker.presentation.adapter.RatesAdapter
import app.guava.cryptotracker.presentation.base.BaseFragment
import app.guava.cryptotracker.presentation.util.ServiceUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoRateListFragment : BaseFragment<FragmentCryptoRatesListBinding, CryptoRateListViewModel>() {

    private var adapter: RatesAdapter? = null
    private lateinit var binding: FragmentCryptoRatesListBinding
    private lateinit var viewModel: CryptoRateListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding!!
        initRecyclerView()

        ServiceUtil.startCheckRateService(requireContext())


        viewModel.cryptoList.observe(viewLifecycleOwner){

        }

        Log.d(CryptoRateListFragment::class.java.name, "onViewCreated: Russle")


    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_crypto_rates_list
    }

    override fun getViewModel(): CryptoRateListViewModel {
        viewModel = ViewModelProvider(this)[CryptoRateListViewModel::class.java]
        return viewModel
    }

    private fun initRecyclerView() {
        if (adapter == null) {
            adapter = RatesAdapter()
            viewDataBinding!!.recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = this@CryptoRateListFragment.adapter
            }
        }
    }

}