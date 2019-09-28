package com.example.phonepequiz.model

data class Questions(
    val logo: Logos,
    var userAnswer: String,
    var isAttempted: Boolean,
    var isCorrect: Boolean,
    var timeTaken: Int,
    var jumbledAnswer: List<Char>
)