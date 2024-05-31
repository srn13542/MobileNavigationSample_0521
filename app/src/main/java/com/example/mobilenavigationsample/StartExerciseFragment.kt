package com.example.mobilenavigationsample

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.location.LocationManager
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
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//gson이 해결되지 않아 주석 처리.,,.

import androidx.fragment.app.FragmentManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import android.os.Looper
import android.util.Log
import androidx.annotation.ReturnThis
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.gms.location.*
//import com.google.android.gms.maps.model.CameraPosition


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
class StartExerciseFragment() : Fragment()/*, OnMapReadyCallback*/ {
//class MapsFragment(val activity: Activity) : Fragment(), OnMapReadyCallback

    //변수 삽입 부분
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var mLocationManager: LocationManager? = null
    var mLocationListener: LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }// getSystemService를 사용할 때 context를 사용해야 합니다.

        context?.let {
            mLocationManager = it.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        } ?: run {
            // context가 null인 경우 처리
            throw IllegalStateException("Context is null")
        }
        //mLocationManager = getSystemService(LOCATION_SERVICE) as LocationManager
    }

    private lateinit var mView: MapView

    //MapView 나중에 추가.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {


        //

        return inflater.inflate(R.layout.fragment_start_exercise, container, false)
    }



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


