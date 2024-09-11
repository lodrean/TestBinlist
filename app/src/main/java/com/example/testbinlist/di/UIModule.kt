package com.example.testbinlist.di

import com.example.testbinlist.viewmodels.HistoryViewModel
import com.example.testbinlist.viewmodels.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    viewModel<SearchViewModel> {
        SearchViewModel(get(), get())
    }
    viewModel<HistoryViewModel> {
        HistoryViewModel(get())
    }
}