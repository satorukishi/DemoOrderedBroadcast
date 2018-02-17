package com.satoru.kishi.demoorderedbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //region:
        var filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.action) {
                    Intent.ACTION_POWER_CONNECTED -> tvUSBStatus.text = "Cabo conectado"
                    Intent.ACTION_POWER_DISCONNECTED -> {
                        tvUSBStatus.text = "Cabo desconectado"
                    }
                }
            }
        }
        registerReceiver(receiver, filter)



        // É possível utilizar Lambda expression aqui porque a interface OnClickListener só tem um método
        btEnviar.setOnClickListener {
            sendBroadcast(Intent("com.satoru.kishi.demoorderedbroadcast.MeuOrderedBroadcast"))
        }
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()


    }
}
