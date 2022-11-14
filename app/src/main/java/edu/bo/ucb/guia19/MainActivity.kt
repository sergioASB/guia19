package edu.bo.ucb.guia19

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener{notificacionInterna()}
    }
    val button1:Button
    get() = findViewById(R.id.button)


    fun notificacionInterna() {
        val i = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT)
        val sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val CHANNEL_ID = "my_channel_01"
        if ( Build.VERSION.SDK_INT >= 26) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_bg)
            .setContentTitle("Notificación")
            .setContentText("Notificaciones Internas")
            .setSound(sonido)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notification.build())
    }
}
