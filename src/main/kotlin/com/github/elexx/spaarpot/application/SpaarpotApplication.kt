package com.github.elexx.spaarpot.application

import com.github.elexx.spaarpot.events.ShutdownEvent
import tornadofx.*

fun main(args: Array<String>) = launch<SpaarpotApplication>(args)

class SpaarpotApplication : App(MainWindow::class, Styles::class) {
    override fun stop() {
        fire(ShutdownEvent)
        super.stop()
    }
}
