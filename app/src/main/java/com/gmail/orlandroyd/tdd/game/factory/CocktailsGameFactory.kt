package com.gmail.orlandroyd.tdd.game.factory

import com.gmail.orlandroyd.tdd.game.model.Game

interface CocktailsGameFactory {

    fun buildGame(callback: Callback)

    interface Callback {
        fun onSuccess(game: Game)
        fun onError()
    }
}