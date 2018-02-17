package com.satoru.kishi.demoorderedbroadcast.sms

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.satoru.kishi.demoorderedbroadcast.*
import kotlinx.android.synthetic.main.activity_sms.*

class SMSActivity : AppCompatActivity() {

    private var mReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)

        if (intent != null) {
            val i = intent
            imprimirMensagem(i)
        }
        requestSmsPermission()
    }

    private fun imprimirMensagem(intent: Intent?) {
        val remetente = intent?.getStringExtra("remetente")
        val mensagem = intent?.getStringExtra("mensagem")
        tvMessage.text = if (remetente == null && mensagem == null) "" else remetente + " : " + mensagem
    }

    private fun requestSmsPermission() {
        val permission = Manifest.permission.RECEIVE_SMS
        val grant = ContextCompat.checkSelfPermission(this, permission)
        if (grant != PackageManager.PERMISSION_GRANTED) {
            val permission_list = arrayOfNulls<String>(1)
            permission_list[0] = permission
            ActivityCompat.requestPermissions(this, permission_list, 1)
        }
    }

    override fun onResume() {
        super.onResume()

        val intentFilter = IntentFilter("android.intent.action.SMSRECEBIDO")

        mReceiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {
                //extract our message from intent
                imprimirMensagem(intent)
            }
        }
        //registering our receiver
        this.registerReceiver(mReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        //unregister our receiver
        this.unregisterReceiver(this.mReceiver)
    }
}