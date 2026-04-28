package com.example.testbinlist.viewmodels

import app.cash.turbine.test
import com.example.testbinlist.domain.CardInfo
import com.example.testbinlist.domain.Country
import com.example.testbinlist.domain.DataBaseRepository
import com.example.testbinlist.domain.SharingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HistoryViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: HistoryViewModel
    private lateinit var fakeDatabase: FakeDataBaseRepository
    private lateinit var fakeSharing: FakeSharingRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeDatabase = FakeDataBaseRepository()
        fakeSharing = FakeSharingRepository()
        viewModel = HistoryViewModel(fakeDatabase, fakeSharing)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is empty list`() {
        val state = viewModel.stateFlow.value
        assertTrue(state.historyList.isEmpty())
    }

    @Test
    fun `fetchHistory updates state with database items`() = runTest {
        val testCards = listOf(
            CardInfo(
                cardNumber = "40000002",
                country = Country(name = "Russia", latitude = 60, longitude = 100),
                bank = com.example.testbinlist.domain.Bank(
                    name = "Sberbank",
                    city = "Moscow",
                    phone = "",
                    url = ""
                ),
                scheme = "visa",
                brand = "Visa",
                type = "debit"
            )
        )
        fakeDatabase.historyFlow = flowOf(testCards)

        viewModel.fetchHistory()

        val state = viewModel.stateFlow.value
        assertEquals(1, state.historyList.size)
        assertEquals("Russia", state.historyList[0].country.name)
    }

    @Test
    fun `fetchHistory with empty database returns empty list`() = runTest {
        fakeDatabase.historyFlow = flowOf(emptyList())

        viewModel.fetchHistory()

        val state = viewModel.stateFlow.value
        assertTrue(state.historyList.isEmpty())
    }

    // Fake implementations
    private class FakeDataBaseRepository : DataBaseRepository {
        var historyFlow: Flow<List<CardInfo>> = flowOf(emptyList())

        override suspend fun putCardIntoDb(cardInfo: CardInfo) {
            // no-op
        }

        override fun getHistory(): Flow<List<CardInfo>> {
            return historyFlow
        }
    }

    private class FakeSharingRepository : SharingRepository {
        override fun openLink(url: String) {}
        override fun openMap(coordinates: String) {}
        override fun openDialer(phone: String) {}
    }
}
