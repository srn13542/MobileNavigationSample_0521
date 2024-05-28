package com.example.mobilenavigationsample

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BmiFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var naviActivity: NaviActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // NaviActivity 클래스의 참조를 저장
        if (context is NaviActivity) {
            naviActivity = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bmi_flagment, container, false)

        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("login_prefs", AppCompatActivity.MODE_PRIVATE)
        val height = sharedPreferences.getInt("height", 0)
        val weight = sharedPreferences.getInt("weight", 0)

        val bmi = calculateBMI(height, weight)

        val bmiResult: TextView = view.findViewById(R.id.bmi_result)
        bmiResult.text = String.format("%.2f", bmi)

        val currentState: TextView = view.findViewById(R.id.CurrentState)
        val state = getBMIState(bmi)
        currentState.text = state
        currentState.setTextColor(getColorByState(state))

        val idealWeight: TextView = view.findViewById(R.id.IdealWeight)
        idealWeight.text = getIdealWeight(height)

        val goBackButton: Button = view.findViewById(R.id.buttonGoBack)
        goBackButton.setOnClickListener {
            // BmiFragment가 화면에 표시되어 있고 "돌아가기" 버튼을 클릭했을 때만 이전 Fragment로 돌아감
            parentFragmentManager.popBackStack()
        }



        return view
    }

    private fun calculateBMI(height: Int, weight: Int): Double {
        if (height == 0) return 0.0
        val heightInMeters = height / 100.0
        return weight / (heightInMeters * heightInMeters)
    }

    private fun getBMIState(bmi: Double): String {
        return when {
            bmi < 18.5 -> "저체중"
            bmi < 23 -> "정상"
            bmi < 25 -> "과체중"
            bmi < 30 -> "비만"
            else -> "고도비만"
        }
    }

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
