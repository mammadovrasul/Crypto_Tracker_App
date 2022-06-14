package app.guava.cryptotracker.domain.useCase

import app.guava.cryptotracker.domain.repository.RatesRepository
import javax.inject.Inject

class LastSetRangeUseCase @Inject constructor(private val repository: RatesRepository) {
    suspend fun execute(cryptoType: String) = repository.getLatestCryptoRange(cryptoType)
}