package com.github.elexx.spaarpot.application.transaction

import com.github.elexx.spaarpot.controllers.FileController
import com.github.elexx.spaarpot.domain.viewmodel.TransactionViewObject
import tornadofx.*

class TransactionController : Controller() {

    private val fileController: FileController by inject()

    fun create(transaction: TransactionViewObject) {
        runAsync {
            fileController.createTransaction(transaction.toModel())
        }
    }

    fun listByAccountId(accountId: String): List<TransactionViewObject> {
        return fileController.listTransactionsByAccount(accountId).toList().map(::TransactionViewObject)
    }
}
