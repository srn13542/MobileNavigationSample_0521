package com.example.mobilenavigationsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * 간단한 [Fragment] 서브클래스.
 * [HomeFragment.newInstance] 팩토리 메서드를 사용하여 이 프래그먼트의 인스턴스를 생성하세요.
 */
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

        val goExerciseButton: Button = view.findViewById(R.id.goExerciseButton)

        goExerciseButton.setOnClickListener {
            // 운동하러 가기 버튼 클릭 시 동작 (현재 토스트 메시지 출력. 추후 변경.)
            Toast.makeText(requireContext(), "운동하러 가기 버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show()
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

    companion object {
        /**
         * 이 팩토리 메서드를 사용하여 이 프래그먼트의 새 인스턴스를 생성합니다.
         * 제공된 매개변수를 사용하여.
         *
         * @param param1 매개변수 1.
         * @param param2 매개변수 2.
         * @return HomeFragment의 새 인스턴스.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
