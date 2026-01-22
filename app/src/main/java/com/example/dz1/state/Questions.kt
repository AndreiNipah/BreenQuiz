package com.example.dz1.state

data class Question(
    val id: Int,
    val text: String,
    val answers: List<Answer>,
    val correctAnswerId: Int
)

data class Answer(
    val id: Int,
    val text: String
)

object QuizData {

    val quizTitle = "Творческий путь Нила Брина"

    val quizDescription =
        "Викторина о карьере и фильмах Нила Брина — культового независимого режиссёра, " +
                "известного своим уникальным стилем, минимальным бюджетом и абсолютной уверенностью " +
                "в собственном гении."

    val questions: List<Question> = listOf(
        Question(
            id = 1,
            text = "Кем по профессии был Нил Брин до начала своей кинокарьеры?",
            answers = listOf(
                Answer(1, "Журналист"),
                Answer(2, "Архитектор"),
                Answer(3, "Актёр театра"),
                Answer(4, "Программист")
            ),
            correctAnswerId = 2
        ),
        Question(
            id = 2,
            text = "Какой фильм является режиссёрским дебютом Нила Брина?",
            answers = listOf(
                Answer(1, "Twisted Pair"),
                Answer(2, "Fateful Findings"),
                Answer(3, "Double Down"),
                Answer(4, "I Am Here… Now")
            ),
            correctAnswerId = 3 // ← Double Down
        ),
        Question(
            id = 3,
            text = "Какую роль Нил Брин чаще всего играет в своих фильмах?",
            answers = listOf(
                Answer(1, "Антагонист"),
                Answer(2, "Обычный человек"),
                Answer(3, "Божественная или сверхъестественная сущность"),
                Answer(4, "Комический персонаж")
            ),
            correctAnswerId = 3
        ),
        Question(
            id = 4,
            text = "Какая повторяющаяся тема часто встречается в фильмах Нила Брина?",
            answers = listOf(
                Answer(1, "Космические путешествия"),
                Answer(2, "Коррупция правительства и общества"),
                Answer(3, "Исторические события"),
                Answer(4, "Романтические комедии")
            ),
            correctAnswerId = 2
        ),
        Question(
            id = 5,
            text = "Почему фильмы Нила Брина стали культовыми?",
            answers = listOf(
                Answer(1, "Из-за огромных бюджетов"),
                Answer(2, "Из-за участия известных актёров"),
                Answer(3, "Из-за уникального авторского стиля и наивности"),
                Answer(4, "Из-за точного следования правилам Голливуда")
            ),
            correctAnswerId = 3
        )
    )
}
