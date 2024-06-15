package com.example.mobilenavigationsample

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG_BMI = "bmi_fragment"

class MyInfoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var sharedPreferences: SharedPreferences

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
        val view = inflater.inflate(R.layout.fragment_my_info, container, false)
        sharedPreferences = requireContext().getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

        val btnSave: Button = view.findViewById(R.id.buttonSave)

        // 원래의 배경과 포커스를 받은 배경을 가져옴
        val originalBackground = ContextCompat.getDrawable(requireContext(), R.drawable.edittext_border)
        val focusedBackground = ContextCompat.getDrawable(requireContext(), R.drawable.click_edittext_border)

        // 이전에 저장된 사용자 정보 불러오기
        //sharedPreferences.run{}
        val nickname = sharedPreferences.getString("nickname", "")
        val gender = sharedPreferences.getString("gender", "")
        val age = sharedPreferences.getInt("age", 0)
        val height = sharedPreferences.getInt("height", 0)
        val weight = sharedPreferences.getInt("weight", 0)
        val targetWeight = sharedPreferences.getInt("targetWeight", 0)





        // EditText 포커스 변경 이벤트 처리
        val editTextList = listOf<EditText>(
            view.findViewById(R.id.Age),
            view.findViewById(R.id.Height),
            view.findViewById(R.id.Weight),
            view.findViewById(R.id.TargetWeight)
        )
        editTextList.forEach { editText ->
            editText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    editText.background = focusedBackground
                } else {
                    editText.background = originalBackground
                }
            }
        }

        // 화면에 사용자 정보 설정
        view.findViewById<TextView>(R.id.Nickname).text = nickname
        view.findViewById<RadioButton>(if (gender == "남자") R.id.BtnMan else R.id.BtnWoman).isChecked = true
        view.findViewById<EditText>(R.id.Age).setText(age.toString())
        view.findViewById<EditText>(R.id.Height).setText(height.toString())
        view.findViewById<EditText>(R.id.Weight).setText(weight.toString())
        view.findViewById<EditText>(R.id.TargetWeight).setText(targetWeight.toString())

        // 저장 버튼 클릭 시 동작
        btnSave.setOnClickListener {
            // 수정된 사용자 정보 저장
            val editedGender = if (view.findViewById<RadioButton>(R.id.BtnMan).isChecked) "남자" else "여자"
            val editedAge = view.findViewById<EditText>(R.id.Age).text.toString().toInt()
            val editedHeight = view.findViewById<EditText>(R.id.Height).text.toString().toInt()
            val editedWeight = view.findViewById<EditText>(R.id.Weight).text.toString().toInt()
            val editedTargetWeight = view.findViewById<EditText>(R.id.TargetWeight).text.toString().toInt()

            // SharedPreferences에 저장
            sharedPreferences.edit().apply {
                putString("gender", editedGender)
                putInt("age", editedAge)
                putInt("height", editedHeight)
                putInt("weight", editedWeight)
                putInt("targetWeight", editedTargetWeight)
                apply()
            }

            // BmiFragment로 이동
            val bmiFragment = BmiFragment.newInstance("", "")
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.mainFrameLayout, bmiFragment, TAG_BMI)
                addToBackStack(TAG_BMI)
                commit()
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
