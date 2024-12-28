package com.doanchung.dobcalc

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import java.util.Calendar

class CalcInHour : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calc_in_hour)

        //set up button
        val buttonSelectDate : Button = findViewById(R.id.btnSelectDate)
        buttonSelectDate.setOnClickListener {
            clickDateSolve()
        }

    }

    private fun clickDateSolve() {
        val mycalen = Calendar.getInstance()
        val year = mycalen.get(Calendar.YEAR)
        val month = mycalen.get(Calendar.MONTH)
        val day = mycalen.get(Calendar.DAY_OF_MONTH)

        val mydpd = DatePickerDialog(
            this,
            { view, myyear, mymonth,myday ->
                //remember .show()
                Toast.makeText(this,"click success",Toast.LENGTH_LONG).show()

            },
            year,
            month,
            day)

        }
}