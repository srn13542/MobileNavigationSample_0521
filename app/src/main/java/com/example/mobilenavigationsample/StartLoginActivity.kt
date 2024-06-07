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
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class StartLoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var emailEt: EditText
    lateinit var passwordEt: EditText
    lateinit var loginBtn: Button
    lateinit var auth: FirebaseAuth


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


        auth = FirebaseAuth.getInstance()
        emailEt = findViewById(R.id.email_et)
        passwordEt = findViewById(R.id.pwd_et)
        loginBtn = findViewById(R.id.loginButton)



        val originalBackgroundId = emailEt.background
        val originalBackgroundPw = passwordEt.background
        val focusedBackground = ContextCompat.getDrawable(this, R.drawable.click_edittext_border)



        loginBtn.setOnClickListener {
            val email = emailEt.text.toString().trim()
            val password = passwordEt.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, FirstOptionsActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

            } else Toast.makeText(this, "아이디와 비밀번호를 입력해주세요 .", Toast.LENGTH_SHORT).show()

        }


        // 포커스 시 동작

        emailEt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                passwordEt.requestFocus()
                true
            } else {
                false
            }
        }

        passwordEt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginBtn.performClick()
                true
            } else {
                false
            }
        }

//        loginBtn.setOnClickListener {
//            onLoginClick()
//        }

        emailEt.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                emailEt.background = focusedBackground
                passwordEt.background = originalBackgroundPw
            } else {
                emailEt.background = originalBackgroundId
            }
        }

        passwordEt.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                passwordEt.background = focusedBackground
                emailEt.background = originalBackgroundId
            } else {
                passwordEt.background = originalBackgroundPw
            }
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