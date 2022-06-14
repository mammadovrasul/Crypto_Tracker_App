package app.guava.cryptotracker.domain.useCase

import app.guava.cryptotracker.domain.repository.RatesRepository
import javax.inject.Inject

class ExchangeRatesUseCase @Inject constructor(private val repository: RatesRepository) {
    suspend fun execute() = repository.getExchangeRates().rates
}