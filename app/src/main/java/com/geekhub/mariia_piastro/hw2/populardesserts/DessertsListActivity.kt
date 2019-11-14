package com.geekhub.mariia_piastro.hw2.populardesserts

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_fragment.*


class DessertsListActivity : AppCompatActivity(), DessertAdapter.Callback, View.OnClickListener {

    private var DESSERT_KEY = "DESSERT"
    private val REQUEST_CODE_PERMISSION_ACCESS_FINE_LOCATION = 1
    private var dessert: Desserts? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        createNotificationChannel()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container_titles,
                    DessertsListFragment.newInstance()
                )
                .commit()
        } else dessert = savedInstanceState.getSerializable(DESSERT_KEY) as Desserts?

        if (fragment_container_details != null) {
            if (supportFragmentManager.backStackEntryCount > 0)
                supportFragmentManager.popBackStack()
            if (dessert == null)
                dessert = Desserts(
                    getString(R.string.dessert_is_not_selected),
                    "",
                    R.drawable.empty_image
                )
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_titles, DessertsListFragment())
                .replace(
                    R.id.fragment_container_details,
                    DessertDetailFragment.newInstance(dessert)
                )
                .commit()
        } else {
            buttonLocation?.setOnClickListener(this)
            buttonNotification?.setOnClickListener(this)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = getString(R.string.channel_id)
            val name = getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(channelId, name, importance).apply {
                this.description = description
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonLocation -> {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                    == PackageManager.PERMISSION_GRANTED
                )
                    startActivity(Intent(this, LocationActivity::class.java))
                else
                    ActivityCompat.requestPermissions(
                        this, arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ), REQUEST_CODE_PERMISSION_ACCESS_FINE_LOCATION
                    )
            }
            R.id.buttonNotification -> {
                val noti =
                    NotificationCompat.Builder(this, getString(R.string.channel_id)).apply {
                        setContentTitle(getString(R.string.notification_title))
                        setContentText(getString(R.string.notification_text))
                        setSmallIcon(R.drawable.cake)
                        priority = NotificationCompat.PRIORITY_DEFAULT
                    }
                val notificationManager =
                    NotificationManagerCompat.from(this)
                notificationManager.notify(1, noti.build())
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(DESSERT_KEY, dessert)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        dessert = savedInstanceState?.getSerializable(DESSERT_KEY) as Desserts?
    }

    override fun onItemClick(dessert: Desserts) {
        this.dessert = dessert
        val fragmentDetails: FrameLayout? = fragment_container_details
        val detailsFragment =
            DessertDetailFragment.newInstance(dessert)
        if (fragmentDetails == null) { //портрет
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_titles, detailsFragment)
                .addToBackStack(null)
                .commit()
        } else { //пейзаж
            if (supportFragmentManager.backStackEntryCount > 0)
                supportFragmentManager.popBackStack()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_details, detailsFragment)
                .commit()
        }
    }
}
