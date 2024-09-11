package com.example.testbinlist.di

import com.example.testbinlist.domain.GetCardInfoUseCase
import com.example.testbinlist.domain.GetCardInfoUseCaseImpl
import org.koin.dsl.module


val useCaseModule = module {

    factory<GetCardInfoUseCase> {
        GetCardInfoUseCaseImpl(get())
    }
}