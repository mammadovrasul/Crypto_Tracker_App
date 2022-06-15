package app.guava.cryptotracker.presentation.interfaces

import app.guava.cryptotracker.domain.model.other.Rate

/**
 * Created by rasulmammadov on 15,June,2022
 */
interface CoinItemClickListener {
    fun getCoinHistoryInfoClickListener(rate: Rate)
}