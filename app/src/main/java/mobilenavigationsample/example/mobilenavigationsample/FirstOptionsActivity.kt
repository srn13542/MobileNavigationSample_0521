package mobilenavigationsample.example.mobilenavigationsample

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import mobilenavigationsample.example.mobilenavigationsample.R

class FirstOptionsActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_options)

        sharedPreferences = getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userDocRef = firestore.collection("User").document(currentUser.uid)
            userDocRef.get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        // 사용자 정보가 이미 존재하면 NaviActivity로 이동
                        val intent = Intent(this, NaviActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "사용자 정보를 확인하는 데 실패했습니다: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
        val btnSave: Button = findViewById(R.id.buttonSave)

        btnSave.setOnClickListener {
            // 사용자 정보 저장
            val editedNickname = findViewById<EditText>(R.id.Nickname).text.toString()
            val editedGender = if (findViewById<RadioButton>(R.id.BtnMan).isChecked) "남자" else "여자"
            val editedAge = findViewById<EditText>(R.id.Age).text.toString().toInt()
            val editedHeight = findViewById<EditText>(R.id.Height).text.toString().toInt()
            val editedWeight = findViewById<EditText>(R.id.Weight).text.toString().toInt()
            val editedTargetWeight =
                findViewById<EditText>(R.id.TargetWeight).text.toString().toInt()

            val user = hashMapOf(
                "nickname" to editedNickname,
                "gender" to editedGender,
                "age" to editedAge,
                "height" to editedHeight,
                "weight" to editedWeight,
                "targetWeight" to editedTargetWeight
            )

            if (currentUser != null) {
                firestore.collection("User").document(currentUser.uid)
                    .set(user)
                    .addOnSuccessListener {
                        Toast.makeText(this, "정보가 저장되었습니다.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, NaviActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "정보 저장에 실패했습니다: ${e.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
            } else {
                Toast.makeText(this, "사용자 인증 실패", Toast.LENGTH_SHORT).show()
            }
        }


        // 포커스 시 테두리 변경
        val editTextNickname = findViewById<EditText>(R.id.Nickname)
        val editTextAge = findViewById<EditText>(R.id.Age)
        val editTextHeight = findViewById<EditText>(R.id.Height)
        val editTextWeight = findViewById<EditText>(R.id.Weight)
        val editTextTargetWeight = findViewById<EditText>(R.id.TargetWeight)

        val originalBackground = editTextNickname.background
        val focusedBackground = ContextCompat.getDrawable(this, R.drawable.click_edittext_border)

        editTextNickname.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextNickname.background = focusedBackground
                editTextAge.background = originalBackground
                editTextHeight.background = originalBackground
                editTextWeight.background = originalBackground
                editTextTargetWeight.background = originalBackground
            } else {
                editTextNickname.background = originalBackground
            }
        }

        editTextAge.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextAge.background = focusedBackground
                editTextNickname.background = originalBackground
                editTextHeight.background = originalBackground
                editTextWeight.background = originalBackground
                editTextTargetWeight.background = originalBackground
            } else {
                editTextAge.background = originalBackground
            }
        }

        editTextHeight.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextHeight.background = focusedBackground
                editTextNickname.background = originalBackground
                editTextAge.background = originalBackground
                editTextWeight.background = originalBackground
                editTextTargetWeight.background = originalBackground
            } else {
                editTextHeight.background = originalBackground
            }
        }

        editTextWeight.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextWeight.background = focusedBackground
                editTextNickname.background = originalBackground
                editTextAge.background = originalBackground
                editTextHeight.background = originalBackground
                editTextTargetWeight.background = originalBackground
            } else {
                editTextWeight.background = originalBackground
            }
        }

        editTextTargetWeight.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextTargetWeight.background = focusedBackground
                editTextNickname.background = originalBackground
                editTextAge.background = originalBackground
                editTextHeight.background = originalBackground
                editTextWeight.background = originalBackground
            } else {
                editTextTargetWeight.background = originalBackground
            }
        }
    }
}