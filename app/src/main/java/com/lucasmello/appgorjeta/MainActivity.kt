package com.lucasmello.appgorjeta

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.lucasmello.appgorjeta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        //Porcentagem
        var percentage: Int = 0
        //Radio Button One 10%
        binding.rbOptionOne.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentage = 10
            }
        }

        //Radio Button Two 15%
        binding.rbOptionTwo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentage = 15
            }
        }

        //Radio Button Three 20%
        binding.rbOptionThree.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentage = 20
            }
        }


        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.num_people,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerNumberOfPeople.adapter = adapter

        var numberOfPeopleSelected = 0
        binding.spinnerNumberOfPeople.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    numberOfPeopleSelected = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

        //Cálculo Gorjeta
        binding.btnDone.setOnClickListener {
            val totalTableTemp = binding.tieTotal.text


            if (totalTableTemp?.isEmpty() == true) {

                Snackbar.make(binding.tieTotal, "Fill in all the fields", Snackbar.LENGTH_SHORT)
                    .show()

            } else {
                val totalTable: Float = totalTableTemp.toString().toFloat()
                val nPeople: Int = numberOfPeopleSelected

                val totalTemp = totalTable / nPeople
                val tips = totalTemp * percentage / 100
                val totalWithtips = totalTemp + tips


                val intent = Intent(this, SummaryActivity::class.java)
                intent.apply {
                    putExtra("totalTable", totalTable)
                    putExtra("numPeople", numberOfPeopleSelected)
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
        binding.rbOptionOne.isChecked = false
        binding.rbOptionTwo.isChecked = false
        binding.rbOptionThree.isChecked = false
    }
}