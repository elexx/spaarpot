package com.github.elexx.spaarpot.domain.viewmodel

import com.github.elexx.spaarpot.domain.entities.Transaction
import tornadofx.*

class TransactionModel : ItemViewModel<Transaction>() {
//    val category = bind(Transaction::category)
//    val payee = bind(Transaction::payee)
//    val amount = bind(Transaction::postingTotal)
//    val notes = bind(Transaction::notes)


    val valueDate = bind(Transaction::valueDate)
    val postingTotal = bind(Transaction::postingTotal)
    val notes = bind(Transaction::notes)

    // keys
    val id = bind(Transaction::id)
    val account = bind(Transaction::account)
}
