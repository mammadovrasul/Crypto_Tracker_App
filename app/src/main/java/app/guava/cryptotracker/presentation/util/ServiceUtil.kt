package app.guava.cryptotracker.presentation.util

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import app.guava.cryptotracker.presentation.services.CheckCoinRateService

object ServiceUtil {

    private fun isServiceRunning(
        context: Context,
        serviceClass: Class<*>
    ): Boolean {
        val manager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    fun startCheckRateService(context: Context) {
        if (!isServiceRunning(context, CheckCoinRateService::class.java)) {

            val intent = Intent(context, CheckCoinRateService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }

        }
    }

    fun stopCheckRateService(context: Context) {
        if (isServiceRunning(context, CheckCoinRateService::class.java)) {
            val serviceIntent = Intent(context, CheckCoinRateService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.stopService(serviceIntent)
            } else {
                context.stopService(serviceIntent)
            }
        }
    }
}