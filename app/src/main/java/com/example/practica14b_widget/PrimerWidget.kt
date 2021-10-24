package com.example.practica14b_widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import java.text.SimpleDateFormat
import java.util.*

class PrimerWidget: AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ){
        for(i in appWidgetIds.indices){
            val widgetId = appWidgetIds[i]
            actualizarWidget(context, appWidgetManager, widgetId)
        }
    }
    companion object {
        fun actualizarWidget(context: Context, appWidgetManager: AppWidgetManager, widgetId: Int){
            Log.d(null, "Actualizar")
            val prefs =  context.getSharedPreferences("WidgetPref", Context.MODE_PRIVATE)
            val msg = prefs.getString("msg_$widgetId", "Hora Actual: ")
            val controles = RemoteViews(context.packageName, R.layout.primer_widget)
            controles.setTextViewText(R.id.lblMsg, msg)
            val date = Calendar.getInstance().time
            val formatter = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
            val hora =  formatter.format(date)
            controles.setTextViewText(R.id.lblHora, hora)
            appWidgetManager.updateAppWidget(widgetId, controles)
        }
    }
}