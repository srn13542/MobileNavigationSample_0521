package com.example.mobilenavigationsample

//타이머 참조 사이트 https://velog.io/@723poil/%EC%8B%9C%EA%B3%84-%EC%95%B1%EC%9D%98-%EC%8A%A4%ED%86%B1%EC%9B%8C%EC%B9%98-%EA%B8%B0%EB%8A%A5-%EB%A7%8C%EB%93%A4%EA%B8%B0

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter


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

            val today = getTodayDate() // 오늘 날짜 저장
            date = findViewById(R.id.date)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        date.text = today.format(formatter)


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
            val today = getTodayDate()
            

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
