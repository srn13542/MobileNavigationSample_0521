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

        val originalBackgroundId = editTextId.background
        val originalBackgroundPw = editTextPw.background
        val focusedBackground = ContextCompat.getDrawable(this, R.drawable.click_edittext_border)

        editTextId.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                editTextPw.requestFocus()
                true
            } else {
                false
            }
        }

        editTextPw.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginButton.performClick()
                true
            } else {
                false
            }
        }

        loginButton.setOnClickListener {
            onLoginClick()
        }

        editTextId.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextId.background = focusedBackground
                editTextPw.background = originalBackgroundPw
            } else {
                editTextId.background = originalBackgroundId
            }
        }

        editTextPw.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextPw.background = focusedBackground
                editTextId.background = originalBackgroundId
            } else {
                editTextPw.background = originalBackgroundPw
            }
        }
    }

    private fun onLoginClick() {
        // 테스트 확인을 위해 작성. (나중엔 뺄 것)
        Toast.makeText(this, "로그인 버튼이 클릭되었습니다.", Toast.LENGTH_SHORT).show()

        // 이전에 로그인한 적이 있는지 확인
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            // 이미 로그인한 적이 있으면 네비게이션 화면으로 이동
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

    fun onFindPw(view: View) {
        val intent = Intent(this, FindPwActivity::class.java)
        startActivity(intent)
    }

    fun onFindId(view: View) {
        val intent = Intent(this, FindIdActivity::class.java)
        startActivity(intent)
    }

    fun onSignUp(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

}
