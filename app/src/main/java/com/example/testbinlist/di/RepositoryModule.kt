package com.example.testbinlist.di

import com.example.testbinlist.data.SearchRepositoryImpl
import com.example.testbinlist.domain.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<SearchRepository> {
        SearchRepositoryImpl(get())
    }
}