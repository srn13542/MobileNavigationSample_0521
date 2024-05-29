package com.example.mobilenavigationsample


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        val buttonSignUp = findViewById<Button>(R.id.buttonSignUp)

        // 원래의 배경과 포커스를 받은 배경을 가져옴
        val originalBackground = ContextCompat.getDrawable(this, R.drawable.edittext_border)
        val focusedBackground = ContextCompat.getDrawable(this, R.drawable.click_edittext_border)

        buttonSignUp.setOnClickListener {
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
                // 비밀번호 일치
                // 사용자 정보를 데이터베이스에 저장하거나 서버로 전송
                Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                // StartLoginActivity로 돌아가기
                val intent = Intent(this, StartLoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // 비밀번호 불일치
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        // 포커스 변경 이벤트 처리
        val editTextList = listOf<EditText>(
            findViewById(R.id.editTextName),
            findViewById(R.id.editTextEmail),
            findViewById(R.id.editTextPhone),
            findViewById(R.id.editTextUsername),
            findViewById(R.id.editTextPassword),
            findViewById(R.id.editTextConfirmPassword)
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
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // 뒤로 가기를 눌렀을 때 StartLoginActivity로 돌아가기
        val intent = Intent(this, StartLoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}