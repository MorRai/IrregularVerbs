package com.rai.irregularverbs.receiver



import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.app.*
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.lifecycle.viewModelScope
import com.rai.irregularverbs.R
import com.rai.irregularverbs.ui.MainActivity
import kotlinx.coroutines.launch


class VerbReceiver : BroadcastReceiver() {

    private val CHANNEL_ID = "this.is.my.channelId"

    override fun onReceive(context: Context, intent: Intent?) {

        val notificationIntent =
            Intent(context, MainActivity::class.java) //on tap this activity will open
        val stackBuilder: TaskStackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(notificationIntent)
        val pendingIntent: PendingIntent = stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT
        ) //getting the pendingIntent



        val notification = NotificationCompat.Builder(context).apply {
            setContentTitle(context.getString(R.string.content_title))
           // setContentText("be/???/???")
            setSmallIcon(R.mipmap.ic_launcher)
            setLargeIcon(
                BitmapFactory.decodeResource(context.resources,
                    R.mipmap.ic_launcher))
            setAutoCancel(true)
            setContentIntent(pendingIntent)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                setChannelId(CHANNEL_ID)
            }
        }.build()



            val notificationManager =
                context.getSystemService(NotificationManager::class.java)
            //below creating notification channel, because of androids latest update, O is Oreo
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "NotificationVerbs",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }
            notificationManager.notify(0, notification)
        }
}





