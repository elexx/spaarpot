package com.github.elexx.spaarpot.application

import tornadofx.*

class EmptyView : View("Empty") {

    override val root = label(messages["view.empty.text"])
}
