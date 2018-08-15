package com.github.elexx.spaarpot.application.transaction

import com.github.elexx.spaarpot.controllers.FileController
import com.github.elexx.spaarpot.domain.viewmodel.Transaction
import tornadofx.*

class TransactionController : Controller() {

    private val fileController: FileController by inject()
    val transactionsForSelectedAccount = mutableListOf<Transaction>().observable()

    fun create(transaction: Transaction) {
        runAsync {
            fileController.createTransaction(transaction)
        } ui {
            if (transactionsForSelectedAccount.none { item -> item.id == transaction.id }) {
                transactionsForSelectedAccount.add(transaction)
            }
        }
    }

    fun selectAccount(accountId: String) {
        runAsync {
            fileController.listTransactionsByAccount(accountId)
        } ui {
            transactionsForSelectedAccount.clear()
            transactionsForSelectedAccount.addAll(it)
        }
    }
}
