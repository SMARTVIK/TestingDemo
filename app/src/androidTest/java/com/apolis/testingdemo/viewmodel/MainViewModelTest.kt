package com.apolis.testingdemo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule() //this is for live data

     val repository : MainRepository = mock()
     val liveDataObserver : Observer<UiModel> = mock()

    private val viewModel = MainViewModel()

    @Before
    fun setUp() {
        viewModel.repository = repository
        viewModel.liveData.observeForever(liveDataObserver)
    }

    @Test
    fun setTitleSavesTitle() {
        val title = "New title"
        viewModel.setTitle(title)
        verify(repository).setTitle(title)//verifying setTitle was called or not
    }

    @Test
    fun setTitleCallsOnChanged() {
        val title = "New title"
        viewModel.setTitle(title)
        verify(liveDataObserver).onChanged(UiModel.TitleUpdate(title))
    }





}