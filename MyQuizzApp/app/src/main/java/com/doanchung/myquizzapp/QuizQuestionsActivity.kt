package com.doanchung.myquizzapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class QuizQuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        val questionList = Constants.getQuestions()
        //muốn show thì cần chạy start qua để kích cái onCreate bên quizAct trước
        Log.i("QuestionsList size is ", "${questionList.size}")

    }
}