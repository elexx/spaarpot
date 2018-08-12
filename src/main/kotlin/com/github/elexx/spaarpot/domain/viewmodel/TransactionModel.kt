package com.github.elexx.spaarpot.domain.viewmodel

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import org.dizitart.no2.Document
import org.dizitart.no2.mapper.Mappable
import org.dizitart.no2.mapper.NitriteMapper
import tornadofx.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

class TransactionModel : ItemViewModel<Transaction>() {
    val valueDate = bind(Transaction::valueDateProperty)
    val postingTotal = bind(Transaction::postingTotalProperty)
    val payee = bind(Transaction::payeeProperty)
    val notes = bind(Transaction::notesProperty)
    val category = bind(Transaction::categoryProperty)
    val account = bind(Transaction::accountProperty)
    val id = bind(Transaction::idProperty)
}


class Transaction : Mappable {
    val valueDateProperty = SimpleObjectProperty<LocalDate>()
    var valueDate by valueDateProperty

    val postingTotalProperty = SimpleObjectProperty<BigDecimal>()
    var postingTotal by postingTotalProperty

    val payeeProperty = SimpleStringProperty()
    var payee by payeeProperty

    val notesProperty = SimpleStringProperty()
    var notes by notesProperty

    val categoryProperty = SimpleStringProperty()
    var category by categoryProperty

    val accountProperty = SimpleStringProperty()
    var account by accountProperty

    val idProperty = SimpleStringProperty(UUID.randomUUID().toString())
    var id by idProperty

    override fun write(mapper: NitriteMapper): Document {
        val doc = Document()
        doc["valueDate"] = valueDate
        doc["postingTotal"] = postingTotal
        doc["payee"] = payee
        doc["notes"] = notes
        doc["category"] = category
        doc["account"] = account
        doc["id"] = id
        return doc
    }

    override fun read(mapper: NitriteMapper, doc: Document) {
        valueDate = doc["valueDate"] as LocalDate?
        postingTotal = doc["postingTotal"] as BigDecimal?
        payee = doc["payee"] as String?
        notes = doc["notes"] as String?
        category = doc["category"] as String?
        account = doc["account"] as String?
        id = doc["id"] as String?
    }
}
