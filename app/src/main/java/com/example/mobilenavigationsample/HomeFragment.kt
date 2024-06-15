package com.example.mobilenavigationsample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var burnedCalories = 1500 // 사용자가 소모한 칼로리 (나중에 데이터를 받아오는 것으로 변경 필요)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 크레딧 레이아웃 ClickListener
        val credits_button: View = view.findViewById(R.id.credits_button)
        credits_button.setOnClickListener {
            // 크레딧 버튼 눌릴때 CreditActivity로 변경
            val intent = Intent(activity, DeveloperCreditsActivity::class.java)
            startActivity(intent)
        }

        // 소모한 칼로리에 따라 음식 종류와 칼로리 설정
        setFoodAndCalories(view)

        return view
    }

    // 소모한 칼로리에 따라 음식 종류와 칼로리 설정
    private fun setFoodAndCalories(view: View) {
        val food: TextView = view.findViewById(R.id.Food)
        val calorie: TextView = view.findViewById(R.id.Calorie)

        calorie.text = burnedCalories.toString()

        // 소모한 칼로리 범위에 따라 음식 종류 설정
        val foodText = when {
            burnedCalories < 150 -> "탕후루 1개"
            burnedCalories < 200 -> "초코파이 1개"
            burnedCalories < 300 -> "피자 1조각"
            burnedCalories < 400 -> "포카칩 한 봉지"
            burnedCalories < 500 -> "초콜릿 케이크 1조각"
            burnedCalories < 550 -> "라면 1개"
            burnedCalories < 600 -> "빅맥 1개"
            burnedCalories < 700 -> "짬뽕 1그릇"
            burnedCalories < 800 -> "제육덮밥 1그릇"
            burnedCalories < 900 -> "짜장면 1그릇"
            burnedCalories < 1000 -> "다이제 1봉지"
            burnedCalories < 1100 -> "빅맥세트 1개"
            burnedCalories < 1200 -> "굽네치킨 1마리"
            burnedCalories < 1500 -> "보쌈 1인분"
            burnedCalories < 2000 -> "후라이드 치킨 1마리"
            burnedCalories >= 2000 -> "양념치킨 1마리"
            else -> "Over-Training"
        }
        food.text = foodText
    }
}
