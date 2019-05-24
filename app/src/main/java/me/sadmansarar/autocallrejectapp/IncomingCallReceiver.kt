package me.sadmansarar.autocallrejectapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import com.android.internal.telephony.ITelephony


class IncomingCallReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val tm = context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        try {
            val c = Class.forName(tm.javaClass.name)
            val m = c.getDeclaredMethod("getITelephony")
            m.isAccessible = true
            val telephonyService = m.invoke(tm) as ITelephony
            val bundle = intent?.extras
            val phoneNumber = bundle?.getString("incoming_number")
            Log.d("INCOMING", phoneNumber)
            if (phoneNumber != null) {
                telephonyService.endCall()
                Log.d("HANG UP", phoneNumber)
                Log.d("INCOMING", "hang up $phoneNumber")
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}