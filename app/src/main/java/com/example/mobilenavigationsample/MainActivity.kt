package com.example.mobilenavigationsample

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 로그인 화면(StartLoginActivity)으로 이동
        val intent = Intent(this, StartLoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
