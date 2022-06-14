package app.guava.cryptotracker.presentation.util

import android.content.Context
import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.io.IOException


class ErrorHandler(private val context: Context) {
    var errorMessage: String? = null

    @Throws(IOException::class)
    fun handleError(throwable: Throwable): String {
        throwable.printStackTrace()

        when (throwable) {
            is HttpException -> {
                Log.d("handleError", "handleError: ${throwable.code()}")

                val json = throwable.response()?.errorBody()!!.string()
                val error = convert(json)

                error?.message?.let {

                    return it
                }
            }
        }


        Log.d("errorHandler", "handleError1: " + throwable.message.toString())

        return errorMessage!!
    }

    private fun convert(json: String): Error? {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<Error> =
            moshi.adapter(Error::class.java)

        return jsonAdapter.fromJson(json)
    }

}