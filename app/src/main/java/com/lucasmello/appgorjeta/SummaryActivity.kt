package com.lucasmello.appgorjeta

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lucasmello.appgorjeta.databinding.ActivitySummaryBinding

class SummaryActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySummaryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val totalTable = intent.getFloatExtra("totalTable", 0.0f)
        val numPeople = intent.getIntExtra("numPeople", 0)
        val percentage = intent.getIntExtra("percentage", 0)
        val totalAmount = intent.getFloatExtra("totalAmount", 0.0f)

        with(binding) {
            tvTotalTable.text = totalTable.toString()
            tvNumPeopleTable.text = numPeople.toString()
            tvPercentage.text = percentage.toString()
            tvTotalAmount.text = totalAmount.toString()

            btnRefresh.setOnClickListener {
                finish()
            }
        }
    }
}