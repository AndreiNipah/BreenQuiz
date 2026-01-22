package com.example.dz1.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


data class QuizUiState(
    val screen: QuizScreen = QuizScreen.START,
    val currentQuestionIndex: Int = 0,
    val selectedAnswerId: Int? = null,
    val correctAnswers: Int = 0,
    val isAnswerSelected: Boolean = false
)

enum class QuizScreen {
    START, QUESTION, RESULT
}

class QuizViewModel : ViewModel() {

    var uiState by mutableStateOf(QuizUiState())
        private set

    private val questions: List<Question> = QuizData.questions

    val quizTitle: String = QuizData.quizTitle
    val quizDescription: String = QuizData.quizDescription

    fun startQuiz() {
        uiState = QuizUiState(
            screen = QuizScreen.QUESTION,
            currentQuestionIndex = 0,
            selectedAnswerId = null,
            correctAnswers = 0,
            isAnswerSelected = false
        )
    }

    fun selectAnswer(answerId: Int) {
        uiState = uiState.copy(
            selectedAnswerId = answerId,
            isAnswerSelected = true
        )
    }

    fun nextQuestion() {
        val current = currentQuestion
        val isCorrect = uiState.selectedAnswerId == current.correctAnswerId
        val newCorrectAnswers = uiState.correctAnswers + if (isCorrect) 1 else 0

        val isLastQuestion = uiState.currentQuestionIndex >= questions.size - 1

        uiState = if (!isLastQuestion) {
            uiState.copy(
                currentQuestionIndex = uiState.currentQuestionIndex + 1,
                selectedAnswerId = null,
                correctAnswers = newCorrectAnswers,
                isAnswerSelected = false
            )
        } else {
            uiState.copy(
                screen = QuizScreen.RESULT,
                correctAnswers = newCorrectAnswers
            )
        }
    }

    fun restartQuiz() {
        uiState = QuizUiState()
    }

    val currentQuestion: Question
        get() = questions[uiState.currentQuestionIndex]

    fun getTotalQuestions(): Int = questions.size

    fun getPercentage(): Float {
        val total = getTotalQuestions()
        if (total == 0) return 0f
        return (uiState.correctAnswers.toFloat() / total) * 100f
    }

    fun getResultComment(): String {
        val percentage = getPercentage()
        return when {
            percentage < 50f -> "Есть куда расти! Попробуйте ещё раз."
            percentage < 80f -> "Хороший результат! Но можно лучше."
            else -> "Отличный результат! Вы отлично знаете тему!"
        }
    }
}
