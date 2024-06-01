package com.example.mobilenavigationsample

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.mobilenavigationsample.databinding.FragmentStartExerciseBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

//gson이 해결되지 않아 주석 처리.,,.

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [StartExerciseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartExerciseFragment() : Fragment()/*, OnMapReadyCallback*/ {
//class MapsFragment(val activity: Activity) : Fragment(), OnMapReadyCallback


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null //현재 위치를 가져오기 위한 변수
    lateinit var mLastLocation: Location //위치 값을 가지고 있는 객체
    internal lateinit var mLocationRequest: LocationRequest //위치 정보 요청의 매개변수를 저장하는 곳
    private val REQUEST_PERMISSION_LOCATION = 10

    lateinit var startExerciseCheckButton: Button
    private lateinit var binding: FragmentStartExerciseBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

            //버튼 바인딩


            mLocationRequest =  LocationRequest.create().apply {

                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                //mLocationRequest는 위치 정보 요청의 매개변수 저장하는 곳.
                //본 함수를 통해 위기 정보 요청의 매개변수를 저장..,불러옴?
            }
        }// getSystemService를 사용할 때 context를 사용해야 합니다.
    }

    //아오  이 하단에 getMapaSync 하면 작동될 것이라고 함
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val mapFragment = childFragmentManager.findFragmentById(R.id.startExerciseMap) as SupportMapFragment
        //mapFragment.getMapAsync()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val startExerciseView = inflater.inflate(R.layout.fragment_start_exercise, container, false)
        binding = FragmentStartExerciseBinding.bind(startExerciseView)

        startExerciseCheckButton = binding.startExerciseButton

        //getMapAsync 하려고 똥꼬쇼

        //
        mLocationRequest =  LocationRequest.create().apply {

            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            //mLocationRequest는 위치 정보 요청의 매개변수 저장하는 곳.
            //본 함수를 통해 위기 정보 요청의 매개변수를 저장..,불러옴?
        }

        // 버튼 이벤트를 통해 현재 위치 찾기
        startExerciseCheckButton.setOnClickListener {
            if (checkPermissionForLocation(requireContext())) {
                startLocationUpdates()
            }

        }

        // 버튼 이벤트 설정
        startExerciseCheckButton.setOnClickListener {
            if (checkPermissionForLocation(requireContext())) {
                //여기 마커 추가 및
                //운동을 시작합니다 ㄱ
                startLocationUpdates()
            }
        }


        //jsonFile 읽어오는 프로세스
        val jsonLocationString = requireActivity().assets.open("location.json").bufferedReader().use { it.readText() }
        val jsonLocationType = object:TypeToken<Maps>() {}.type
        val somePlaceMap = Gson().fromJson(jsonLocationString, jsonLocationType) as Maps //각각이 객체가 되어 somPlaceMap에 담김

        //마커를 더함
        somePlaceMap.map.forEach{

        }


        return startExerciseView
    }


    //함수 기입

    private fun startLocationUpdates(){
        //FusedLocationProvideClient의 인스턴스를 생성.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        if(ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED
            &&ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            return
        }
        // 기기의 위치에 관한 정기 업데이트를 요청하는 메서드 실행
        // 지정한 루퍼 스레드(Looper.myLooper())에서 콜백(mLocationCallback)으로 위치 업데이트를 요청

        mFusedLocationProviderClient!!.requestLocationUpdates(mLocationRequest,mLocationCallback,
            Looper.myLooper()?: Looper.getMainLooper())
    }

    private val mLocationCallback = object:LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult){
            //시스템에서 받은 Location 정보를 onLocationChanged()에 전달
            locationResult.lastLocation
            locationResult.lastLocation?.let { onLocationChanged(it) }
            Log.d("Location", "위도: "+locationResult.lastLocation?.latitude+" 경도: "+locationResult.lastLocation?.longitude)
            //onLocationChanged(locationResult.lastLocation)
            //으로 작성했는데 되는지 모르겠음
            //본 본문은 https://fre2-dom.tistory.com/134
            //를 참고함
        }
    }

    fun onLocationChanged(location: Location){
        mLastLocation = location
        //시스템으로부터 받은 위치 정보를 화면에 갱신해주는 메소드
    }

    //위치 권한이 있는지 확인하는 메소드
    private fun checkPermissionForLocation(context: Context):Boolean{
        // Android 6.0 Marshmallow 이상에서는 위치 권한에 추가 런타임 권한이 필요
        return if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
            if(context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                true
            }else{
                //권한이 없으므로 권한 요청 알림 보내기
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_PERMISSION_LOCATION)
                false
            }
        }else{
            true
        }
    }

    //사용자에게 권한 요청 수 결과에 대한 처리 로직
    fun onRequestPermissionResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_PERMISSION_LOCATION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startLocationUpdates()
            }else{
                Log.d("ttt", "onRequestPermissionsResult() _ 권한 허용 거부")
                Toast.makeText(requireContext(), "권한이 없어 해당 기능을 실행할 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
            }
        }


    //--------------------------------------------------------------------
    //마커 추가하는 프로세스








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


