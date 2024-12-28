package com.doanchung.myquizzapp

object Constants {
    fun getQuestions() : ArrayList<Question> {
        val questionsList = ArrayList<Question>()
        //1
        val que1 = Question(
            1,
            "what country?",
            R.drawable.united_kingdom,
            "Vietnam",
            "Laos",
            "China",
            "VQuoc Anh",
            4
        )
        questionsList.add(que1)

        //2
        val que2 = Question(
            2,
            "what country?",
            R.drawable.united_states,
            "Ngũ My",
            "Laos",
            "China",
            "VQuoc Anh",
            1
        )
        questionsList.add(que1)

        return questionsList
    }
}