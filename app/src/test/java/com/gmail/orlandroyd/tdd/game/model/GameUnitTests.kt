package com.gmail.orlandroyd.tdd.game.model

import org.junit.Assert
import org.junit.Test

class GameUnitTests {

    @Test
    fun whenIncrementingHighScore_shouldIncrementCurrentScore() {
        val game = Game()
        game.incrementScore()
//        Assert.assertEquals(1, game.currentScore)
        Assert.assertEquals("Current score shuld have been 1",1, game.currentScore)
    }
}