package app.guava.cryptotracker.data.api

import app.guava.cryptotracker.data.api.Endpoints.EX_RATES
import app.guava.cryptotracker.domain.model.response.RateResponse
import retrofit2.http.GET

/**
 * Created by rasulmammadov on 14,June,2022
 */
interface ApiService {

    @GET(EX_RATES)
    suspend fun getExchangeRates(): RateResponse

}