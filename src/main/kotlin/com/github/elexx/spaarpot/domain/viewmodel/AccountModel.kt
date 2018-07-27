package com.github.elexx.spaarpot.domain.viewmodel

import com.github.elexx.spaarpot.domain.entities.Account
import tornadofx.*

class AccountModel : ItemViewModel<Account>(Account()) {
    val name = bind(Account::name)

    // keys
    val id = bind(Account::id)
}
