package app.guava.cryptotracker.domain.useCase

import app.guava.cryptotracker.data.database.models.CryptoRange
import app.guava.cryptotracker.domain.repository.RatesRepository
import javax.inject.Inject

class CryptoRateUseCase @Inject constructor(private val repository: RatesRepository) {
    suspend fun execute(cryptoRange: CryptoRange) = repository.insertCryptoRange(cryptoRange)
}