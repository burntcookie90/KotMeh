package io.dwak.meh

import io.dwak.meh.model.CurrentMeh

public trait MainView {
    fun populatePage(currentMeh : CurrentMeh)
}