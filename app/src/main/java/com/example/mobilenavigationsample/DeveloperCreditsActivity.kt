package com.example.mobilenavigationsample

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DeveloperCreditsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer_credits)

        // showCreditsButton 찾기
        val showCreditsButton = findViewById<Button>(R.id.showCreditsButton)

        // 클릭 리스너 설정
        showCreditsButton.setOnClickListener {
            // 다이얼로그 생성
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_credit)

            // 출처 텍스트 설정
            val creditsTextView = dialog.findViewById<TextView>(R.id.creditsTextView)
            val imageCredits =
                "출처 1\n" +
                        "이미지 출처 2\n" +
                        "이미지 출처 3"
            creditsTextView.text = imageCredits

            // 다이얼로그 표시
            dialog.show()
        }
    }
}
