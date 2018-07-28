package com.github.elexx.spaarpot.application.transaction

import com.github.elexx.spaarpot.controllers.FileController
import com.github.elexx.spaarpot.domain.entities.Transaction
import tornadofx.*

class TransactionController : Controller() {

    private val fileController: FileController by inject()

    fun create(transaction: Transaction) {
        runAsync {
            fileController.createTransaction(transaction)
        }
    }

    fun listByAccountId(accountId: String): List<Transaction> {
        return fileController.listTransactionsByAccount(accountId).toList()
    }
}
