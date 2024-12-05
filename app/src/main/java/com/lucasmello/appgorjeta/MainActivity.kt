package com.lucasmello.appgorjeta

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.lucasmello.appgorjeta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Cálculo Gorjeta
        binding.btnDone.setOnClickListener {
            val totalTableTemp = binding.tieTotal.text
            val numPeopleTemp = binding.tieNumPeople.text
            val percentageTemp = binding.tiePercentage.text



            if (totalTableTemp?.isEmpty() == true ||
                numPeopleTemp?.isEmpty() == true ||
                percentageTemp?.isEmpty() == true
            ) {

                Snackbar.make(binding.tieTotal, "Preencha todos os campos", Snackbar.LENGTH_SHORT)
                    .show()

            } else {
                val totalTable: Float = totalTableTemp.toString().toFloat()
                val nPeople: Int = numPeopleTemp.toString().toInt()
                val percentage: Int = percentageTemp.toString().toInt()


                val totalTemp = totalTable / nPeople
                val tips = totalTemp * percentage / 100
                val totalWithtips = totalTemp + tips


                val intent = Intent(this, SummaryActivity::class.java)
                intent.apply {
                    putExtra("totalTable", totalTable)
                    putExtra("numPeople", nPeople)
                    putExtra("percentage", percentage)
                    putExtra("totalAmount", totalWithtips)
                }
                clean()
                startActivity(intent)
            }

            binding.btnClean.setOnClickListener {
                clean()
            }
        }
    }

    private fun clean() {
        binding.tieTotal.setText("")
        binding.tieNumPeople.setText("")
        binding.tiePercentage.setText("")
    }
}