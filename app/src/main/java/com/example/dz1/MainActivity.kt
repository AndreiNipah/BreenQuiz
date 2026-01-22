package com.example.dz1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dz1.screens.QuestionScreen
import com.example.dz1.screens.ResultScreen
import com.example.dz1.screens.StartScreen
import com.example.dz1.state.QuizScreen
import com.example.dz1.state.QuizViewModel
import com.example.dz1.ui.theme.Dz1Theme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Dz1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val viewModel: QuizViewModel = viewModel()
    val uiState = viewModel.uiState

    when (uiState.screen) {
        QuizScreen.START -> StartScreen(
            title = viewModel.quizTitle,
            description = viewModel.quizDescription,
            onStartClick = { viewModel.startQuiz() }
        )

        QuizScreen.QUESTION -> QuestionScreen(
            currentQuestion = viewModel.currentQuestion,
            currentQuestionIndex = uiState.currentQuestionIndex,
            totalQuestions = viewModel.getTotalQuestions(),
            selectedAnswerId = uiState.selectedAnswerId,
            onAnswerSelected = { answerId -> viewModel.selectAnswer(answerId) },
            onNextClick = { viewModel.nextQuestion() },
            isAnswerSelected = uiState.isAnswerSelected
        )

        QuizScreen.RESULT -> ResultScreen(
            correctAnswers = uiState.correctAnswers,
            totalQuestions = viewModel.getTotalQuestions(),
            percentage = viewModel.getPercentage(),
            comment = viewModel.getResultComment(),
            onRestartClick = { viewModel.restartQuiz() }
        )
    }
}
