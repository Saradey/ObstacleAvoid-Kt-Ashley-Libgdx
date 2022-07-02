package com.goncharov.evgeny.obstacleavoid.common

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.goncharov.evgeny.obstacleavoid.util.FormatUtils

abstract class BaseScreen : Screen, InputProcessor {

    private val logTag = javaClass.simpleName

    override fun show() = Unit

    override fun render(delta: Float) = Unit

    override fun resize(width: Int, height: Int) = Unit

    override fun pause() {
        debug("pause")
    }

    override fun resume() {
        debug("resume")
    }

    override fun hide() = Unit

    override fun dispose() = Unit

    protected fun debug(message: String) {
        Gdx.app.debug(
            logTag,
            "${FormatUtils.dateFormat.format(FormatUtils.calendar.time)} $message"
        )
    }

    override fun keyDown(keycode: Int) = true

    override fun keyUp(keycode: Int) = true

    override fun keyTyped(character: Char) = true

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int) = true

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int) = true

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int) = true

    override fun mouseMoved(screenX: Int, screenY: Int) = true

    override fun scrolled(amountX: Float, amountY: Float) = true
}