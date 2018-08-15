package com.github.elexx.spaarpot.controllers

import com.github.elexx.spaarpot.domain.viewmodel.Account
import com.github.elexx.spaarpot.domain.viewmodel.Transaction
import com.github.elexx.spaarpot.events.ShutdownEvent
import org.dizitart.kno2.filters.eq
import org.dizitart.kno2.getRepository
import org.dizitart.kno2.nitrite
import org.dizitart.no2.Nitrite
import tornadofx.*
import java.io.File

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
            disableShutdownHook = true
        }
    }

    fun createTransaction(data: Transaction) {
        db.getRepository<Transaction> {
            update(Transaction::id eq data.id, data, true)
        }
    }

    fun createAccount(data: Account) {
        db.getRepository<Account> {
            update(Account::id eq data.id, data, true)
        }
    }

    fun listTransactionsByAccount(accountId: String) = db.getRepository<Transaction>().find(Transaction::account eq accountId)

    fun listAccounts() = db.getRepository<Account>().find()

    fun close() = db.close()
}
