package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.Viewport

class RenderSystem(
    private val gameViewport: Viewport,
    private val batch: SpriteBatch
) : EntitySystem() {

}