package com.example.mobilenavigationsample

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mobilenavigationsample.databinding.ActivityNaviBinding
import com.example.mobilenavigationsample.databinding.FragmentStartExerciseBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.fragment.app.FragmentManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FragmentStartExerciseBinding // 주석 하기

/**
 * A simple [Fragment] subclass.
 * Use the [StartExerciseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartExerciseFragment/*(val activity: Activity)*/ : Fragment()/*, OnMapReadyCallback*/ {
//class MapsFragment(val activity: Activity) : Fragment(), OnMapReadyCallback
    //onMapReadyCallBack ==

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //사용자의 위치를 받기 위한 코드
    lateinit var locationPermission: ActivityResultLauncher<Array<String>>
    private lateinit var mMap: GoogleMap

    lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var locationCallback: LocationCallback

    /*  private val callback = OnMapReadyCallback { googleMap ->
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var mView: MapView

    //MapView 나중에 추가.

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment*/

        locationPermission = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { results ->
            if (!results.all { it.value }) {
                Toast.makeText(activity, "권한 승인이 필요합니다.", Toast.LENGTH_LONG).show()
            }
        }

        //권한 요청
        locationPermission.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )

        return inflater.inflate(R.layout.fragment_start_exercise, container, false)
    }

/*  오류 나는 부분이라 임시 처리,
 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }
*/


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StartExerciseFragment.
         */
        // TODO: Rename and change types and number of parameters
        /* 오류 나는 부분이라 임시 주석 처리, 0531
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StartExerciseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*/
    }
}


