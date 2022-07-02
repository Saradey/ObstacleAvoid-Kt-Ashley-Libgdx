package com.goncharov.evgeny.obstacleavoid.systems.debug

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.Viewport

class GridRenderSystem(
    private val fameViewport: Viewport,
    private val renderer: ShapeRenderer
) : EntitySystem() {

}