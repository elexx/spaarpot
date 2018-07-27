package com.github.elexx.spaarpot.domain.entities

import org.dizitart.no2.objects.Id
import java.time.OffsetDateTime
import java.util.*

//@Indices(Index(value = "account_fk", type = IndexType.NonUnique))
data class Transaction(
        var valueDate: OffsetDateTime,
        var postingTotal: Int,
        var payee: String,
        var notes: String,
        var category: String,

        // keys
        var account: String,
        @Id val id: String = UUID.randomUUID().toString()
)
