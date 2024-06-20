package mobilenavigationsample.example.mobilenavigationsample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mobilenavigationsample.example.mobilenavigationsample.R

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

        // 배경 Drawable 초기화
        val originalBackground = ContextCompat.getDrawable(this, R.drawable.edittext_border)
        val focusedBackground = ContextCompat.getDrawable(this, R.drawable.click_edittext_border)

        // EditText 리스트 초기화
        val editTextList = listOf(
            findViewById<EditText>(R.id.editTextName),
            findViewById<EditText>(R.id.editTextEmail),
            findViewById<EditText>(R.id.editTextPhone)
        )

        // 포커스 변경 이벤트 처리
        editTextList.forEach { editText ->
            editText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    editText.background = focusedBackground
                } else {
                    editText.background = originalBackground
                }
            }
        }

        // 버튼 클릭 리스너 설정
        val buttonSave = findViewById<Button>(R.id.buttonSave)
        buttonSave.setOnClickListener {
            val name = findViewById<EditText>(R.id.editTextName).text.toString()
            val email = findViewById<EditText>(R.id.editTextEmail).text.toString()
            val phone = findViewById<EditText>(R.id.editTextPhone).text.toString()

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                // 입력이 모두 되어있지 않을 경우 경고 메시지 출력
                Toast.makeText(this, "모두 입력해 주세요.", Toast.LENGTH_SHORT).show()
            } else {
                // 입력된 데이터를 저장하거나 전송하는 로직 추가 필요
                Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // 뒤로 가기를 눌렀을 때 StartLoginActivity로 이동
        val intent = Intent(this, StartLoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
