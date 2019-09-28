package com.example.phonepequiz.model

data class GameState(
    var isPaused: Boolean,
    var answeredQuestions: Int = 0,
    var questionList: List<Questions>,
    var currentQuestionIndex: Int
)