package com.github.elexx.spaarpot.application.transaction

import com.github.elexx.spaarpot.application.account.AccountList
import com.github.elexx.spaarpot.domain.viewmodel.AccountModel
import com.github.elexx.spaarpot.domain.viewmodel.TransactionModel
import com.github.elexx.spaarpot.domain.viewmodel.Transaction
import javafx.scene.layout.Priority
import tornadofx.*

class TransactionView : View() {

    private val controller: TransactionController by inject()

    private val selectedAccount: AccountModel by inject()
    private val selectedTransaction: TransactionModel by inject()


    init {
        selectedAccount.itemProperty.onChange {
            it?.apply {
                controller.selectAccount(id)
            }
        }
    }

    override val root = hbox {
        vbox {
            minWidth = 140.0
            maxWidth = 220.0

            add(AccountList::class)
        }
        tableview(controller.transactionsForSelectedAccount) {
            hgrow = Priority.ALWAYS
//            readonlyColumn(messages["table.col.status"], Transaction::status)
//            readonlyColumn(messages["table.col.date"], Transaction::dueDate)
            column(messages["table.col.payee"], Transaction::payeeProperty)
            column(messages["table.col.notes"], Transaction::notesProperty) {
                remainingWidth()
            }
            column(messages["table.col.postingTotal"], Transaction::postingTotalProperty) {
                converter(CurrencyStringConverter())
            }

            columnResizePolicy = SmartResize.POLICY

            bindSelected(selectedTransaction)
        }

        add(TransactionForm::class)
    }
}
