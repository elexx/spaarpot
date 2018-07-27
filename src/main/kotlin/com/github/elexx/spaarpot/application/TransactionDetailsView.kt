package com.github.elexx.spaarpot.application

import com.github.elexx.spaarpot.domain.viewmodel.TransactionModel
import tornadofx.*

class TransactionDetailsView : View() {

    val selectedTransaction: TransactionModel by inject()

    override val root = form {
        minWidth = 140.0
        maxWidth = 250.0

        fieldset {
            field(messages["transaction.details.postingTotal"]) {
                //                textfield(selectedTransaction.postingTotal) {
//                    useMaxSize = true
//                }
            }
//                    field {
//                        togglebutton(messages["transaction.details.button.income"], toggleGroup) {
//                            useMaxSize = true
//                        }
//                        togglebutton(messages["transaction.details.button.expense"], toggleGroup) {
//                            useMaxSize = true
//                        }
//                    }
//            field(messages["transaction.details.date"]) {
//                datepicker(selectedTransaction.dueDate) {
//                    useMaxSize = true
//                }
//            }
//            field(messages["transaction.details.payee"]) {
//                textfield(selectedTransaction.pay) {
//                    useMaxSize = true
//                }
//            }
            field(messages["transaction.details.notes"]) {
                textarea(selectedTransaction.notes) {
                    useMaxWidth = true
                    prefHeight = 60.0
                }
            }
        }
    }
}
