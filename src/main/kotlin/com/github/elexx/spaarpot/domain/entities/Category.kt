package com.github.elexx.spaarpot.domain.entities

import org.dizitart.no2.objects.Id
import java.util.*

data class Category(
        var name: String,

        // keys
        @Id var id: String = UUID.randomUUID().toString()
)
