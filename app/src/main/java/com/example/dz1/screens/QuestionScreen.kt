package com.example.dz1.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dz1.state.Question

@Composable
fun QuestionScreen(
    currentQuestion: Question,
    currentQuestionIndex: Int,
    totalQuestions: Int,
    selectedAnswerId: Int?,
    onAnswerSelected: (Int) -> Unit,
    onNextClick: () -> Unit,
    isAnswerSelected: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "Вопрос ${currentQuestionIndex + 1} из $totalQuestions",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = currentQuestion.text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 26.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            currentQuestion.answers.forEachIndexed { index, answer ->
                val selected = selectedAnswerId == answer.id

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onAnswerSelected(answer.id) }
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selected,
                        onClick = { onAnswerSelected(answer.id) }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = answer.text,
                        fontSize = 16.sp
                    )
                }

                if (index != currentQuestion.answers.lastIndex) {
                    HorizontalDivider()
                }
            }
        }

        Button(
            onClick = onNextClick,
            enabled = isAnswerSelected,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Дальше", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}
