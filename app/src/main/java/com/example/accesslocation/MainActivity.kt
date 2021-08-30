package com.example.accesslocation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity(), LocationListener{

    lateinit var locationManager: LocationManager
    lateinit var mContext: Context
    lateinit var tvLattitude: TextView
    lateinit var tvLongitude: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this

        tvLattitude = findViewById(R.id.tvLattitude)
        tvLongitude = findViewById(R.id.tvLongitude)

        val btnGetLocation: Button = findViewById(R.id.btnGetLocation)
        btnGetLocation.setOnClickListener {
            locationManager =mContext.getSystemService(LOCATION_SERVICE) as LocationManager
           /* if((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCAATION)!=PackageManager.PERMISSION_GRANTED))
            {

            }*/

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION ) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 10)
            }
            else
            {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
            }

        }
    }

    override fun onLocationChanged(p0: Location) {
        tvLattitude.text = p0.latitude.toString()
        tvLongitude.text = p0.longitude.toString()
        Toast.makeText(this, "Location is changed", Toast.LENGTH_SHORT).show()
        Log.d("Lat: "+p0.latitude, "Long: "+p0.longitude)
    }
}