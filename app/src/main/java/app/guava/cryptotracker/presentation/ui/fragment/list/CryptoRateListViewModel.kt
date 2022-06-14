package app.guava.cryptotracker.presentation.ui.fragment.list

import android.util.Log
import androidx.lifecycle.ViewModel
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
class CryptoRateListViewModel @Inject constructor(
    private val specificCryptoRangeListUseCase: SpecificCryptoRangeListUseCase
) :
    BaseViewModel() {

    init {
        getCryptoList("")
    }

    val cryptoList: SingleLiveEvent<CryptoRange> = SingleLiveEvent()


    fun getCryptoList(type: String) {
        viewModelScope.launch {

            flow {
                emit(specificCryptoRangeListUseCase.execute(type))
            }
                .flowOn(Dispatchers.IO)
                .onStart { loading(true) }
                .onCompletion { loading(false) }
                .catch { errorLiveData.value = it }
                .collect {
                    loading(false)


                    // cryptoList.value = it!!
                    Log.d("login", "login: -->>--->>>" + it!!.size)
                }

        }
    }
}