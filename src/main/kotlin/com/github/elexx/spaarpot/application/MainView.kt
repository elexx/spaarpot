package com.github.elexx.spaarpot.application

import com.github.elexx.spaarpot.application.budget.BudgetView
import com.github.elexx.spaarpot.application.transaction.TransactionView
import javafx.scene.layout.Priority
import tornadofx.*


class MainView : View("My View") {

    private val transactionView: TransactionView by inject()
    private val budgetView: BudgetView by inject()

    override val root = drawer {
        vgrow = Priority.ALWAYS
        item(messages["drawer.item.transaction"], expanded = true) {
            add(transactionView)
        }

        item(messages["drawer.item.budget"]) {
            add(budgetView)
        }
    }
}
