package app.guava.cryptotracker.data.repository

import app.guava.cryptotracker.data.api.ApiService
import app.guava.cryptotracker.data.database.dao.CryptoRatesDao
import app.guava.cryptotracker.data.database.models.CryptoRange
import app.guava.cryptotracker.domain.model.response.RateResponse
import app.guava.cryptotracker.domain.repository.RatesRepository
import javax.inject.Inject

class RatesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val cryptoRatesDao: CryptoRatesDao
) :
    RatesRepository {
    override suspend fun getExchangeRates(): RateResponse {
        return apiService.getExchangeRates()
    }

    override suspend fun insertCryptoRange(cryptoRange: CryptoRange) {
        return cryptoRatesDao.insert(cryptoRange)
    }

    override suspend fun getLatestCryptoRange(cryptoType: String): CryptoRange? {
        return cryptoRatesDao.getLatestCryptoRange(cryptoType)
    }

    override suspend fun getSpecificCryptoList(cryptoType: String): List<CryptoRange>? {
        return cryptoRatesDao.getSpecificCryptoList(cryptoType)
    }
}