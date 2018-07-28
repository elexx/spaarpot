package com.github.elexx.spaarpot.domain.entities

import org.dizitart.no2.objects.Id
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

//@Indices(Index(value = "account_fk", type = IndexType.NonUnique))
data class Transaction(
        var valueDate: LocalDate? = null,
        var postingTotal: BigDecimal? = null,
        var payee: String? = null,
        var notes: String? = null,
        var category: String? = null,

        // keys
        var account: String? = null,
        @Id val id: String = UUID.randomUUID().toString()
)
