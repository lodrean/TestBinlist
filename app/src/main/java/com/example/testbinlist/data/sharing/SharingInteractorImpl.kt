package com.example.testbinlist.data.sharing

import ExternalNavigator
import com.example.testbinlist.domain.SharingInteractor

class SharingInteractorImpl(
    private val externalNavigator: ExternalNavigator,
) : SharingInteractor {
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
