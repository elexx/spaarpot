package com.github.elexx.spaarpot.domain.viewmodel

import javafx.beans.property.SimpleStringProperty
import org.dizitart.no2.Document
import org.dizitart.no2.mapper.Mappable
import org.dizitart.no2.mapper.NitriteMapper
import tornadofx.*
import java.util.*

class AccountModel : ItemViewModel<Account>() {
    val name = bind(Account::nameProperty)
    val id = bind(Account::idProperty)
}

class Account : Mappable {
    val nameProperty = SimpleStringProperty()
    var name by nameProperty

    val idProperty = SimpleStringProperty(UUID.randomUUID().toString())
    var id by idProperty

    override fun write(mapper: NitriteMapper): Document {
        val doc = Document()
        doc["name"] = name
        doc["id"] = id
        return doc
    }

    override fun read(mapper: NitriteMapper, doc: Document) {
        name = doc["name"] as String?
        id = doc["id"] as String?
    }
}
