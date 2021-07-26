package com.apolis.testingdemo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
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

    @Test
    fun setCountCallsRepositoryCount() {
        viewModel.incrementCount()
        verify(repository).getCount() //method was not called or not
    }

    @Test
    fun setCountValueCallsRepositorySetCount() {

        val count = 50

        stubbingTheValueCount(count) //stubbing = faking

        viewModel.incrementCount()

        verify(repository).setCount(51)

    }

    @Test
    fun setCountValueCallsOnChanged() {

        val count = 50

        stubbingTheValueCount(count) //stubbing = faking

        viewModel.incrementCount()

        verify(liveDataObserver).onChanged(UiModel.CountUpdate(count + 1))

    }


    private fun stubbingTheValueCount(count: Int) {
        whenever(repository.getCount()).thenReturn(count)
    }


}