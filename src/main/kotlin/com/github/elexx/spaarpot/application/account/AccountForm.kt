package com.github.elexx.spaarpot.application.account

import com.github.elexx.spaarpot.domain.viewmodel.AccountModel
import tornadofx.*

class AccountForm : Fragment() {

    private val accountModel: AccountModel by inject()

    override val root = form {
        fieldset {
            field(messages["form.account.name"]) {
                textfield(accountModel.name)
                        .required()
            }
        }

        button(messages["form.save"]) {
            isDefaultButton = true
            action {
                accountModel.commit {
                    close()
                }
            }
        }
    }
}
