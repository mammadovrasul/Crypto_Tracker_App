package app.guava.cryptotracker.di

import app.guava.cryptotracker.data.api.ApiService
import app.guava.cryptotracker.data.database.dao.CryptoRatesDao
import app.guava.cryptotracker.data.repository.RatesRepositoryImpl
import app.guava.cryptotracker.domain.repository.RatesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideDiscoverRepository(
        apiService: ApiService,
        cryptoRatesDao: CryptoRatesDao,
    ): RatesRepository {
        return RatesRepositoryImpl(apiService, cryptoRatesDao)
    }
}