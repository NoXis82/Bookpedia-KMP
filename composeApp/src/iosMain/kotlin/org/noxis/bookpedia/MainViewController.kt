package org.noxis.bookpedia

import androidx.compose.ui.window.ComposeUIViewController
import org.noxis.bookpedia.app.App
import org.noxis.bookpedia.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}