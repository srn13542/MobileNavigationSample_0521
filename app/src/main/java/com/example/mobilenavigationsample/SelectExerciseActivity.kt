package com.example.mobilenavigationsample

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
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

        title="운동 선택하기"

        var mExersiceList = arrayOf("산책","풋살","다목적체육관","런닝","스케이트보드"
        ,"수영","게이트볼","체력단련","인라인 스케이트","물놀이","다목적 운동기구","배드민턴"
        ,"족구","골프","테니스","탁구","등산","야외 운동기구","승마","국궁","자전거"
        ,"그라운드 골프","썰매","체력단련장")

        var selectExerciseList = findViewById<View>(R.id.selectExerciseListView) as ListView

        var adapter : ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1
        ,mExersiceList)

        selectExerciseList.adapter = adapter

        selectExerciseList.setOnClickListener {
            //override fun onIt

           // selectedExerciseType = mExersiceList[position]
            findCheckExerciseButton = true


        }



        selectExerciseList.setOnClickListener{
            if(findCheckExerciseButton != true){
                Toast.makeText(this,"어떤 운동을 하실 것인지 체크해주세요!",Toast.LENGTH_SHORT).show()
            }else{
                //클릭시, 타이머로 넘어감
            }
        }







    }
}