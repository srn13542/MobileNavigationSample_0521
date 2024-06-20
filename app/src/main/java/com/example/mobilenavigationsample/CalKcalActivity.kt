package com.example.mobilenavigationsample

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge

class CalKcalActivity : AppCompatActivity() {

    private lateinit var exerciseType: String
    private lateinit var exerciseTime: String // hh:mm:ss 형식
    private var kcalPerMinute: Int = 0
    private var totalKcal: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cal_kcal)

        // 인텐트로부터 데이터 받기
        exerciseType = intent.getStringExtra("selected_exercise") ?: "Unknown"
        exerciseTime = intent.getStringExtra("exercise_time") ?: "00:00:00"

        // 분당 칼로리 소모량을 설정 (여기서 모든 운동 종류에 대해 설정)
        kcalPerMinute = when (exerciseType) {
            "풋살" -> 7
            "하이킹" -> 7
            "다목적체육관" -> 0
            "산책" -> 4
            "런닝" -> 10
            "스케이트보드" -> 5
            "게이트볼" -> 3
            "수영" -> 5
            "체력단련" -> 0
            "인라인 스케이트" -> 9
            "물놀이" -> 6
            "배드민턴" -> 5
            "다목적 운동기구" -> 5
            "족구" -> 7
            "농구" -> 7
            "골프" -> 4
            "탁구" -> 3
            "등산" -> 6
            "야외 운동기구" -> 5
            "달리기" -> 7
            "승마" -> 5
            "국궁" -> 4
            "테니스" -> 5
            "자전거" -> 7
            "야구" -> 3
            "그라운드 골프" -> 2
            "워터파크" -> 5
            "썰매" -> 5
            "축구" -> 8
            else -> 0
        }

        // 총 칼로리 계산
        totalKcal = calculateTotalKcal(exerciseTime, kcalPerMinute)

        // 결과를 TimerActivity로 반환
        val resultIntent = Intent()
        resultIntent.putExtra("total_kcal", totalKcal)
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    private fun calculateTotalKcal(exerciseTime: String, kcalPerMinute: Int): Int {
        val timeParts = exerciseTime.split(":")
        val hours = timeParts[0].toInt()
        val minutes = timeParts[1].toInt()
        val totalMinutes = (hours * 60) + minutes
        return totalMinutes * kcalPerMinute
    }
}
