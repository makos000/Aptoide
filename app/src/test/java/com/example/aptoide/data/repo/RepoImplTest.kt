package com.example.aptoide.data.repo

import com.example.aptoide.data.local.room.AppEntity
import com.example.aptoide.data.remote.remoteds.RemoteDSImpl
import com.example.aptoide.domain.model.Parameters
import com.example.aptoide.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.checkerframework.checker.units.qual.A
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class RepoImplTest {

    private lateinit var fakeRepo: FakeRepo
    private val testDispatcher = StandardTestDispatcher()

    val model = Parameters("",0,"", "",
        0,"","","","",0,
        0.0, 0,"","","",
        0,"")

    @Mock
    private lateinit var remote: RemoteDSImpl

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        fakeRepo = FakeRepo(remote)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `simulate success response from api call`() = runBlocking{
        val expectedResult = Resource.Success(listOf<Parameters>())
        whenever(remote.getApps()).thenReturn(expectedResult)

        val response = fakeRepo.getAppsFromApi()

        assertEquals(response, expectedResult)
    }

    @Test
    fun `get list from fakerepo with model after insertArticlesToDB`() = runBlocking{
        val expected = listOf(model)
        fakeRepo.insertAppsToDB(AppEntity(listOf(model)))
        assertEquals(expected,fakeRepo.list)
    }

    @Test
    fun `get empty list of AppEntity from flow collect of fake repo`() = runBlocking{
        val expected = listOf<AppEntity>()
        fakeRepo.insertAppsToDB(AppEntity(listOf(model)))
        val result = fakeRepo.readAppsfromDB()
        result.collect(){
            assertEquals(expected,it)
        }
    }

    @Test
    fun `get list with model and then nuke it, expect emtpy list`() = runBlocking{
        val expected = listOf<Parameters>()
        fakeRepo.insertAppsToDB(AppEntity(listOf(model)))
        fakeRepo.nukeTable()
        val result = fakeRepo.readAppsfromDB()
        result.collect(){
            assertEquals(expected,it)
        }
    }
}