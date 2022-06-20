package com.gmail.orlandroyd.tdd.game.model

class Question(
    private val correctOption: String,
    private val incorrectOption: String,
) {
    var answeredOption: String? = null
        private set

    private val isAnsweredCorrectly: Boolean
        get() = correctOption == answeredOption

    fun answer(option: String): Boolean {
        if (option != correctOption && option != incorrectOption)
            throw IllegalArgumentException("Not a valid option")

        answeredOption = option

        return isAnsweredCorrectly
    }
}