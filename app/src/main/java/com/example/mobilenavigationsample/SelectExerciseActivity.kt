package com.example.mobilenavigationsample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SelectExerciseActivity : AppCompatActivity() {

    private var findCheckExerciseButton:Boolean = false  //버튼이 클릭되었는지를 확인
    private lateinit var selectedExerciseType:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_exercise)

        //인텐트 받아와 실행됨-----------------------------------
        val intent = intent



        //---------------------------------------

        title="운동 선택하기"

        var mExerciseList = arrayOf("산책","풋살","다목적체육관","런닝","스케이트보드"
        ,"수영","게이트볼","체력단련","인라인 스케이트","물놀이","다목적 운동기구","배드민턴"
        ,"족구","골프","테니스","탁구","등산","야외 운동기구","승마","국궁","자전거"
        ,"그라운드 골프","썰매","체력단련장")

        var selectExerciseList = findViewById<View>(R.id.selectExerciseListView) as ListView
        var selectExerciseButton = findViewById<Button>(R.id.selectExerciseButton) as Button
        var adapter : ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_single_choice
        ,mExerciseList)

        selectExerciseList.adapter = adapter
        selectExerciseList.choiceMode = ListView.CHOICE_MODE_SINGLE
        selectExerciseList.setOnItemClickListener {
           parent, view, position, id -> applicationContext
            selectedExerciseType = mExerciseList[position]
            findCheckExerciseButton = true
        }



        selectExerciseButton.setOnClickListener{
            if(!findCheckExerciseButton){  //아무것도 클릭되어있지 않을 때 toast 띄움
                Toast.makeText(this,"어떤 운동을 하실 것인지 체크해주세요!",Toast.LENGTH_SHORT).show()
            }else{
               // val intent = Intent(applicationContext,TimerActivity::class.java)
//                intent.putExtra("EXERCISE_TYPE",selectedExerciseType)
//                startActivity(intent)
            }
        }
    }



}