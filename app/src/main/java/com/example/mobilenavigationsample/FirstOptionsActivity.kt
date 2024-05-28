package com.example.mobilenavigationsample

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class FirstOptionsActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_options)

        sharedPreferences = getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
        val btnSave: Button = findViewById(R.id.buttonSave)

        btnSave.setOnClickListener {
            // 수정된 사용자 정보 저장하기
            val editedNickname = findViewById<EditText>(R.id.Nickname).text.toString()
            val editedGender = if (findViewById<RadioButton>(R.id.BtnMan).isChecked) "남자" else "여자"
            val editedAge = findViewById<EditText>(R.id.Age).text.toString().toInt()
            val editedHeight = findViewById<EditText>(R.id.Height).text.toString().toInt()
            val editedWeight = findViewById<EditText>(R.id.Weight).text.toString().toInt()
            val editedTargetWeight = findViewById<EditText>(R.id.TargetWeight).text.toString().toInt()

            val editor = sharedPreferences.edit()
            editor.putString("nickname", editedNickname)
            editor.putString("gender", editedGender)
            editor.putInt("age", editedAge)
            editor.putInt("height", editedHeight)
            editor.putInt("weight", editedWeight)
            editor.putInt("targetWeight", editedTargetWeight)
            editor.putBoolean("isLoggedIn", true)
            editor.apply()

            val intent = Intent(this, NaviActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 수정된 부분 시작
        val editTextNickname = findViewById<EditText>(R.id.Nickname)
        val editTextGender = findViewById<RadioGroup>(R.id.radioGroupGender)
        val editTextAge = findViewById<EditText>(R.id.Age)
        val editTextHeight = findViewById<EditText>(R.id.Height)
        val editTextWeight = findViewById<EditText>(R.id.Weight)
        val editTextTargetWeight = findViewById<EditText>(R.id.TargetWeight)

        val originalBackground = editTextNickname.background

        val focusedBackground = ContextCompat.getDrawable(this, R.drawable.click_edittext_border)

        editTextNickname.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextNickname.background = focusedBackground
                editTextGender.background = originalBackground
                editTextAge.background = originalBackground
                editTextHeight.background = originalBackground
                editTextWeight.background = originalBackground
                editTextTargetWeight.background = originalBackground
            } else {
                editTextNickname.background = originalBackground
            }
        }

        editTextGender.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextGender.background = focusedBackground
                editTextNickname.background = originalBackground
                editTextAge.background = originalBackground
                editTextHeight.background = originalBackground
                editTextWeight.background = originalBackground
                editTextTargetWeight.background = originalBackground
            } else {
                editTextGender.background = originalBackground
            }
        }

        editTextAge.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextAge.background = focusedBackground
                editTextNickname.background = originalBackground
                editTextGender.background = originalBackground
                editTextHeight.background = originalBackground
                editTextWeight.background = originalBackground
                editTextTargetWeight.background = originalBackground
            } else {
                editTextAge.background = originalBackground
            }
        }

        editTextHeight.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextHeight.background = focusedBackground
                editTextNickname.background = originalBackground
                editTextGender.background = originalBackground
                editTextAge.background = originalBackground
                editTextWeight.background = originalBackground
                editTextTargetWeight.background = originalBackground
            } else {
                editTextHeight.background = originalBackground
            }
        }

        editTextWeight.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextWeight.background = focusedBackground
                editTextNickname.background = originalBackground
                editTextGender.background = originalBackground
                editTextAge.background = originalBackground
                editTextHeight.background = originalBackground
                editTextTargetWeight.background = originalBackground
            } else {
                editTextWeight.background = originalBackground
            }
        }

        editTextTargetWeight.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextTargetWeight.background = focusedBackground
                editTextNickname.background = originalBackground
                editTextGender.background = originalBackground
                editTextAge.background = originalBackground
                editTextHeight.background = originalBackground
                editTextWeight.background = originalBackground
            } else {
                editTextTargetWeight.background = originalBackground
            }
        }
        // 수정된 부분 끝
    }
}
