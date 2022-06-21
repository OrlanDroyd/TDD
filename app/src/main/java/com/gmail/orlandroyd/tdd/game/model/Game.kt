package com.gmail.orlandroyd.tdd.game.model

class Game(
    private val questions: List<Question>,
    private val score: Score = Score(0),
) {

    private var questionIndex = -1

    fun nextQuestion(): Question? {
        if (questionIndex + 1 < questions.size) {
            questionIndex++
            return questions[questionIndex]
        }
        return null
    }

    fun answer(question: Question, option: String) {
        val result = question.answer(option)
        if (result) {
            score.increment()
        }
    }
}