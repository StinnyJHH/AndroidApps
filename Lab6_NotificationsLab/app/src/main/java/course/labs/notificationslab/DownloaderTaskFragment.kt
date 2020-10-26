@file:Suppress("unused")

package course.labs.notificationslab

import android.annotation.SuppressLint
import android.app.*
import android.app.PendingIntent.*
import android.content.BroadcastReceiver
import android.content.Context
import android.app.NotificationManager
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews
import android.widget.Toast
import java.io.*

class DownloaderTaskFragment : Fragment() {
    private var mDownloaderTask: DownloaderTask? = null
    private var mCallback: DownloadFinishedListener? = null
    private lateinit var mContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        mDownloaderTask = DownloaderTask()

        val tmp = arguments.getIntegerArrayList(
                MainActivity.TAG_FRIEND_RES_IDS)
        var feeds = arrayOfNulls<Int>(tmp!!.size)
        feeds = tmp.toArray(feeds)

        mDownloaderTask!!.execute(*feeds)

        return inflater.inflate(R.layout.feed, container, false)
    }

    // Assign current hosting Activity to mCallback
    // Store application context for use by downloadTweets()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

        // Make sure that the hosting activity has implemented
        // the correct callback interface.

        try {

            mCallback = context as DownloadFinishedListener

        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement DownloadFinishedListener")
        }
    }

    // Null out mCallback
    override fun onDetach() {
        super.onDetach()
        mCallback = null
    }


    // This class is an AsyncTask, Async has been deprecated by API 30, therefore
    // you will not have to implement doInBackground and onPostExecute
    @SuppressLint("StaticFieldLeak")
    inner class DownloaderTask : AsyncTask<Int?, Void?, Array<String?>>() {
        override fun doInBackground(vararg resourceIDs: Int?): Array<String?> {
            return downloadTweets(resourceIDs as Array<Int>)
        }

        // Simulates downloading Twitter data from the network
        private fun downloadTweets(resourceIDS: Array<Int>): Array<String?> {
            val simulatedDelay = 2000
            val feeds = arrayOfNulls<String>(resourceIDS.size)
            var downLoadCompleted = false
            try {
                for (idx in resourceIDS.indices) {
                    var `in`: BufferedReader
                    try {
                        // Pretend downloading takes a long time
                        Thread.sleep(simulatedDelay.toLong())
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    val inputStream: InputStream? = mContext.resources.openRawResource(
                            resourceIDS[idx])
                    `in` = BufferedReader(InputStreamReader(inputStream))
                    var readLine: String?
                    val buf = StringBuffer()
                    while (`in`.readLine().also { readLine = it } != null) {
                        buf.append(readLine)
                    }
                    feeds[idx] = buf.toString()
                    `in`.close()
                }
                downLoadCompleted = true
                saveTweetsToFile(feeds)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            // Notify user that downloading has finished
            notify(downLoadCompleted)
            return feeds
        }

        // If necessary, notifies the user that the tweet downloads are
        // complete. Sends an ordered broadcast back to the BroadcastReceiver in
        // MainActivity to determine whether the notification is necessary.
        private fun notify(success: Boolean) {
            val restartMainActivityIntent = Intent(mContext,
                    MainActivity::class.java)
            restartMainActivityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            // Sends an ordered broadcast to determine whether MainActivity is
            // active and in the foreground. Creates a new BroadcastReceiver
            // to receive a result indicating the state of MainActivity

            // TODO: The Action for this broadcast Intent is MainActivity.DATA_REFRESHED_ACTION
            // The result, MainActivity.IS_ALIVE, indicates that MainActivity is
            // active and in the foreground.
            mContext.sendOrderedBroadcast(Intent(
                    MainActivity.DATA_REFRESHED_ACTION), null,
                    object : BroadcastReceiver() {
                        val failMsg = mContext
                                .getString(R.string.download_failed_string)
                        val successMsg = mContext
                                .getString(R.string.download_succes_string)
                        val notificationSentMsg = mContext
                                .getString(R.string.notification_sent_string)

                        @RequiresApi(Build.VERSION_CODES.O)
                        override fun onReceive(context: Context, intent: Intent) {
                            createNotificationChannel()
                            // TODO: Check whether or not the MainActivity
                            // received the broadcast
                            if (resultCode != MainActivity.IS_ALIVE) {

                                // TODO: If not, create a PendingIntent using the
                                // restartMainActivityIntent and set its flags
                                // to FLAG_UPDATE_CURRENT
                                val restartIntent = restartMainActivityIntent
                                val pendingIntent = PendingIntent.getActivity(
                                        mContext, 0, restartIntent,
                                        PendingIntent.FLAG_UPDATE_CURRENT)
                                // Uses R.layout.custom_notification for the
                                // layout of the notification View. The xml
                                // file is in res/layout/custom_notification.xml
                                val mContentView = RemoteViews(
                                        mContext.packageName,
                                        R.layout.custom_notification)

                                // TODO: Set the notification View's text to
                                // reflect whether the download completed
                                // successfully
                                if(success)
                                    mContentView.setTextViewText(R.layout.custom_notification, successMsg)
                                else
                                    mContentView.setTextViewText(R.layout.custom_notification, failMsg)
                                // TODO: Use the Notification.Builder class to
                                // create the Notification. You will have to set
                                // several pieces of information. You can use
                                // android.R.drawable.stat_sys_warning
                                // for the small icon. You should also
                                // setAutoCancel(true).
                                var notificationBuilder = Notification.Builder(mContext, channelID).setAutoCancel(true)
                                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                                        .setContentIntent(pendingIntent)
                                // TODO: Send the notification
                                val mNotifyManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                                mNotifyManager.notify(MY_NOTIFICATION_ID, notificationBuilder.build())

                                Toast.makeText(mContext, notificationSentMsg, Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(mContext,
                                        if (success) successMsg else failMsg,
                                        Toast.LENGTH_LONG).show()
                            }
                        }
                    }, null, 0, null, null)
        }

        private val channelID = "my_channel_01"
        private fun createNotificationChannel() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // TODO: Create Notification Channel with id channelID,
                // name R.string.channel_name
                // and description R.string.channel_description of high importance
                val name = mContext.getString(R.string.channel_name)
                val descriptionText = mContext.getString(R.string.channel_description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val mChannel = NotificationChannel(channelID, name, importance)
                mChannel.description = descriptionText
                val notificationManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(mChannel)
            }
        }

        // Saves the tweets to a file
        private fun saveTweetsToFile(result: Array<String?>) {
            var writer: PrintWriter? = null
            try {
                val fos = mContext.openFileOutput(
                        MainActivity.TWEET_FILENAME, Context.MODE_PRIVATE)
                writer = PrintWriter(BufferedWriter(
                        OutputStreamWriter(fos)))
                for (s in result) {
                    writer.println(s)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                writer?.close()
            }
        }

        // Pass newly available data back to hosting Activity
        // using the DownloadFinishedListener interface
        override fun onPostExecute(result: Array<String?>) {
            super.onPostExecute(result)
            if (null != mCallback) {
                mCallback!!.notifyDataRefreshed(result)
            }
        }
    }

    companion object {
        private const val TAG = "Lab-Notifications"
        private const val MY_NOTIFICATION_ID = 11151990
    }
}