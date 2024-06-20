package mobilenavigationsample.example.mobilenavigationsample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import mobilenavigationsample.example.mobilenavigationsample.R
import java.text.SimpleDateFormat
import java.util.*

class ReportFragment : Fragment() {

    private lateinit var datePicker: DatePicker
    private lateinit var exerciseTextView: TextView
    private lateinit var timeTextView: TextView
    private lateinit var caloriesTextView: TextView
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_report, container, false)

        datePicker = view.findViewById(R.id.date_picker)
        exerciseTextView = view.findViewById(R.id.TextViewExercise)
        timeTextView = view.findViewById(R.id.time_header)
        caloriesTextView = view.findViewById(R.id.TextViewCalories)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val today = Calendar.getInstance()
        fetchExerciseData(today.time)

        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)) { _, year, month, day ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, day)
            fetchExerciseData(selectedDate.time)
        }

        return view
    }

    private fun fetchExerciseData(date: Date) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
            firestore.collection("record").document(currentUser.uid)
                .collection("userRecord")
                .whereEqualTo("date", formattedDate)
                .get()
                .addOnSuccessListener { documents ->
                    var exerciseText = ""
                    var timeText = ""
                    var caloriesText = ""
                    for (document in documents) {
                        exerciseText += document.getString("exerciseType") + "\n"
                        timeText += document.getString("exerciseRecord") + "\n"
                        caloriesText += document.getLong("kcal").toString() + " kcal\n"
                    }
                    exerciseTextView.text = exerciseText.trim()
                    timeTextView.text = timeText.trim()
                    caloriesTextView.text = caloriesText.trim()
                }
                .addOnFailureListener { exception ->
                    Log.w("Firestore", "Error getting documents: ", exception)
                }
        }
    }
}