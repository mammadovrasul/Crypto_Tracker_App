package app.guava.cryptotracker.di

import android.content.Context
import androidx.room.Room
import app.guava.cryptotracker.data.database.db.CryptoRatesDatabase
import app.guava.cryptotracker.data.database.dao.CryptoRatesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun providesUserDao(dailyDatabase: CryptoRatesDatabase): CryptoRatesDao = dailyDatabase.cryptoRatesDao()

    @Provides
    @Singleton
    fun providesUserDatabase(@ApplicationContext context: Context): CryptoRatesDatabase =
        Room.databaseBuilder(context, CryptoRatesDatabase::class.java, "CryptoRatesDB")
            .fallbackToDestructiveMigration()
            .build()
}