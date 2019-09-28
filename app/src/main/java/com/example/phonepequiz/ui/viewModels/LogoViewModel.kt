package com.example.phonepequiz.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.phonepequiz.Constants.EMPTY_STRING
import com.example.phonepequiz.model.GameState
import com.example.phonepequiz.model.Questions
import com.example.phonepequiz.repository.LogoRepository
import com.example.phonepequiz.utils.jumbleSequence

class LogoViewModel() : ViewModel() {
    var logoRepository: LogoRepository = LogoRepository()
    private var gameState: GameState

    init {
        val logoList = logoRepository.fetchLogoList()
        val questions = logoList.map {
            var questions = Questions(
                it, EMPTY_STRING,
                isAttempted = false,
                isCorrect = false,
                timeTaken = 0,
                jumbledAnswer =
                jumbleSequence(it.name)
            )
            questions
        }
        gameState = GameState(false, 0, questions, 0)
    }

    fun startGame() {

    }

    fun pauseGame() {

    }

    fun captureUserAnswer(answer: String) {

    }
}