package com.org.mobilenavigationsample

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SelectExerciseActivity : AppCompatActivity() {

    private var findCheckExerciseButton: Boolean = false  //버튼이 클릭되었는지를 확인
    private lateinit var selectedExerciseType: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_exercise)

        title = "운동 선택하기"

//        val locationName = intent.getStringExtra("location_name") ?: ""
//        val latitude = intent.getDoubleExtra("latitude", 0.0)
//        val longitude = intent.getDoubleExtra("longitude", 0.0)
        selectedExerciseType = intent.getStringExtra("Exercise") ?: ""

        // UI 요소 초기화
        val listView: ListView = findViewById(R.id.selectExerciseListView)

        // 운동 종목 데이터 설정
        val exerciseList = selectedExerciseType.split(", ") // 운동 종목이 쉼표와 공백으로 구분되어 있을 경우 분리
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, exerciseList)
        listView.adapter = adapter

        // 리스트뷰 아이템 클릭 리스너 설정
        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as String
            // 선택한 운동 종목을 기준으로 다음 화면으로 이동
            navigateToExerciseDetail(selectedItem)
        }

    }

    private fun navigateToExerciseDetail(selectedExercise: String) {
        // 선택한 운동 종목을 가지고 ExerciseDetailActivity로 이동하는 Intent 설정
        val intent = Intent(this, TimerActivity::class.java).apply {
            putExtra("selected_exercise", selectedExercise)
        }
        startActivity(intent)
    }
}