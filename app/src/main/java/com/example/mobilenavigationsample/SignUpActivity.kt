package com.example.mobilenavigationsample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        // 배경 Drawable 초기화
        val originalBackground = ContextCompat.getDrawable(this, R.drawable.edittext_border)
        val focusedBackground = ContextCompat.getDrawable(this, R.drawable.click_edittext_border)

        // 회원가입 버튼 초기화
        val buttonSignUp = findViewById<Button>(R.id.buttonSignUp)
        buttonSignUp.setOnClickListener {
            handleSignUp()
        }

        // EditText 포커스 변경 이벤트 처리
        val editTextList = listOf(
            findViewById<EditText>(R.id.editTextName),
            findViewById<EditText>(R.id.editTextEmail),
            findViewById<EditText>(R.id.editTextPhone),
            findViewById<EditText>(R.id.editTextUsername),
            findViewById<EditText>(R.id.editTextPassword),
            findViewById<EditText>(R.id.editTextConfirmPassword)
        )
        editTextList.forEach { editText ->
            editText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                editText.background = if (hasFocus) focusedBackground else originalBackground
            }
        }
    }

//    val name = MutableStateFlow("")
//    val email = MutableStateFlow("")
//    val phone = MutableStateFlow("")
//    val username = MutableStateFlow("")
//    val password = MutableStateFlow("")
//    val enabledSingUp = combine(name, email, phone, username, password) { n, e, p, u, p2 ->
//        n.length >= 2 && e.length >= 6 && p.length >= 11 && u.length >= 4 && p2.length >= 8
//    }.stateIn(lifecycleScope, SharingStarted.WhileSubscribed(), false)

    //회원가입 처리 함수
    private fun handleSignUp() {
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        val editTextUsername = findViewById<EditText>(R.id.editTextUsername)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val editTextConfirmPassword = findViewById<EditText>(R.id.editTextConfirmPassword)

        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
        val phone = editTextPhone.text.toString()
        val username = editTextUsername.text.toString()
        val password = editTextPassword.text.toString()
        val confirmPassword = editTextConfirmPassword.text.toString()

        if (password == confirmPassword) {
            // 비밀번호 일치 시 회원가입 완료 메시지 표시 및 로그인 화면으로 이동
            Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, StartLoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // 비밀번호 불일치 시 오류 메시지 표시
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // 뒤로 가기를 눌렀을 때 로그인 화면으로 돌아가기
        val intent = Intent(this, StartLoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
