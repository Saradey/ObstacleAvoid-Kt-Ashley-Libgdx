package com.goncharov.evgeny.obstacleavoid.util

import com.badlogic.gdx.Gdx

object LogDebugUtils {

    fun debug(tag: String, message: String) {
        Gdx.app.debug(tag, message)
    }
}