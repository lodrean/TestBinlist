package com.example.testbinlist.data.sharing

import ExternalNavigator
import com.example.testbinlist.domain.SharingRepository

class SharingRepositoryImpl(
    private val externalNavigator: ExternalNavigator,
) : SharingRepository {
    override fun openLink(link: String) {
        externalNavigator.openLink(link)
    }


    override fun openMap(uri: String) {
        externalNavigator.openMap(uri)
    }

    override fun openDialer(phoneNumber: String) {
        externalNavigator.openPhone(phoneNumber)
    }
}

