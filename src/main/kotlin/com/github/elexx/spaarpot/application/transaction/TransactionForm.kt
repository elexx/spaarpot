package com.github.elexx.spaarpot.application.transaction

import com.github.elexx.spaarpot.domain.entities.Transaction
import com.github.elexx.spaarpot.domain.viewmodel.AccountModel
import com.github.elexx.spaarpot.domain.viewmodel.TransactionModel
import javafx.util.converter.BigDecimalStringConverter
import tornadofx.*

class TransactionForm : Fragment() {

    private val controller: TransactionController by inject()

    private val selectedAccount: AccountModel by inject()
    private val model: TransactionModel by inject()

    override val root = form {
        fieldset {
            field(messages["transaction.details.postingTotal"]) {
                textfield(model.postingTotal, BigDecimalStringConverter()) {
                    useMaxSize = true
                }
            }
//            field {
//                togglebutton(messages["transaction.details.button.income"], toggleGroup) {
//                    useMaxSize = true
//                }
//                togglebutton(messages["transaction.details.button.expense"], toggleGroup) {
//                    useMaxSize = true
//                }
//            }
            field(messages["transaction.details.date"]) {
                datepicker(model.valueDate) {
                    useMaxSize = true
                }
            }
//            field(messages["transaction.details.payee"]) {
//                textfield(model.pay) {
//                    useMaxSize = true
//                }
//            }
            field(messages["transaction.details.notes"]) {
                textarea(model.notes) {
                    useMaxWidth = true
                    prefHeight = 60.0
                }
            }
        }

        button(messages["form.save"]) {
            isDefaultButton = true
            action {
                saveTransaction()
            }
        }
    }

    fun saveTransaction() {
        if (model.isEmpty) model.item = Transaction()

        model.commit {
            model.account.value = selectedAccount.id.value
            controller.create(model.item)
        }
    }
}
