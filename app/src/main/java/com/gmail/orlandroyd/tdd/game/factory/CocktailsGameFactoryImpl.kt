package com.gmail.orlandroyd.tdd.game.factory

import com.gmail.orlandroyd.tdd.common.network.Cocktail
import com.gmail.orlandroyd.tdd.common.repository.CocktailsRepository
import com.gmail.orlandroyd.tdd.common.repository.RepositoryCallback
import com.gmail.orlandroyd.tdd.game.factory.CocktailsGameFactory.Callback
import com.gmail.orlandroyd.tdd.game.model.Game
import com.gmail.orlandroyd.tdd.game.model.Question
import com.gmail.orlandroyd.tdd.game.model.Score

class CocktailsGameFactoryImpl(
    private val repository: CocktailsRepository,
) : CocktailsGameFactory {

    override fun buildGame(callback: Callback) {
        repository.getAlcoholic(
            object : RepositoryCallback<List<Cocktail>, String> {
                override fun onSuccess(t: List<Cocktail>) {
                    val questions = buildQuestions(t)
                    val score = Score(repository.getHighScore())
                    val game = Game(questions, score)
                    callback.onSuccess(game)
                }

                override fun onError(e: String) {
                    callback.onError()
                }
            })
    }

    private fun buildQuestions(cocktailList: List<Cocktail>) = cocktailList.map { cocktail ->
        val otherCocktail = cocktailList.shuffled().first { it != cocktail }
        Question(cocktail.strDrink, otherCocktail.strDrink, cocktail.strDrinkThumb)
    }
}