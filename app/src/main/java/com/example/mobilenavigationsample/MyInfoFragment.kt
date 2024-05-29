package com.example.mobilenavigationsample

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
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
        val nickname = sharedPreferences.getString("nickname", "")
        val gender = sharedPreferences.getString("gender", "")
        val age = sharedPreferences.getInt("age", 0)
        val height = sharedPreferences.getInt("height", 0)
        val weight = sharedPreferences.getInt("weight", 0)
        val targetWeight = sharedPreferences.getInt("targetWeight", 0)


        // 포커스 변경 이벤트 처리
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
        view.findViewById<TextView>(R.id.Nickname).apply {
            text = nickname
        }
        if (gender == "남자") {
            view.findViewById<RadioButton>(R.id.BtnMan).isChecked = true
        } else {
            view.findViewById<RadioButton>(R.id.BtnWoman).isChecked = true
        }
        view.findViewById<EditText>(R.id.Age).apply {
            setText(age.toString())
            isEnabled = true // EditText 수정 가능하도록 변경
        }
        view.findViewById<EditText>(R.id.Height).apply {
            setText(height.toString())
            isEnabled = true // EditText 수정 가능하도록 변경
        }
        view.findViewById<EditText>(R.id.Weight).apply {
            setText(weight.toString())
            isEnabled = true // EditText 수정 가능하도록 변경
        }
        view.findViewById<EditText>(R.id.TargetWeight).apply {
            setText(targetWeight.toString())
            isEnabled = true // EditText 수정 가능하도록 변경
        }

        btnSave.setOnClickListener {
            // 수정된 사용자 정보 저장하기
            val editedGender = if (view.findViewById<RadioButton>(R.id.BtnMan).isChecked) "남자" else "여자"
            val editedAge = view.findViewById<EditText>(R.id.Age).text.toString().toInt()
            val editedHeight = view.findViewById<EditText>(R.id.Height).text.toString().toInt()
            val editedWeight = view.findViewById<EditText>(R.id.Weight).text.toString().toInt()
            val editedTargetWeight = view.findViewById<EditText>(R.id.TargetWeight).text.toString().toInt()

            val editor = sharedPreferences.edit()
            editor.putString("gender", editedGender)
            editor.putInt("age", editedAge)
            editor.putInt("height", editedHeight)
            editor.putInt("weight", editedWeight)
            editor.putInt("targetWeight", editedTargetWeight)
            editor.apply()

            // BmiFragment로 이동
            val bmiFragment = BmiFragment.newInstance("", "")
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.mainFrameLayout, bmiFragment, TAG_BMI)
            transaction.addToBackStack(TAG_BMI)
            transaction.commit()
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
