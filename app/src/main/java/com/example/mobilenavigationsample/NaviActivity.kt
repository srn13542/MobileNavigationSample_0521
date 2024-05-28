package com.example.mobilenavigationsample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.mobilenavigationsample.databinding.ActivityNaviBinding

private const val TAG_REPORT = "report_fragment"
private const val TAG_HOME = "home_fragment"
private const val TAG_START_EXERCISE = "start_exercise_fragment"
private const val TAG_MY_INFO = "my_info_fragment"

class NaviActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNaviBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 초기 선택을 홈으로 설정
        binding.navigationView.selectedItemId = R.id.homeFragment

        // 홈 프래그먼트 표시
        setFragment(TAG_HOME, HomeFragment())

        binding.navigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.reportFragment -> setFragment(TAG_REPORT, ReportFragment())
                R.id.homeFragment -> setFragment(TAG_HOME, HomeFragment())
                R.id.startExcerciseFragment -> setFragment(TAG_START_EXERCISE, StartExerciseFragment())
                R.id.myInfoFragment -> setFragment(TAG_MY_INFO, MyInfoFragment())
            }
            true
        }
    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager = supportFragmentManager
        val fragTransaction: FragmentTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null) {
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        val report = manager.findFragmentByTag(TAG_REPORT)
        val home = manager.findFragmentByTag(TAG_HOME)
        val startExercise = manager.findFragmentByTag(TAG_START_EXERCISE)
        val myInfo = manager.findFragmentByTag(TAG_MY_INFO)

        if (report != null) fragTransaction.hide(report)
        if (home != null) fragTransaction.hide(home)
        if (startExercise != null) fragTransaction.hide(startExercise)
        if (myInfo != null) fragTransaction.hide(myInfo)

        when (tag) {
            TAG_REPORT -> if (report != null) fragTransaction.show(report)
            TAG_HOME -> if (home != null) fragTransaction.show(home)
            TAG_START_EXERCISE -> if (startExercise != null) fragTransaction.show(startExercise)
            TAG_MY_INFO -> if (myInfo != null) fragTransaction.show(myInfo)
        }

        fragTransaction.commitAllowingStateLoss()
    }
}