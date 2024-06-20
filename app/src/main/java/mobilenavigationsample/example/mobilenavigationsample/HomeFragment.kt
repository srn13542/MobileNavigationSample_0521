package mobilenavigationsample.example.mobilenavigationsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import mobilenavigationsample.example.mobilenavigationsample.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var burnedCalories = 0 // Used to store burned calories
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        // Fetch burned calories from Firestore
        fetchBurnedCalories()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)


        // Set food and calories based on burned calories
        setFoodAndCalories(view)

        return view
    }

    // Fetch burned calories from Firestore
    private fun fetchBurnedCalories() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            firestore.collection("record").document(currentUser.uid)
                .collection("userRecord")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        burnedCalories += document.getLong("kcal")?.toInt() ?: 0
                    }
                    view?.let { setFoodAndCalories(it) }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), "칼로리 데이터를 가져오는 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                }
        }
    }

    // 추가된 부분 (0618)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 뒤로가기 버튼 눌렀을 때 실행할 행동 정의
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showExitConfirmationDialog()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    // 다이얼로그를 띄우는 함수
    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(requireContext(), R.style.CustomExitDialogTheme)
            .setTitle("어플리케이션 종료")
            .setMessage("정말 종료하시겠습니까?")
            .setPositiveButton("종료") { dialog, which ->
                // 종료
                requireActivity().finish()
            }
            .setNegativeButton("취소") { dialog, which ->
                // 닫기
                dialog.dismiss()
            }
            .create()

            .show()
    }

    // Set food and calories based on burned calories
    private fun setFoodAndCalories(view: View) {
        val food: TextView = view.findViewById(R.id.Food)
        val calorie: TextView = view.findViewById(R.id.Calorie)

        calorie.text = burnedCalories.toString()

        // Set food text based on the range of burned calories
        val foodText = when {
            burnedCalories < 150 -> "탕후루 1개"
            burnedCalories < 200 -> "초코파이 1개"
            burnedCalories < 300 -> "피자 1조각"
            burnedCalories < 400 -> "포카칩 한 봉지"
            burnedCalories < 500 -> "초콜릿 케이크 1조각"
            burnedCalories < 550 -> "라면 1개"
            burnedCalories < 600 -> "빅맥 1개"
            burnedCalories < 700 -> "짬뽕 1그릇"
            burnedCalories < 800 -> "제육덮밥 1그릇"
            burnedCalories < 900 -> "짜장면 1그릇"
            burnedCalories < 1000 -> "다이제 1봉지"
            burnedCalories < 1100 -> "빅맥세트 1개"
            burnedCalories < 1200 -> "굽네치킨 1마리"
            burnedCalories < 1500 -> "보쌈 1인분"
            burnedCalories < 2000 -> "후라이드 치킨 1마리"
            burnedCalories >= 2000 -> "양념치킨 1마리"
            else -> "Over-Training"
        }
        food.text = foodText
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
