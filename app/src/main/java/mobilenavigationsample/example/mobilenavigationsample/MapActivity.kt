package mobilenavigationsample.example.mobilenavigationsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mobilenavigationsample.example.mobilenavigationsample.databinding.ActivityMapBinding


class MapActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMapBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getStringExtra("town")?.let{binding.town.text = it}
        intent.getStringExtra("name")?.let{binding.name.text = it}
        intent.getStringExtra("address")?.let{binding.address.text = it}
        intent.getStringExtra("exercise")?.let{binding.exercise.text = it}
        //val latitude = intent.getDoubleExtra("latitude", 0.0)
        //val longitude = intent.getDoubleExtra("longitude", 0.0)
        //binding.location.text = "$latitude/$longitude"

        binding.close.setOnClickListener() {
            this@MapActivity.finish()
        }



    }
}