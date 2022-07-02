package com.goncharov.evgeny.obstacleavoid.common

import com.badlogic.gdx.InputProcessor

abstract class BaseInputProcessor : InputProcessor {

    override fun keyDown(keycode: Int): Boolean = true

    override fun keyUp(keycode: Int): Boolean = true

    override fun keyTyped(character: Char): Boolean = true

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = true

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = true

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = true

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean = true

    override fun scrolled(amountX: Float, amountY: Float): Boolean = true
}