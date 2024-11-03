package com.example.testbinlist.domain

interface SharingRepository {
    fun openLink(link: String)

    fun openDialer(phoneNumber: String)

    fun openMap(uri: String)
}