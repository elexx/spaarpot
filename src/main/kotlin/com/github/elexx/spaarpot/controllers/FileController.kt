package com.github.elexx.spaarpot.controllers

import com.github.elexx.spaarpot.domain.entities.Account
import com.github.elexx.spaarpot.domain.entities.Transaction
import com.github.elexx.spaarpot.events.ShutdownEvent
import org.dizitart.kno2.filters.eq
import org.dizitart.kno2.getRepository
import org.dizitart.kno2.nitrite
import org.dizitart.no2.Nitrite
import tornadofx.*
import java.io.File
import java.time.OffsetDateTime

class FileController : Controller() {

    private lateinit var db: Nitrite

    init {
        subscribe<ShutdownEvent> { close() }
    }

    fun createOrOpen(path: File) {
        if (::db.isInitialized && !db.isClosed) {
            db.close()
        }

        db = nitrite {
            file = path
        }
        if (listAccounts().none()) {
            // TODO: Remove me!!
            val accountId1 = createAccount(Account("Testaccount 1"))
            createAccount(Account("Testaccount 2"))
            createTransaction(Transaction(OffsetDateTime.now(), 10, "payee1", "note1", "cat1", accountId1))
            createTransaction(Transaction(OffsetDateTime.now(), 10, "payee1", "note1", "cat1", accountId1))
            createTransaction(Transaction(OffsetDateTime.now(), 10, "payee1", "note1", "cat1", accountId1))
            createTransaction(Transaction(OffsetDateTime.now(), 10, "payee1", "note1", "cat1", accountId1))
        }
    }

    fun createTransaction(data: Transaction): String {
        db.getRepository<Transaction> {
            insert(data)
        }
        return data.id
    }

    fun createAccount(data: Account): String {
        db.getRepository<Account> {
            insert(data)
        }
        return data.id
    }

    fun listTransactionsByAccount(accountId: String) = db.getRepository<Transaction>().find(Transaction::account eq accountId)

    fun listAccounts() = db.getRepository<Account>().find()

    fun close() = db.close()
}
