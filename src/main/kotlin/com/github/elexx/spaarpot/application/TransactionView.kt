package com.github.elexx.spaarpot.application

import com.github.elexx.spaarpot.application.account.AccountList
import com.github.elexx.spaarpot.controllers.FileController
import com.github.elexx.spaarpot.domain.entities.Transaction
import com.github.elexx.spaarpot.domain.viewmodel.AccountModel
import com.github.elexx.spaarpot.domain.viewmodel.TransactionModel
import javafx.collections.ObservableList
import javafx.scene.layout.Priority
import tornadofx.*

class TransactionView : View() {

    private val controller: FileController by inject()

    private val selectedAccount: AccountModel by inject()
    private val selectedTransaction: TransactionModel by inject()

    private val transactions: ObservableList<Transaction> = mutableListOf<Transaction>().observable()

    init {
        selectedAccount.itemProperty.onChange {
            it?.apply {
                runAsync {
                    controller.listTransactionsByAccount(id)
                } ui {
                    transactions.clear()
                    transactions.addAll(it)
                }
            }
        }
    }

    override val root = hbox {
        vbox {
            minWidth = 140.0
            maxWidth = 220.0

            add(AccountList::class)
        }
        tableview(transactions) {
            hgrow = Priority.ALWAYS
//            readonlyColumn(messages["table.col.status"], Transaction::status)
//            readonlyColumn(messages["table.col.date"], Transaction::dueDate)
            readonlyColumn(messages["table.col.payee"], Transaction::payee)
            readonlyColumn(messages["table.col.notes"], Transaction::notes) {
                remainingWidth()
            }
            readonlyColumn(messages["table.col.postingTotal"], Transaction::postingTotal)

            columnResizePolicy = SmartResize.POLICY

            bindSelected(selectedTransaction)
        }

        add(TransactionDetailsView::class)
    }
}
