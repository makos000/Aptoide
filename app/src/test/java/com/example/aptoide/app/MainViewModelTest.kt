package com.example.aptoide.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.aptoide.data.remote.api.ApiInterface
import com.example.aptoide.data.remote.remoteds.RemoteDSImpl
import com.example.aptoide.data.repo.FakeRepo
import com.example.aptoide.domain.model.Parameters
import com.example.aptoide.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class MainViewModelTest{
    private lateinit var viewModel: MainViewModel
    private lateinit var fakeRepo: FakeRepo

    val model = Parameters("",0,"", "",
        0,"","","","",0,
        0.0, 0,"","","",
        0,"")

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutionRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remote: RemoteDSImpl

    @Mock
    private lateinit var api: ApiInterface

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        fakeRepo = FakeRepo(remote)
        viewModel = MainViewModel(fakeRepo)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `api call test loading and success`() = runBlocking {
        whenever(remote.getApps()).thenReturn(Resource.Success(listOf(model)))
        viewModel.getData()
        viewModel.data.test {
            assertTrue(awaitItem() is Resource.Loading)
            awaitItem()
            val result = viewModel.data.value.data?.get(0)?.appModel
            assertEquals(awaitItem().data?.get(0)?.appModel , result)
        }
    }

    @Test
    fun `api call test loading and error`() = runBlocking {
        whenever(api.getApps()).thenReturn(Response.error(404,"Error".toResponseBody()))
        whenever(remote.getApps()).thenReturn(Resource.Error("Error"))
        viewModel.getData()
        viewModel.data.test {
            assertTrue(awaitItem() is Resource.Loading)
            awaitItem()
            val result = viewModel.data.value.message
            assertEquals(awaitItem().message , result)
        }
    }
}