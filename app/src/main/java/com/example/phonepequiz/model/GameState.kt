package com.example.phonepequiz.model

data class GameState(
    var isPaused: Boolean,
    var questionList: List<Questions>? = null,
    var currentQuestionIndex: Int = 0,
    var isGameOver: Boolean
)