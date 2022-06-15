package app.guava.cryptotracker.presentation.ui.fragment.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import app.guava.cryptotracker.R
import app.guava.cryptotracker.databinding.FragmentCryptoRatesListBinding
import app.guava.cryptotracker.domain.model.other.Rate
import app.guava.cryptotracker.presentation.adapter.RatesAdapter
import app.guava.cryptotracker.presentation.base.BaseFragment
import app.guava.cryptotracker.presentation.interfaces.CoinItemClickListener
import app.guava.cryptotracker.presentation.interfaces.SetCoinValueClickListener
import app.guava.cryptotracker.presentation.ui.dialog.DialogSetRange
import app.guava.cryptotracker.presentation.util.ServiceUtil
import app.guava.cryptotracker.presentation.util.safeNavigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoRateListFragment :
    BaseFragment<FragmentCryptoRatesListBinding, CryptoRateListViewModel>(), CoinItemClickListener,
    SetCoinValueClickListener {

    private var adapter: RatesAdapter? = null
    private lateinit var binding: FragmentCryptoRatesListBinding
    private lateinit var viewModel: CryptoRateListViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding!!

        ServiceUtil.startCheckRateService(requireContext())
        viewModel.cryptoList.observe(viewLifecycleOwner) {
            initRecyclerView(it)
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_crypto_rates_list
    }

    override fun getViewModel(): CryptoRateListViewModel {
        viewModel = ViewModelProvider(this)[CryptoRateListViewModel::class.java]
        return viewModel
    }

    private fun initRecyclerView(rate: ArrayList<Rate>) {
        val layoutManager = LinearLayoutManager(context)
        adapter = RatesAdapter(rate, this, this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    override fun getCoinHistoryInfoClickListener(rate: Rate) {
        safeNavigate(
            CryptoRateListFragmentDirections.actionNavigationHomeToNavigationRateHistory(
                rate.cryptoName
            )
        )

    }

    override fun onSetCoinRangeValue(coinName: String) {
        val dialog = DialogSetRange(coinName) {
            viewModel.saveToLocalRange(it)
        }
        dialog.show(childFragmentManager, "dialog")
    }


}