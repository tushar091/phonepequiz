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
    private var questionList: List<Questions>?
    private var pasuseQueued = true
    //data-binding
    var timerText = ObservableField<String>()
    var timer: CountDownTimer? = null
    //notify-view
    var uiActions = MutableLiveData<Int>()
    var questionLiveData = MutableLiveData<Questions>()

    init {
        val logoList = logoRepository.fetchLogoList()
        questionList = logoList?.map {
            val question = Questions(
                it, EMPTY_STRING,
                isAttempted = false,
                isCorrect = false,
                timeTaken = 0,
                jumbledAnswer = jumbleSequence(it.name)
            )
            question
        }
        gameState = GameState(false, 0, false, 0, 0, 0)
    }

    fun startGame() {
        startTimer()
        showQuestion()
    }

    fun queuePause() {
        pasuseQueued = true
    }

    private fun pauseGame() {
        pasuseQueued = true
    }

    fun captureUserAnswer(answer: String) {
        timer?.cancel()
        val currentQuestion = questionList?.get(gameState.currentQuestionIndex)
        currentQuestion?.userAnswer = answer
        currentQuestion?.timeTaken = timerText.get()?.toInt() ?: 0
        if (answer == currentQuestion?.logo?.name) {
            gameState.correctAnswerCount++
        } else {
            gameState.wrongAnswerCount++
        }
        if (pasuseQueued) {
            pauseGame()
        } else {
            takeUserToNextQuestion()
        }
    }

    private fun startTimer() {
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
        takeUserToNextQuestion()
    }

    private fun takeUserToNextQuestion() {
        if (gameState.currentQuestionIndex == questionList?.size) {
            gameState.isGameOver = true
            showResultScreen()
            return
        }
        startTimer()
        showQuestion()
    }

    private fun showResultScreen() {
        uiActions.postValue(TAKE_TO_RESULT)
    }

    private fun showQuestion() {
        gameState.apply {
            questionLiveData.postValue(questionList?.get(currentQuestionIndex++))
        }
    }


}