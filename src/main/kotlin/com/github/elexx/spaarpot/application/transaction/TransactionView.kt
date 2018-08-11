package com.github.elexx.spaarpot.application.transaction

import com.github.elexx.spaarpot.application.account.AccountList
import com.github.elexx.spaarpot.domain.viewmodel.AccountModel
import com.github.elexx.spaarpot.domain.viewmodel.TransactionModel
import com.github.elexx.spaarpot.domain.viewmodel.TransactionViewObject
import javafx.collections.ObservableList
import javafx.scene.layout.Priority
import tornadofx.*

class TransactionView : View() {

    private val controller: TransactionController by inject()

    private val selectedAccount: AccountModel by inject()
    private val selectedTransaction: TransactionModel by inject()

    private val transactions: ObservableList<TransactionViewObject> = mutableListOf<TransactionViewObject>().observable()

    init {
        selectedAccount.itemProperty.onChange {
            it?.apply {
                runAsync {
                    controller.listByAccountId(id)
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
            column(messages["table.col.payee"], TransactionViewObject::payeeProperty)
            column(messages["table.col.notes"], TransactionViewObject::notesProperty) {
                remainingWidth()
            }
            column(messages["table.col.postingTotal"], TransactionViewObject::postingTotalProperty)

            columnResizePolicy = SmartResize.POLICY

            bindSelected(selectedTransaction)
        }

        add(TransactionForm::class)
    }
}
