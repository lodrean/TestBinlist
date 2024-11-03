package com.example.testbinlist.di

import com.example.testbinlist.data.SearchRepositoryImpl
import com.example.testbinlist.data.DataBaseRepositoryImpl
import com.example.testbinlist.data.sharing.SharingRepositoryImpl
import com.example.testbinlist.domain.DataBaseRepository
import com.example.testbinlist.domain.SearchRepository
import com.example.testbinlist.domain.SharingRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<SearchRepository> {
        SearchRepositoryImpl(get())
    }
    factory<DataBaseRepository> {
        DataBaseRepositoryImpl(get())
    }
    factory<SharingRepository> {
        SharingRepositoryImpl(get())
    }
}