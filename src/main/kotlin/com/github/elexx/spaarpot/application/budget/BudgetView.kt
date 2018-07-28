package com.github.elexx.spaarpot.application.budget

import tornadofx.*

class BudgetView : View() {
    override val root = borderpane {
        center = label("Budget goes here")
    }
}
