package com.github.elexx.spaarpot.application.transaction

import com.github.elexx.spaarpot.domain.viewmodel.AccountModel
import com.github.elexx.spaarpot.domain.viewmodel.Transaction
import com.github.elexx.spaarpot.domain.viewmodel.TransactionModel
import javafx.util.converter.BigDecimalStringConverter
import tornadofx.*

class TransactionForm : Fragment() {

    private val controller: TransactionController by inject()

    private val selectedAccount: AccountModel by inject()
    private val model: TransactionModel by inject()

    override val root = vbox {
        button(messages["transaction.new"]) {
            enableWhen { !model.dirty }
            action {
                model.item = Transaction()
                model.account.value = selectedAccount.id.value
            }
        }

        form {
            fieldset {
                enableWhen {
                    model.itemProperty.isNotNull
                }
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
                    textfield(model.notes) {
                        useMaxWidth = true
                    }
                }
            }

            buttonbar {
                button(messages["form.cancel"]) {
                    action { model.rollback() }
                    enableWhen { model.dirty }
                }
                button(messages["form.save"]) {
                    isDefaultButton = true
                    action { saveTransaction() }
                    enableWhen { model.dirty }
                }
            }
        }
    }

    fun saveTransaction() {
        model.commit {
            controller.create(model.item)
        }
    }
}
