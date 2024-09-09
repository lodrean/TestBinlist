package com.example.testbinlist.di

import org.koin.dsl.module

val dataModule = module {

    /* single {
         Gson()
     }*/

    /*single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }*/
    /*single<OkHttp> {

    }*/
    /*single<Api> {
        Ktor.Builder()
            .baseUrl("https://api.hh.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HHApi::class.java)
    }*/


    /*single {
        ExternalNavigator(get())
    }*/

}
