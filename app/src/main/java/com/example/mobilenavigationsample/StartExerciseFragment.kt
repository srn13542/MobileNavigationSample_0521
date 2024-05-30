package com.example.mobilenavigationsample


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobilenavigationsample.databinding.ActivityNaviBinding
import com.example.mobilenavigationsample.databinding.FragmentStartExerciseBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken







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
class StartExerciseFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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

    ): View? {
        binding = FragmentStartExerciseBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        //여기에
        //여기부터 주석 시작
        val mapFragment = supportFragmentManager.findFragmentById(R.id.startExerciseMap) as? SupportMapFragment
        mapFragment?.getMapAsync{ googleMap->
            //초기 장소 설정
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(37.3184153,126.7064638),16F))
            // 최소 줌
            googleMap.setMinZoomPreference(14F)

            val jsonString = this@StartExerciseFragment.assets.open("Location.json").bufferedReader().readText()
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
                val intent = Intent (this@StartExerciseFragment, MapActivity::class.java).apply{
                    putExtra("town", map.town)
                    putExtra("name", map.name)
                    putExtra("address", map.address)
                    putExtra("latitude",  map.latitude)
                    putExtra("longitude", map.longitude)
                    putExtra("exercise", map.exercise)
                }
                this@StartExerciseFragment.startActivity(intent)
            }
        }
// 여기까지 주석하기



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


}
