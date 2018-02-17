package com.satoru.kishi.demoorderedbroadcast

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // É possível utilizar Lambda expression aqui porque a interface OnClickListener só tem um método
        btEnviar.setOnClickListener {
            sendBroadcast(Intent("com.satoru.kishi.demoorderedbroadcast.MeuOrderedBroadcast"))
            sendBroadcast(Intent("com.satoru.kishi.demoorderedbroadcast.MeuOutroBroadcast"))
        }
    }
}
