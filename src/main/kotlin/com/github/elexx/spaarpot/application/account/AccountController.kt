package com.github.elexx.spaarpot.application.account

import com.github.elexx.spaarpot.controllers.FileController
import com.github.elexx.spaarpot.domain.entities.Account
import javafx.collections.ObservableList
import tornadofx.*

class AccountController : Controller() {

    private val fileController: FileController by inject()

    private val list: ObservableList<Account> = mutableListOf<Account>().observable()

    fun create(account: Account) {
        runAsync {
            fileController.createAccount(account)
        } ui {
            list.add(account)
        }
    }

    fun list(): ObservableList<Account> {
        if (list.isEmpty()) {
            runAsync {
                fileController.listAccounts()
            } ui {
                list.addAll(it)
            }
        }
        return list
    }
}
