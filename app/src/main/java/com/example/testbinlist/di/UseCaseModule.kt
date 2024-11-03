package com.example.testbinlist.di

import com.example.testbinlist.data.sharing.SharingRepositoryImpl
import com.example.testbinlist.domain.GetCardInfoUseCase
import com.example.testbinlist.domain.GetCardInfoUseCaseImpl
import com.example.testbinlist.domain.SharingRepository
import org.koin.dsl.module

val useCaseModule = module {

    factory<GetCardInfoUseCase> {
        GetCardInfoUseCaseImpl(get())
    }
    factory<SharingRepository> {
        SharingRepositoryImpl(get())
    }
}