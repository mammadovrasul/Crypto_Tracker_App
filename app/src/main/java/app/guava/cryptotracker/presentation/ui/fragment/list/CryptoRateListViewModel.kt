package app.guava.cryptotracker.presentation.ui.fragment.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.guava.cryptotracker.data.database.models.CryptoRange
import app.guava.cryptotracker.domain.model.enums.CryptoType
import app.guava.cryptotracker.domain.model.other.Rate
import app.guava.cryptotracker.domain.model.response.CryptoDetail
import app.guava.cryptotracker.domain.useCase.CryptoRateUseCase
import app.guava.cryptotracker.domain.useCase.SpecificCryptoRangeListUseCase
import app.guava.cryptotracker.presentation.adapter.RatesAdapter
import app.guava.cryptotracker.presentation.base.BaseViewModel
import app.guava.cryptotracker.presentation.manager.RatesManager
import app.guava.cryptotracker.presentation.util.SingleLiveEvent
import app.guava.cryptotracker.presentation.util.Utils.roundFloatTwoDecimal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoRateListViewModel @Inject constructor(
    private val cryptoRateUseCase: CryptoRateUseCase
) :
    BaseViewModel() {

    val cryptoList: SingleLiveEvent<ArrayList<Rate>> = SingleLiveEvent()
    val rate: SingleLiveEvent<CryptoRange> = SingleLiveEvent()

    init {
        fetchRates()

    }

    private fun fetchRates() {

        viewModelScope.launch {
            RatesManager.sharadFlow.collectLatest {
                val rates = ArrayList<Rate>()
                rates.add(Rate(CryptoType.BTC.name, it.btc.value.roundFloatTwoDecimal()))
                rates.add(Rate(CryptoType.ETH.name, it.eth.value.roundFloatTwoDecimal()))
                rates.add(Rate(CryptoType.XRP.name, it.xrp.value.roundFloatTwoDecimal()))

                cryptoList.value = rates
            }
        }
    }

    fun saveToLocalRange(cryptoRange: CryptoRange) {
        Log.d(CryptoRateListViewModel::class.java.name, "saveToLocalRange: -->>--->> ")
        viewModelScope.launch {
            cryptoRateUseCase.execute(cryptoRange)
        }
    }

}