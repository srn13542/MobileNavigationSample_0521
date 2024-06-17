package com.example.mobilenavigationsample

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import android.util.Log

class TimerActivity : AppCompatActivity() {

    private lateinit var timeView: TextView
    private lateinit var date: TextView
    private lateinit var saveButton: Button
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button
    private var handler: Handler = Handler()
    private var startTime: Long = 0L
    private var timeInMilliseconds: Long = 0L
    private var timeSwapBuff: Long = 0L
    private var updateTime: Long = 0L
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private var running = false

    private val updateTimer: Runnable = object : Runnable {
        override fun run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime
            updateTime = timeSwapBuff + timeInMilliseconds
            val secs = (updateTime / 1000).toInt()
            val mins = secs / 60
            val hours = mins / 60
            timeView.text = String.format("%02d:%02d:%02d", hours, mins % 60, secs % 60)
            handler.postDelayed(this, 0)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        // Firebase 초기화 필요
        FirebaseApp.initializeApp(this)

        // 피이어베이스 연결
        sharedPreferences = getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val today = getTodayDate() // 오늘 날짜 저장
        date = findViewById(R.id.date) // xml연동
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // 날짜 형식화
        date.text = today.format(formatter) // 날짜 화면에 보이기

        timeView = findViewById(R.id.timeView)
        saveButton = findViewById(R.id.saveButton)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        resetButton = findViewById(R.id.resetButton)

        startButton.setOnClickListener {
            if (!running) {
                startTime = SystemClock.uptimeMillis()
                handler.postDelayed(updateTimer, 0)
                running = true
            }
        }

        stopButton.setOnClickListener {
            if (running) {
                timeSwapBuff += timeInMilliseconds
                handler.removeCallbacks(updateTimer)
                running = false
                saveTime(updateTime)  // Save the time when stopping
            }
        }

        resetButton.setOnClickListener {
            startTime = 0L
            timeInMilliseconds = 0L
            timeSwapBuff = 0L
            updateTime = 0L
            timeView.text = "00:00:00"
            handler.removeCallbacks(updateTimer)
            running = false
        }

        saveButton.setOnClickListener{
            val date = getTodayDate().format(formatter)
            val exerciseType : String = "running" // 테스트용 예시
            val exerciseRecord = timeView.text.toString()
            val kcal : Int = 100 //테스트용 예시값

            val user = hashMapOf(
                "date" to date,
                "exerciseRecord" to exerciseRecord,
                "exerciseType" to exerciseType,
                "kcal" to kcal
            )

            val currentUser = auth.currentUser
            if (currentUser != null) {
                firestore.collection("record").document(currentUser.uid)
                    .set(user)
                    .addOnSuccessListener {
                        Log.d("Firestore", "DocumentSnapshot successfully written!")
                        Toast.makeText(this, "정보가 저장되었습니다.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, ReportFragment::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Log.w("Firestore", "Error writing document", e)
                        Toast.makeText(this, "정보 저장에 실패했습니다: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Log.w("Firestore", "No authenticated user")
                Toast.makeText(this, "운동 기록 저장 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveTime(time: Long) {
        val sharedPreferences = getSharedPreferences("stopwatch", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong("saved_time", time)
        editor.apply()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTodayDate(): LocalDate {
        return LocalDate.now()
    }
}
