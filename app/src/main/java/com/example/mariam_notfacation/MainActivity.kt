package com.example.mariam_notfacation

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    val channelId = "notification"
    val description = "Notification App"
    lateinit var name: EditText
    lateinit var getnoti: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name=findViewById(R.id.etMsg)
        getnoti=findViewById(R.id.notificationBtn)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager



        getnoti.setOnClickListener {
            notify(notificationManager)
            name.text.clear()
        }

    }
    fun notify(n: NotificationManager){
        var builder: Notification.Builder
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationChannel = NotificationChannel(channelId,description,
                NotificationManager.IMPORTANCE_HIGH)
            n.createNotificationChannel(notificationChannel)

            builder= Notification.Builder(this,channelId)
                .setSmallIcon(R.drawable.notifcattion)
                .setContentIntent(pendingIntent)
                .setContentTitle("New Notification")
                .setContentText(name.text.toString())
        } else {
            builder= Notification.Builder(this)
                .setSmallIcon(R.drawable.notifcattion)
                .setLargeIcon(
                    BitmapFactory.decodeResource(this.resources,
                    android.R.drawable.sym_call_outgoing)
                )
                .setContentIntent(pendingIntent)

                .setContentTitle("New Notification")
                .setContentText(name.text.toString())

        }
        n.notify(1234, builder.build())
    }
}