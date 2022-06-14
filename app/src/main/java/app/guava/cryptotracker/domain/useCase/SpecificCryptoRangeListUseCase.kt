package app.guava.cryptotracker.domain.useCase

import app.guava.cryptotracker.domain.repository.RatesRepository
import javax.inject.Inject

class SpecificCryptoRangeListUseCase @Inject constructor(private val repository: RatesRepository) {
    suspend fun execute(cryptoType: String) = repository.getSpecificCryptoList(cryptoType)
}