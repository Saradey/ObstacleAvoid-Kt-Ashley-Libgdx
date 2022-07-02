package com.goncharov.evgeny.obstacleavoid.systems.debug

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.OrthographicCamera

class DebugCameraSystem(
    private val gameCamera: OrthographicCamera,
    private val startX: Float,
    private val startY: Float
) : EntitySystem() {


}