package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.Viewport

class UiRenderSystem(
    private val font: BitmapFont,
    private val uiViewport: Viewport,
    private val batch: SpriteBatch
) : EntitySystem() {

}