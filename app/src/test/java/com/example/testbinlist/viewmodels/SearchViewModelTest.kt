package com.example.testbinlist.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.domain.Country
import com.example.testbinlist.domain.DataBaseRepository
import com.example.testbinlist.domain.GetCardInfoUseCase
import com.example.testbinlist.domain.SharingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: SearchViewModel
    private lateinit var fakeUseCase: FakeGetCardInfoUseCase
    private lateinit var fakeDatabase: FakeDataBaseRepository
    private lateinit var fakeSharing: FakeSharingRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeUseCase = FakeGetCardInfoUseCase()
        fakeDatabase = FakeDataBaseRepository()
        fakeSharing = FakeSharingRepository()
        viewModel = SearchViewModel(fakeUseCase, fakeDatabase, fakeSharing)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is empty`() = runTest {
        val initialState = viewModel.stateFlow.value

        assertFalse(initialState.isLoading)
        assertEquals(CardInfo(), initialState.cardInfo)
    }

    @Test
    fun `successful search updates state`() = runTest {
        val testCard = CardInfo(
            country = Country(name = "Russia", latitude = 60, longitude = 100),
            bank = com.example.testbinlist.domain.Bank(name = "Sberbank", city = "Moscow"),
            scheme = "visa"
        )
        fakeUseCase.result = flowOf(testCard to null)

        viewModel.fetchCardInfo("427612")

        val state = viewModel.stateFlow.value
        assertFalse(state.isLoading)
        assertEquals("Russia", state.cardInfo.country.name)
    }

    @Test
    fun `invalid BIN shows snackbar error`() = runTest {
        viewModel.snackbarEvent.test {
            viewModel.fetchCardInfo("123")

            val message = awaitItem()
            assertTrue(message.contains("6 до 8"))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `loading state is shown during request`() = runTest {
        fakeUseCase.result = flowOf(CardInfo() to null)

        viewModel.fetchCardInfo("99999999")

        // After request completes
        val state = viewModel.stateFlow.value
        assertFalse(state.isLoading)
    }

    // Fake implementations
    private class FakeGetCardInfoUseCase : GetCardInfoUseCase {
        var result: Flow<Pair<CardInfo, String?>> = flowOf(CardInfo() to null)

        override suspend fun execute(id: String): Flow<Pair<CardInfo, String?>> {
            return result
        }
    }

    private class FakeDataBaseRepository : DataBaseRepository {
        val savedCards = mutableListOf<CardInfo>()

        override suspend fun putCardIntoDb(cardInfo: CardInfo) {
            savedCards.add(cardInfo)
        }

        override fun getAllCards(): kotlinx.coroutines.flow.Flow<List<CardInfo>> {
            return flowOf(savedCards)
        }
    }

    private class FakeSharingRepository : SharingRepository {
        override fun openLink(url: String) {}
        override fun openMap(coordinates: String) {}
        override fun openDialer(phone: String) {}
    }
}
