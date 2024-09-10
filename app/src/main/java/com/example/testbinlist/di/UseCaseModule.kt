package com.example.testbinlist.di

import com.example.testbinlist.domain.GetCardInfoUseCase
import com.example.testbinlist.domain.GetCardInfoUseCaseImpl
import org.koin.dsl.module


val useCaseModule= module {
/*
    single<VacanciesRepository> {
        VacanciesRepositoryImpl(
            context = get(),
            networkClient = get(),
        )
    }

    single<DictionariesRepository> {
        DictionariesRepositoryImpl(
            context = get(),
            networkClient = get(),
        )
    }

    single<FavoritesRepository> {
        FavoritesRepositoryImpl(
            database = get(),
        )
    }

    single<SharingRepository> {
        SharingRepositoryImpl(get(), get())
    }

    single<FilterRepository> {
        FilterRepostoryImpl(
            context = get(),
        )
    }*/
    factory<GetCardInfoUseCase> {
        GetCardInfoUseCaseImpl(get())
    }
}