package com.github.elexx.spaarpot.domain.viewmodel

import com.github.elexx.spaarpot.domain.entities.Transaction
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.math.BigDecimal
import java.time.LocalDate

class TransactionModel : ItemViewModel<TransactionViewObject>() {
    val valueDate = bind(TransactionViewObject::valueDateProperty)
    val postingTotal = bind(TransactionViewObject::postingTotalProperty)
    val payee = bind(TransactionViewObject::payeeProperty)
    val notes = bind(TransactionViewObject::notesProperty)
    val category = bind(TransactionViewObject::categoryProperty)
    val account = bind(TransactionViewObject::accountProperty)
    val id = bind(TransactionViewObject::idProperty)
}


class TransactionViewObject(transaction: Transaction? = null) {
    val valueDateProperty = SimpleObjectProperty<LocalDate>(transaction?.valueDate)
    var valueDate by valueDateProperty

    val postingTotalProperty = SimpleObjectProperty<BigDecimal>(transaction?.postingTotal)
    var postingTotal by postingTotalProperty

    val payeeProperty = SimpleStringProperty(transaction?.payee)
    var payee by payeeProperty

    val notesProperty = SimpleStringProperty(transaction?.notes)
    var notes by notesProperty

    val categoryProperty = SimpleStringProperty(transaction?.category)
    var category by categoryProperty

    val accountProperty = SimpleStringProperty(transaction?.account)
    var account by accountProperty

    val idProperty = SimpleStringProperty(transaction?.id)
    var id by idProperty

    fun toModel(): Transaction {
        return Transaction(valueDate, postingTotal, payee, notes, category, account, id)
    }
}
