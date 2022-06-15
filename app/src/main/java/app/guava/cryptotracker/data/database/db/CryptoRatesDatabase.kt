package app.guava.cryptotracker.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import app.guava.cryptotracker.data.database.dao.CryptoRatesDao
import app.guava.cryptotracker.data.database.models.CryptoRange

@Database(
    entities = [CryptoRange::class],
    version = 1,
    exportSchema = false
)
abstract class CryptoRatesDatabase : RoomDatabase() {
    abstract fun cryptoRatesDao(): CryptoRatesDao
}
