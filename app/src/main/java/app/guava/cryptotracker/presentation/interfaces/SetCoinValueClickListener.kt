package app.guava.cryptotracker.presentation.interfaces

import app.guava.cryptotracker.data.database.models.CryptoRange

/**
 * Created by rasulmammadov on 15,June,2022
 */
interface SetCoinValueClickListener {
    fun onSetCoinRangeValue(coinName: String)
}