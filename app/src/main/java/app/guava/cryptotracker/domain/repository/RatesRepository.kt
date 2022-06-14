package app.guava.cryptotracker.domain.repository

import app.guava.cryptotracker.data.database.models.CryptoRange
import app.guava.cryptotracker.domain.model.response.RateResponse

interface RatesRepository {
    suspend fun getExchangeRates(): RateResponse
    suspend fun insertCryptoRange(cryptoRange: CryptoRange)
    suspend fun getLatestCryptoRange(cryptoType: String): CryptoRange?
    suspend fun getSpecificCryptoList(cryptoType: String): List<CryptoRange>?
}