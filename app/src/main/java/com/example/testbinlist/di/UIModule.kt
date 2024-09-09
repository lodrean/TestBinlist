package com.example.testbinlist.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
/*
    viewModel<SearchViewModel> {
        SearchViewModel(
            vacanciesInteractor = get(),
            filterInteractor = get(),
            application = get(),
        )
    }

    viewModel { (vacancy: Vacancy, vacancyNeedToUpdate: Boolean) ->
        VacancyDetailsViewModel(
            vacancy = vacancy,
            vacancyNeedToUpdate = vacancyNeedToUpdate,
            vacanciesInteractor = get(),
            sharingInteractor = get(),
            favoritesInteractor = get(),
            application = get()
        )
    }

    viewModel<FavoritesViewModel> {
        FavoritesViewModel(get())
    }

    viewModel<WorkplaceViewModel> {
        WorkplaceViewModel(get(), get())
    }

    viewModel<CountryViewModel> {
        CountryViewModel(get(), get())
    }

    viewModel<RegionViewModel> {
        RegionViewModel(get(), get())
    }

    viewModel<IndustryViewModel> {
        IndustryViewModel(
            dictionariesInteractor = get(),
            filterInteractor = get(),
            application = get()
        )
    }

    viewModel<FilterViewModel> {
        FilterViewModel(get(), get())
    }*/
}