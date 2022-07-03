package com.goncharov.evgeny.obstacleavoid.screens.game

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.Input
import com.goncharov.evgeny.obstacleavoid.common.BaseInputProcessor
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.consts.gameManagerFamily

class GameController(
    private val engine: Engine
) : BaseInputProcessor() {

    override fun keyDown(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.C -> {
                val entity = engine.getEntitiesFor(gameManagerFamily).first()
                val debugComponent = Mappers.debug[entity]
                debugComponent.renderDebug = !debugComponent.renderDebug
            }
            Input.Keys.B -> {
                val entity = engine.getEntitiesFor(gameManagerFamily).first()
                val debugComponent = Mappers.debug[entity]
                debugComponent.renderFps = !debugComponent.renderFps
            }
            Input.Keys.SPACE -> {
                val entity = engine.getEntitiesFor(gameManagerFamily).first()
                val gameComponent = Mappers.game[entity]
                gameComponent.gameIsPause = !gameComponent.gameIsPause
            }
            Input.Keys.V -> {
                val entity = engine.getEntitiesFor(gameManagerFamily).first()
                val debugComponent = Mappers.debug[entity]
                debugComponent.drawTexture = !debugComponent.drawTexture
            }
        }
        return true
    }
}