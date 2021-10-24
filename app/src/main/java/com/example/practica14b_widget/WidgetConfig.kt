package com.example.practica14b_widget

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class WidgetConfig: Activity() {
    private var widgetID = 0

    private  lateinit var  txtMsg: EditText
    private lateinit var btnAcep: Button
    private lateinit var btnCanc:Button

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_config)
        val intentOrigen = intent
        val params = intentOrigen.extras

        widgetID = params!!.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        )

        setResult(RESULT_CANCELED)

        btnAcep = findViewById(R.id.btnAceptar)
        btnCanc = findViewById(R.id.btnCanelar)
        txtMsg =  findViewById(R.id.txtMsg)

        btnCanc.setOnClickListener {
            finish()
        }

        btnAcep.setOnClickListener {
            //guardamos el mensaje personalizado
            val prefs =  getSharedPreferences("WidgetPref", Context.MODE_PRIVATE)
            val editor =  prefs.edit()

            editor.putString("msg_$widgetID", txtMsg.text.toString())
            editor.commit()

            //actualizamos el widget tras la config
            val appWidgetManager = AppWidgetManager.getInstance(this@WidgetConfig)
            PrimerWidget.actualizarWidget(this, appWidgetManager, widgetID)

            val resultado =  Intent()
            resultado.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID)
            setResult(RESULT_OK, resultado)
            finish()
        }
    }
}