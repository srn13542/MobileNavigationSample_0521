package com.example.mobilenavigationsample

import com.example.mobilenavigationsample.HomeFragment
import com.example.mobilenavigationsample.MapActivity
import com.example.mobilenavigationsample.MyInfoFragment
import com.example.mobilenavigationsample.R
import com.example.mobilenavigationsample.StartExerciseFragment
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
//import androidx.lifecycle.viewmodel.CreationExtras.Empty.map
import com.example.mobilenavigationsample.databinding.ActivityNaviBinding
import com.example.mobilenavigationsample.databinding.FragmentStartExerciseBinding
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
//import com.example.com.example.mobilenavigationsample.databinding.

private const val TAG_REPORT = "report_fragment"
private const val TAG_HOME = "home_fragment"
private const val TAG_START_EXERCISE = "start_exercise_fragment"
private const val TAG_MY_INFO = "my_info_fragment"



class NaviActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNaviBinding
    // intent를 통해 navi activity에서 넘어온 운동 종류가 저장되는 코드.
    //  val selectedExerciseType = intent.getStringExtra("EXERCISE_TYPE")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*
//여기부터 주석 시작
        val mapFragment = supportFragmentManager.findFragmentById(R.id.navigationView) as? SupportMapFragment
        mapFragment?.getMapAsync{ googleMap->
            //초기 장소 설정
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(37.3184153,126.7064638),16F))
            // 최소 줌
            googleMap.setMinZoomPreference(14F)

            val jsonString = this@NaviActivity.assets.open("Location.json").bufferedReader().readText()
            val jsonType = object: TypeToken<Maps>(){}.type
            val maps = Gson().fromJson(jsonString,jsonType) as Maps // CameraMap
            //cameraMap == maps
            //Camera == Location_information
            maps.map.forEach{
                map -> // camera
                val icon = when(map.town){
                    "정왕동" -> BitmapDescriptorFactory.fromResource(R.drawable.nav_exercise_show)
                    else -> BitmapDescriptorFactory.fromResource(R.drawable.nav_exercise_show)
                }
                googleMap.addMarker(
                    MarkerOptions()
                        .icon(icon)
                        .position(LatLng(map.latitude,map.longitude))
                        .title(map.town)
                        .snippet("${map.latitude},${map.longitude}")
                )?.let{
                    it.tag = map
                }


            }

            // 클릭시 상세 정보
            googleMap.setOnInfoWindowClickListener {
                val map = it.tag as Location_information
                val intent = Intent (this@NaviActivity, MapActivity::class.java).apply{
                    putExtra("town", map.town)
                    putExtra("name", map.name)
                    putExtra("address", map.address)
                    putExtra("latitude",  map.latitude)
                    putExtra("longitude", map.longitude)
                    putExtra("exercise", map.exercise)
                }
                this@NaviActivity.startActivity(intent)
            }
        }
// 여기까지 주석하기
 */

        setFragment(TAG_HOME, HomeFragment())

        binding.navigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.reportFragment -> setFragment(TAG_REPORT, ReportFragment())
                R.id.homeFragment -> setFragment(TAG_HOME, HomeFragment())
                R.id.startExcerciseFragment -> setFragment(
                    TAG_START_EXERCISE,
                    StartExerciseFragment(this)
                )

                R.id.myInfoFragment -> setFragment(TAG_MY_INFO, MyInfoFragment())
            }
            true
        }
    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager = supportFragmentManager
        //val fragTransaction = manager.beginTransaction()

        val fragTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null) {
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        val report = supportFragmentManager.findFragmentByTag(TAG_REPORT)
        val home = supportFragmentManager.findFragmentByTag(TAG_HOME)
        val startExercise = supportFragmentManager.findFragmentByTag(TAG_START_EXERCISE)
        val myInfo = supportFragmentManager.findFragmentByTag(TAG_MY_INFO)

        if (report != null) {
            fragTransaction.hide(report)
        }

        if (home != null) {
            fragTransaction.hide(home)
        }

        if (startExercise != null) {
            fragTransaction.hide(startExercise)
        }

        if (myInfo != null) {
            fragTransaction.hide(myInfo)
        }

        if (tag == TAG_REPORT) {
            if (report != null) {
                fragTransaction.show(report)
            }
        } else if (tag == TAG_HOME) {
            if (home != null) {
                fragTransaction.show(home)
            }
        } else if (tag == TAG_START_EXERCISE) {
            if (startExercise != null) {
                fragTransaction.show(startExercise)
            }
        } else if (tag == TAG_MY_INFO) {
            if (myInfo != null) {
                fragTransaction.show(myInfo)
            }
        }

        fragTransaction.commitAllowingStateLoss()

    }

}




