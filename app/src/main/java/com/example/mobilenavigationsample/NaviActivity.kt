package com.example.mobilenavigationsample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mobilenavigationsample.databinding.ActivityNaviBinding

private const val TAG_REPORT = "report_fragment"
private const val TAG_HOME = "home_fragment"
private const val TAG_START_EXERCISE = "start_exercise_fragment"
private const val TAG_MY_INFO = "my_info_fragment"
private const val TAG_BMI = "bmi_fragment"

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
            // BmiFragment가 백 스택에 있을 때 popBackStack 호출 (BMI_TAG를 백 스택에서 제외)
            supportFragmentManager.popBackStack(TAG_BMI, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            true
        }
    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null) {
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        val fragments = listOf(
            manager.findFragmentByTag(TAG_REPORT),
            manager.findFragmentByTag(TAG_HOME),
            manager.findFragmentByTag(TAG_START_EXERCISE),
            manager.findFragmentByTag(TAG_MY_INFO)
        )

        fragments.forEach { frag ->
            if (frag != null) fragTransaction.hide(frag)
        }

        val showFragment = manager.findFragmentByTag(tag)
        if (showFragment != null) fragTransaction.show(showFragment)

        fragTransaction.commitAllowingStateLoss()
    }
}
