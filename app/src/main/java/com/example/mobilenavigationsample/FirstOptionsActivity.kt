package com.example.mobilenavigationsample

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class FirstOptionsActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_options)

        // SharedPreferences 초기화
        sharedPreferences = getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
        val btnSave: Button = findViewById(R.id.buttonSave)

        // 저장 버튼 클릭 시 동작 설정
        btnSave.setOnClickListener {
            saveUserInfoAndNavigate()
        }

        // EditText 포커스 시 배경 변경
        setupEditTextFocusListeners()
    }

    // 사용자 정보를 저장하고 NaviActivity로 이동
    private fun saveUserInfoAndNavigate() {
        val editedNickname = findViewById<EditText>(R.id.Nickname).text.toString()
        val editedGender = if (findViewById<RadioButton>(R.id.BtnMan).isChecked) "남자" else "여자"
        val editedAge = findViewById<EditText>(R.id.Age).text.toString().toInt()
        val editedHeight = findViewById<EditText>(R.id.Height).text.toString().toInt()
        val editedWeight = findViewById<EditText>(R.id.Weight).text.toString().toInt()
        val editedTargetWeight = findViewById<EditText>(R.id.TargetWeight).text.toString().toInt()

        // 사용자 정보 저장
        sharedPreferences.edit().apply {
            putString("nickname", editedNickname)
            putString("gender", editedGender)
            putInt("age", editedAge)
            putInt("height", editedHeight)
            putInt("weight", editedWeight)
            putInt("targetWeight", editedTargetWeight)
            putBoolean("isLoggedIn", true)
            apply()
        }

        // NaviActivity로 이동
        val intent = Intent(this, NaviActivity::class.java)
        startActivity(intent)
        finish()
    }

    // EditText 포커스 시 배경 변경 설정
    private fun setupEditTextFocusListeners() {
        val editTextNickname = findViewById<EditText>(R.id.Nickname)
        val editTextAge = findViewById<EditText>(R.id.Age)
        val editTextHeight = findViewById<EditText>(R.id.Height)
        val editTextWeight = findViewById<EditText>(R.id.Weight)
        val editTextTargetWeight = findViewById<EditText>(R.id.TargetWeight)

        val originalBackground = editTextNickname.background
        val focusedBackground = ContextCompat.getDrawable(this, R.drawable.click_edittext_border)

        // 각 EditText 포커스 변경 시 배경 변경
        listOf(editTextNickname, editTextAge, editTextHeight, editTextWeight, editTextTargetWeight).forEach { editText ->
            editText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                editText.background = if (hasFocus) focusedBackground else originalBackground
            }
        }
    }
}
