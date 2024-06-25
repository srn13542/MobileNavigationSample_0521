package com.org.mobilenavigationsample

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.org.mobilenavigationsample.databinding.FragmentStartExerciseBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val PREFS_NAME = "com.org.mobilenavigationsample.prefs"
private const val PREFS_KEY_FIRST_RUN = "first_run"
private const val REQUEST_PERMISSION_LOCATION = 10

class StartExerciseFragment : Fragment(), OnMapReadyCallback {

    private var param1: String? = null
    private var param2: String? = null

    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null
    private lateinit var mLastLocation: Location
    private lateinit var mLocationRequest: LocationRequest
    private lateinit var binding: FragmentStartExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val startExerciseView = inflater.inflate(R.layout.fragment_start_exercise, container, false)
        binding = FragmentStartExerciseBinding.bind(startExerciseView)

        val fragmentManager = childFragmentManager
        val startExerciseMapView: SupportMapFragment? =
            fragmentManager.findFragmentById(R.id.startExerciseMap) as SupportMapFragment?
        startExerciseMapView?.getMapAsync { googleMap ->
            googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(37.340387, 126.733572),
                    16F
                )
            )
            googleMap.setMinZoomPreference(14F)

            val jsonLocationString = requireActivity().assets.open("location.json").bufferedReader()
                .use { it.readText() }
            val jsonLocationType = object : TypeToken<List<LocationInformation>>() {}.type
            val locations: List<LocationInformation> =
                Gson().fromJson(jsonLocationString, jsonLocationType)

            locations.forEach { location ->
                val icon = BitmapDescriptorFactory.fromResource(R.drawable.exercise_icon)
                googleMap.addMarker(
                    MarkerOptions()
                        .icon(icon)
                        .position(LatLng(location.latitude, location.longitude))
                        .title(location.name)
                        .snippet("${location.latitude}, ${location.longitude}")
                )?.apply {
                    tag = location
                }
            }

            googleMap.setOnMarkerClickListener { marker ->
                val location = marker.tag as? LocationInformation
                if (location != null) {
                    val intent = Intent(context, SelectExerciseActivity::class.java).apply {
                        putExtra("location_name", location.name)
                        putExtra("latitude", location.latitude)
                        putExtra("longitude", location.longitude)
                        putExtra("Exercise", location.exercise)
                    }
                    startActivity(intent)
                }
                true
            }
        }

        mLocationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        checkFirstRun()

        return startExerciseView
    }

    private fun checkFirstRun() {
        val prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isFirstRun = prefs.getBoolean(PREFS_KEY_FIRST_RUN, true)

        if (isFirstRun) {
            prefs.edit().putBoolean(PREFS_KEY_FIRST_RUN, false).apply()
            requestLocationPermission()
        } else {
            if (checkPermissionForLocation(requireContext())) {
                startLocationUpdates()
            } else {
                // Permission was previously denied, request again
                requestLocationPermission()
            }
        }
    }

    private fun requestLocationPermission() {
        if (checkPermissionForLocation(requireContext())) {
            startLocationUpdates()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_PERMISSION_LOCATION
            )
        }
    }

    private fun startLocationUpdates() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        mFusedLocationProviderClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper() ?: Looper.getMainLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation?.let { onLocationChanged(it) }
        }
    }

    fun onLocationChanged(location: Location) {
        mLastLocation = location
    }

    private fun checkPermissionForLocation(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_PERMISSION_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, start location updates
                    startLocationUpdates()
                } else {
                    // Permission denied, show a message or handle accordingly
                    Toast.makeText(
                        requireContext(),
                        "Location permission denied",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        // Implement this method if needed
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StartExerciseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
