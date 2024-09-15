package com.example.testbinlist.domain

interface SharingInteractor {
    fun openLink(link: String)

    fun openDialer(phoneNumber: String)

    fun openMap(uri: String)
}