package com.gmail.orlandroyd.tdd.common.repository

import android.content.SharedPreferences
import com.gmail.orlandroyd.tdd.common.network.CocktailsApi
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryUnitTests {

    private lateinit var repository: CocktailsRepository

    @Mock
    private lateinit var api: CocktailsApi

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    @Before
    fun setup() {
        whenever(sharedPreferences.edit())
            .thenReturn(sharedPreferencesEditor)

        repository = CocktailsRepositoryImpl(api, sharedPreferences)
    }


    @Test
    fun `save score - should save to SharedPreferences`() {

        val score = 100
        repository.saveHighScore(score)

        inOrder(sharedPreferencesEditor) {
            verify(sharedPreferencesEditor).putInt(any(), eq(score))
            verify(sharedPreferencesEditor).apply()
        }

    }

    @Test
    fun `get score - should get from SharedPreferences`() {

        repository.getHighScore()

        verify(sharedPreferences).getInt(any(), any())

    }

    @Test
    fun `save score - should not save to SharedPreferences if is lower`() {

        val previouslySavedHighScore = 100

        val newHighScore = 10

        val spyRepository = spy(repository)

        doReturn(previouslySavedHighScore)
            .whenever(spyRepository)
            .getHighScore()

        spyRepository.saveHighScore(newHighScore)

        verify(sharedPreferencesEditor, never()).putInt(any(), eq(newHighScore))

    }


}