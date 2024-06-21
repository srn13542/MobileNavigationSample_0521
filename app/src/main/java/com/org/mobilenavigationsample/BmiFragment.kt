package com.org.mobilenavigationsample

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.org.mobilenavigationsample.R


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/*
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG_REPORT = "report_fragment"
private const val TAG_HOME = "home_fragment"
private const val TAG_START_EXERCISE = "start_exercise_fragment"
private const val TAG_MY_INFO = "my_info_fragment"
private const val TAG_BMI = "bmi_fragment"
 */

class BmiFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var naviActivity: NaviActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // NaviActivity와의 연결 설정
        if (context is NaviActivity) {
            naviActivity = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bmi, container, false)

        // SharedPreferences에서 키와 몸무게 가져오기
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("login_prefs", AppCompatActivity.MODE_PRIVATE)
        val height = sharedPreferences.getInt("height", 0)
        val weight = sharedPreferences.getInt("weight", 0)

        // BMI 계산
        val bmi = calculateBMI(height, weight)

        // BMI 결과 표시
        val bmiResult: TextView = view.findViewById(R.id.bmi_result)
        bmiResult.text = String.format("%.2f", bmi)

        // 현재 상태 표시
        val currentState: TextView = view.findViewById(R.id.CurrentState)
        val state = getBMIState(bmi)
        currentState.text = state
        currentState.setTextColor(getColorByState(state))

        // 이상적인 체중 표시
        val idealWeight: TextView = view.findViewById(R.id.IdealWeight)
        idealWeight.text = getIdealWeight(height)

        // 뒤로 가기 버튼 설정
        val goBackButton: Button = view.findViewById(R.id.buttonGoBack)
        goBackButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }

    // BMI 계산 함수
    private fun calculateBMI(height: Int, weight: Int): Double {
        if (height == 0) return 0.0
        val heightInMeters = height / 100.0
        return weight / (heightInMeters * heightInMeters)
    }

    // BMI 상태 반환 함수
    private fun getBMIState(bmi: Double): String {
        return when {
            bmi < 18.5 -> "저체중"
            bmi < 23 -> "정상"
            bmi < 25 -> "과체중"
            bmi < 30 -> "비만"
            else -> "고도비만"
        }
    }

    // BMI 상태에 따른 색상 반환 함수
    private fun getColorByState(state: String): Int {
        return when (state) {
            "저체중" -> Color.parseColor("#0000FF")    // Blue
            "정상" -> Color.parseColor("#008000")     // Green
            "과체중" -> Color.parseColor("#e8d100")   // Yellow
            "비만" -> Color.parseColor("#FFA500")     // Orange
            "고도비만" -> Color.parseColor("#FF0000")  // Red
            else -> Color.parseColor("#000000") // Black
        }
    }

    // 이상적인 체중 범위 계산 함수
    private fun getIdealWeight(height: Int): String {
        val heightInMeters = height / 100.0
        val idealWeightMin: Double = 18.5 * heightInMeters * heightInMeters
        val idealWeightMax: Double = 23 * heightInMeters * heightInMeters
        return String.format("%.2f kg ~ %.2f kg", idealWeightMin, idealWeightMax)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BmiFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
