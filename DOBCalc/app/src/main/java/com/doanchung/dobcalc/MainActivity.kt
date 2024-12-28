package com.doanchung.dobcalc

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MainActivity : ComponentActivity() {

    private var tvSelectedDate : TextView ?= null
    private var tvAgeInMinute : TextView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinute = findViewById(R.id.tvSelectedDateInMinutes)

        buttonDatePicker.setOnClickListener {
            clickDatePicker()
        }

    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _ , selectedYear, selectedMonth, selectedDayOfMonth ->

                //view ko dc sd, thi dung _ thay cho view
                //=================== 1. show toast noti for user
                //month dhs start with 0 so it mean january = month 0 :), so we +1 to make it right
                Toast.makeText(this, "year was $selectedYear, " +
                        "month was ${selectedMonth+1}, " +
                        "day of month was $selectedDayOfMonth"
                    , Toast.LENGTH_LONG).show()

                //=================== 2. show result after choosing
                val selectedDateToShowForUser = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate?.text = selectedDateToShowForUser //using .text de thay the cho: setText(selectedDate)

                //=================== 3. change selected date to type date to calc age
                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate = sdf.parse(selectedDateToShowForUser)
                /**
                 - dùng let ở đây để đảm bảo rằng code nó chạy khi mà các thằng
                 theDate hay currentDate nó không rỗng.
                 - nếu mà vì lí do nào đó date rỗng thì ko chạy code ảnh hưởng app
                 **/
                theDate?.let{
                    val selectedDateMinute = theDate.time / 60000 //using .time thay cho getTime()
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateMinute = currentDate.time / 60000
                        val differenceInMinnute = currentDateMinute - selectedDateMinute
                        tvAgeInMinute?.text = differenceInMinnute.toString()
                    }
                }
                                               },
            year, month, day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000 //set max date that can be choose
        dpd.show()
    }


}

