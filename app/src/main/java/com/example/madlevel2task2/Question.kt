package com.example.madlevel2task2


data class Question(
    var questionText: String,
    var answer: Boolean

) {
    //Questions for the quiz
    companion object {
        val QUESTIONS = arrayOf(
         "A 'val' and 'var' are the same",
         "Mobile Application Development grants 12 ETCS. ",
         "A Unit in Kotlin corresponds to a void in Java",
         "In Kotlin 'when' replaces the 'the switch' operator in Java.",
         )

        //Answers for the quiz
        val ANSWERS =  booleanArrayOf(
            true,true,false,false
        )
    }


}
