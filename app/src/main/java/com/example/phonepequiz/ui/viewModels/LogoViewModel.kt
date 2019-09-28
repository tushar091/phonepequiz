package com.example.phonepequiz.ui.viewModels

import android.os.CountDownTimer
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.phonepequiz.Constants.EMPTY_STRING
import com.example.phonepequiz.Constants.TIMER_TIME
import com.example.phonepequiz.model.GameState
import com.example.phonepequiz.model.Questions
import com.example.phonepequiz.repository.LogoRepository
import com.example.phonepequiz.utils.jumbleSequence

class LogoViewModel : ViewModel() {

    companion object {
        const val TAKE_TO_RESULT = 100
    }

    private var logoRepository: LogoRepository = LogoRepository()
    private var gameState: GameState
    var timerText = ObservableField<String>()
    var timer: CountDownTimer? = null
    var uiActions = MutableLiveData<Int>()
    var question = MutableLiveData<Questions>()

    init {
        val logoList = logoRepository.fetchLogoList()
        val questions = logoList?.map {
            val question = Questions(
                it, EMPTY_STRING,
                isAttempted = false,
                isCorrect = false,
                timeTaken = 0,
                jumbledAnswer = jumbleSequence(it.name)
            )
            question
        }
        gameState = GameState(false, 0, questions, 0)
    }

    fun startGame() {
        startTimer()
        gameState.attemptedQues++
        showQuestion()
    }

    fun pauseGame() {
        gameState.isPaused = true
    }

    fun captureUserAnswer(answer: String) {
        timer?.cancel()
        takeUserToNextQuestion()
    }

    fun startTimer() {
        timer = object : CountDownTimer(TIMER_TIME, 1) {
            override fun onFinish() {
                handleTimeOver()
            }

            override fun onTick(remainingTime: Long) {
                timerText.set(remainingTime.toString())
            }

        }
    }

    fun handleTimeOver() {

    }

    fun takeUserToNextQuestion() {
        if (gameState.attemptedQues == gameState.questionList?.size) {
            showResultScreen()
        }
    }

    fun showResultScreen() {
        uiActions.postValue(TAKE_TO_RESULT)
    }

    fun showQuestion(){

    }


}