package io.dwak.meh.view

import io.dwak.meh.model.Meh

public trait MainView {
    fun populatePage(currentMeh : Meh)
}