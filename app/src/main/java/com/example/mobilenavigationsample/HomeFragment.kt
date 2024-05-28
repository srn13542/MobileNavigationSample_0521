package com.example.mobilenavigationsample


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var burnedCalories = 3000 // 사용자가 소모한 칼로리 (나중에 칼로리 데이터 받아오는 것으로 변경)

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
            // 운동하러 가기 버튼 클릭 시 토스트 메시지 출력 (나중엔 버튼 클릭 시 행동 지정)
            Toast.makeText(requireContext(), "운동하러 가기 버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show()
        }

        // 사용자가 소모한 칼로리에 따라 음식 종류와 칼로리 설정
        setFoodAndCalories(view)

        return view
    }

    private fun setFoodAndCalories(view: View) {
        val food: TextView = view.findViewById(R.id.Food)
        val calorie: TextView = view.findViewById(R.id.Calorie)

        calorie.text = burnedCalories.toString()

        // 사용자가 소모한 칼로리 범위에 따라 음식과 칼로리 설정 (범위 설정에 따른 음식 종류 등 회의 필요)
        when {
            burnedCalories < 2000 -> {
                food.text = "간식 한 봉지"
            }
            burnedCalories < 3000 -> {
                food.text = "치킨 한 마리"
            }
            else -> {
                food.text = "피자 한 판"
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
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
