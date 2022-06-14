package app.guava.cryptotracker.presentation.ui.fragment.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.guava.cryptotracker.R
import app.guava.cryptotracker.databinding.FragmentRatesRangeHistoryBinding
import app.guava.cryptotracker.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentRatesRangeHistoryBinding, HistoryViewModel>() {


    private lateinit var binding: FragmentRatesRangeHistoryBinding
    private lateinit var viewModel: HistoryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding!!


//        viewModel.login.observe(viewLifecycleOwner) {
//            if (it != null) {
//                findNavController().navigate(
//                    R.id.action_merchantLoginFragment_to_cashierFragment,
//                    null
//                )
//            }
//
//        }



//
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_rates_range_history
    }

    override fun getViewModel(): HistoryViewModel {
        viewModel = ViewModelProvider(this)[HistoryViewModel::class.java]
        return viewModel
    }

}