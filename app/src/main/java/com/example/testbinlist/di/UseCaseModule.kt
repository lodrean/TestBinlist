package com.example.testbinlist.di

import com.example.testbinlist.data.sharing.SharingInteractorImpl
import com.example.testbinlist.domain.GetCardInfoUseCase
import com.example.testbinlist.domain.GetCardInfoUseCaseImpl
import com.example.testbinlist.domain.SharingInteractor
import org.koin.dsl.module

val useCaseModule = module {

    factory<GetCardInfoUseCase> {
        GetCardInfoUseCaseImpl(get())
    }
    factory<SharingInteractor> {
        SharingInteractorImpl(get())
    }
}