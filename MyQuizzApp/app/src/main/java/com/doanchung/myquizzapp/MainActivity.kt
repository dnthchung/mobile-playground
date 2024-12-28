package com.doanchung.myquizzapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnStart: Button = findViewById(R.id.btn_start)
        val etName: EditText = findViewById(R.id.et_name)
        btnStart.setOnClickListener {

            if (etName.text.isEmpty()) {
                Toast.makeText(this,
                    "Please enter ur name.", Toast.LENGTH_LONG).show()
            }else{
                //create intent(đi từ đâu ? - từ this activity , đến đâu ? - quiz activity
                val intent = Intent(this,QuizQuestionsActivity::class.java)
                startActivity(intent)

                //bắt nhập lại tên, close cái activity main đã mở trc đó
                finish()

            }
        }
    }
}
