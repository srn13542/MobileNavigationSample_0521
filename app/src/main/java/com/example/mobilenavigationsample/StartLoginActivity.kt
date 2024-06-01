package com.example.mobilenavigationsample

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StartLoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // SharedPreferences 초기화
        sharedPreferences = getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

        val editTextId = findViewById<EditText>(R.id.editTextId)
        val editTextPw = findViewById<EditText>(R.id.editTextPw)
        val loginButton = findViewById<Button>(R.id.loginButton)

        // EditText 원래와 포커스된 상태의 배경 설정
        val originalBackgroundId = editTextId.background
        val originalBackgroundPw = editTextPw.background
        val focusedBackground = ContextCompat.getDrawable(this, R.drawable.click_edittext_border)

        // Id 입력 시 엔터 키 동작 설정
        editTextId.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                editTextPw.requestFocus()
                true
            } else {
                false
            }
        }

        // Pw 입력 시 엔터 키 동작 설정
        editTextPw.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginButton.performClick()
                true
            } else {
                false
            }
        }

        // 로그인 버튼 클릭 이벤트 설정
        loginButton.setOnClickListener {
            onLoginClick()
        }

        // Id EditText 포커스 변경 시 배경 변경
        editTextId.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextId.background = focusedBackground
                editTextPw.background = originalBackgroundPw
            } else {
                editTextId.background = originalBackgroundId
            }
        }

        // Pw EditText 포커스 변경 시 배경 변경
        editTextPw.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextPw.background = focusedBackground
                editTextId.background = originalBackgroundId
            } else {
                editTextPw.background = originalBackgroundPw
            }
        }
    }

    // 로그인 버튼 클릭 시 동작
    private fun onLoginClick() {
        // 테스트용 Toast 메시지 표시
        Toast.makeText(this, "로그인 버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show()

        // 이전에 로그인한 적이 있는지 확인
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            // 이전 로그인 기록이 있으면 NaviActivity로 이동
            val intent = Intent(this, NaviActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // 처음 로그인하는 경우 FirstOptionsActivity로 이동
            val intent = Intent(this, FirstOptionsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // 비밀번호 찾기 화면으로 이동
    fun onFindPw(view: View) {
        val intent = Intent(this, FindPwActivity::class.java)
        startActivity(intent)
    }

    // 아이디 찾기 화면으로 이동
    fun onFindId(view: View) {
        val intent = Intent(this, FindIdActivity::class.java)
        startActivity(intent)
    }

    // 회원가입 화면으로 이동
    fun onSignUp(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}
