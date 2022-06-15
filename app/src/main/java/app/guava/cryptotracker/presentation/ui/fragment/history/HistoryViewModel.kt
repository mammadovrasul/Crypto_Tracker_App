package app.guava.cryptotracker.presentation.ui.fragment.history

import androidx.lifecycle.viewModelScope
import app.guava.cryptotracker.data.database.models.CryptoRange
import app.guava.cryptotracker.domain.useCase.SpecificCryptoRangeListUseCase
import app.guava.cryptotracker.presentation.base.BaseViewModel
import app.guava.cryptotracker.presentation.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val specificCryptoRangeListUseCase: SpecificCryptoRangeListUseCase
) :
    BaseViewModel() {

    val rangeHistory: SingleLiveEvent<List<CryptoRange>?> = SingleLiveEvent()


    fun getCryptoData(type: String) {
        viewModelScope.launch {
            flow {
                emit(specificCryptoRangeListUseCase.execute(type))
            }.flowOn(Dispatchers.IO)
                .collect {
                    rangeHistory.value = it
                }
        }
    }
}