package com.example.mobilenavigationsample


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FindIdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_find_id)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        val buttonSave = findViewById<Button>(R.id.buttonSave)

        // 포커스 받았을 때 테두리 색 변화
        editTextName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextName.background = ContextCompat.getDrawable(this, R.drawable.click_edittext_border)
                editTextEmail.background = ContextCompat.getDrawable(this, R.drawable.edittext_border)
                editTextPhone.background = ContextCompat.getDrawable(this, R.drawable.edittext_border)
            }
        }
        editTextEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextEmail.background = ContextCompat.getDrawable(this, R.drawable.click_edittext_border)
                editTextName.background = ContextCompat.getDrawable(this, R.drawable.edittext_border)
                editTextPhone.background = ContextCompat.getDrawable(this, R.drawable.edittext_border)
            }
        }
        editTextPhone.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextPhone.background = ContextCompat.getDrawable(this, R.drawable.click_edittext_border)
                editTextName.background = ContextCompat.getDrawable(this, R.drawable.edittext_border)
                editTextEmail.background = ContextCompat.getDrawable(this, R.drawable.edittext_border)
            }
        }

        buttonSave.setOnClickListener {
            // 버튼 클릭 시 동작 추가
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

