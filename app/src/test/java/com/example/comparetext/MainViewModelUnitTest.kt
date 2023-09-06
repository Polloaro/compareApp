package com.example.comparetext

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.comparetext.view.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MainViewModelUnitTest {


    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private lateinit var viewModel: MainViewModel
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun mainViewModel_CheckInitialValue() = runTest {
        assertEquals(null, viewModel.stringComparison.value)
        assertNull(viewModel.errorMsg.value)
    }

    @Test
    fun mainViewModel_CompareEqualStrings() = runTest {
        val str1Val = "asdasd"
        val str2Val = "asdasd"
        launch {
            viewModel.compareStrings(str1Val, str2Val)
        }
        advanceUntilIdle()
        val result = viewModel.stringComparison.value?.compareResult
        assertTrue(result!!)
        val errorMsg = viewModel.errorMsg.value
        assertNull(errorMsg)
    }

    @Test
    fun mainViewModel_CompareDifferentStrings() = runTest {
        val str1Val = "asdasd"
        val str2Val = "ASDASD"
        launch {
            viewModel.compareStrings(str1Val, str2Val)
        }
        advanceUntilIdle()
        val result = viewModel.stringComparison.value?.compareResult
        assertFalse(result!!)
        val errorMsg = viewModel.errorMsg.value
        assertNull(errorMsg)
    }

    @Test
    fun mainViewModel_CompareEmptyString() = runTest {
        val str1Val = "asdasd"
        val str2Val = ""
        launch {
            viewModel.compareStrings(str1Val, str2Val)
        }
        advanceUntilIdle()
        val errorMsg = viewModel.errorMsg.value
        assertNotNull(errorMsg)
    }
}