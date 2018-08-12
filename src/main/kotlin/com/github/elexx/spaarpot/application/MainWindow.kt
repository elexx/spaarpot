package com.github.elexx.spaarpot.application

import com.github.elexx.spaarpot.application.account.AccountController
import com.github.elexx.spaarpot.application.account.AccountForm
import com.github.elexx.spaarpot.controllers.FileController
import com.github.elexx.spaarpot.domain.viewmodel.Account
import com.github.elexx.spaarpot.domain.viewmodel.AccountModel
import javafx.geometry.Insets
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.Priority
import javafx.stage.FileChooser
import tornadofx.*

class MainWindow : View() {
    private val welcomeView: EmptyView by inject()
    private val mainView: MainView by inject()

    private val fileController: FileController by inject()
    private val accountController: AccountController by inject()

    init {
        primaryStage.width = 1200.0
        primaryStage.height = 800.0
    }

    override val root = borderpane {
        top = menubar {
            menu(messages["menu.file"]) {
                item(messages["menu.file.new"], KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN)).action { newFileAction() }
                item(messages["menu.file.open"], KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN)).action { openFileAction() }
                separator()
                item(messages["menu.file.exit"]).action { primaryStage.close() }
            }
            menu(messages["menu.account"]) {
                item(messages["menu.account.new"]).action { newAccount() }
            }
        }

        center {
            add(welcomeView)
        }

        bottom = hbox {
            padding = Insets(3.0)
            vgrow = Priority.NEVER
            label {
                maxHeight = 1.8
                hgrow = Priority.ALWAYS
                text = messages["statusbar.left.demo"]
            }
            pane {
                hgrow = Priority.ALWAYS
            }
            label {
                maxHeight = 1.8
                hgrow = Priority.NEVER
                text = messages["statusbar.right.demo"]
            }
        }
    }

    private fun newAccount() {
        val accountModel = AccountModel()
        accountModel.item = Account()
        val newScope = Scope(accountModel)
        find<AccountForm>(newScope).openModal(block = true)
        newScope.deregister()
        accountController.create(accountModel.item)
    }


    private fun newFileAction() {
        val files = chooseFile(messages["welcome.new"], filters = filters, mode = FileChooserMode.Save, owner = this@MainWindow.currentStage)
        if (!files.isEmpty()) {
            runAsync {
                fileController.createOrOpen(files[0])
            } ui {
                root.center.replaceWith(mainView.root)
            }
        }
    }

    private fun openFileAction() {
        val files = chooseFile(messages["welcome.open"], filters = filters, mode = FileChooserMode.Single, owner = this@MainWindow.currentStage)
        if (!files.isEmpty()) {
            runAsync {
                fileController.createOrOpen(files[0])
            } ui {
                root.center.replaceWith(mainView.root)
            }
        }
    }

    companion object {
        private val filters = arrayOf(FileChooser.ExtensionFilter("Envelope File (*.nvlp)", "*.nvlp"))
    }
}
