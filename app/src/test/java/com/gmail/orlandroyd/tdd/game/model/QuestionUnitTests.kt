package com.gmail.orlandroyd.tdd.game.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class QuestionUnitTests {
  private lateinit var question: Question

  @Before
  fun setup() {
    question = Question("CORRECT", "INCORRECT")
  }

  @Test
  fun whenCreatingQuestion_shouldNotHaveAnsweredOption() {
    Assert.assertNull(question.answeredOption)
  }

  @Test
  fun whenAnswering_shouldHaveAnsweredOption() {
    question.answer("INCORRECT")

    Assert.assertEquals("INCORRECT", question.answeredOption)
  }

  @Test
  fun whenAnswering_withCorrectOption_shouldReturnTrue() {
    val result = question.answer("CORRECT")

    Assert.assertTrue(result)
  }

  @Test
  fun whenAnswering_withIncorrectOption_shouldReturnFalse() {
    val result = question.answer("INCORRECT")

    Assert.assertFalse(result)
  }

  @Test(expected = IllegalArgumentException::class)
  fun whenAnswering_withInvalidOption_shouldThrowException() {
    question.answer("INVALID")
  }

}