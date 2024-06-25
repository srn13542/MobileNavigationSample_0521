package com.org.mobilenavigationsample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class SignUpActivity : AppCompatActivity() {

    lateinit var emailEt: EditText
    lateinit var passwordEt: EditText
    lateinit var confirmPasswordEt: EditText
    lateinit var signupBtn: Button
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()
        emailEt = findViewById(R.id.email_et)
        passwordEt = findViewById(R.id.pwd_et)
        confirmPasswordEt = findViewById(R.id.re_pwd_et)
        signupBtn = findViewById(R.id.signupbutton)

        signupBtn.setOnClickListener {
            val email = emailEt.text.toString().trim()
            val password = passwordEt.text.toString().trim()
            val confirmPassword = confirmPasswordEt.text.toString()

            if (password.length < 6) {
                Toast.makeText(this, "비밀번호를 6자리 이상 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password == confirmPassword) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "회원가입에 성공했습니다!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, StartLoginActivity::class.java)
                            startActivity(intent)
                            finish()
                            // 회원가입 성공 시 로그인 화면으로 이동
                        } else {
                            try {
                                throw task.exception ?: Exception("회원가입 실패")
                            } catch (e: FirebaseAuthUserCollisionException) {
                                Toast.makeText(
                                    this,
                                    "이미 존재하는 계정입니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } catch (e: FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(
                                    this,
                                    "이메일 형식이 잘못되었습니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } catch (e: Exception) {
                                Toast.makeText(
                                    this,
                                    "회원가입 실패: ${e.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
            } else {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
