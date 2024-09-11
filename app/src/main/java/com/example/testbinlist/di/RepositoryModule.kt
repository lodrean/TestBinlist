package com.example.testbinlist.di

import com.example.testbinlist.data.SearchRepositoryImpl
import com.example.testbinlist.data.db.DataBaseRepositoryImpl
import com.example.testbinlist.domain.DataBaseRepository
import com.example.testbinlist.domain.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<SearchRepository> {
        SearchRepositoryImpl(get())
    }
    factory<DataBaseRepository> {
        DataBaseRepositoryImpl(get())
    }
}