package com.github.elexx.spaarpot.application.account

import com.github.elexx.spaarpot.domain.viewmodel.AccountModel
import javafx.scene.layout.Priority
import tornadofx.*

class AccountList : View() {

    private val controller: AccountController by inject()

    private val selectedAccount: AccountModel by inject()


    override val root = listview(controller.list()) {
        vgrow = Priority.ALWAYS

        cellFormat {
            text = it.name
        }

        bindSelected(selectedAccount)
    }
}
