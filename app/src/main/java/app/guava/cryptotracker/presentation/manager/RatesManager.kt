package app.guava.cryptotracker.presentation.manager

import app.guava.cryptotracker.domain.model.response.CryptoDetail
import kotlinx.coroutines.flow.MutableSharedFlow

object RatesManager {
    val sharadFlow = MutableSharedFlow<CryptoDetail>(1)
}