package com.tinoba.device

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import io.reactivex.Single
import java.net.InetAddress
import java.net.UnknownHostException

class ConnectivityReceiverImpl(val context: Context) : ConnectivityReceiver {

    companion object {

        private const val PING_ADDRESS = "www.google.com"

    }

    override fun isConnected(): Single<Boolean> = Single.fromCallable { isInternetAvailable() && canResolveAddress() }

    private fun canResolveAddress(): Boolean {
        return pingAddress()
    }

    private fun pingAddress(): Boolean {
        return try {
            val address: InetAddress = InetAddress.getByName(PING_ADDRESS)
            return address.hostAddress.isNotEmpty()
        } catch (e: UnknownHostException) {
            false
        }
    }

    private fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }
}