package com.example.phonepequiz.model

data class GameState(
    var isPaused: Boolean,
    var attemptedQues: Int = 0,
    var questionList: List<Questions>? = null,
    var currentQuestionIndex: Int,
    var isGameOver: Boolean
)