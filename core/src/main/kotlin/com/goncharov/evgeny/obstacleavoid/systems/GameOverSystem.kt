package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.EntitySystem
import com.goncharov.evgeny.obstacleavoid.common.EntityFactory
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.consts.gameManagerFamily
import com.goncharov.evgeny.obstacleavoid.navigation.KeyNavigation
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation

class GameOverSystem(
    private val navigation: Navigation,
    private val factory: EntityFactory
) : EntitySystem() {

    private val gm by lazy {
        Mappers.game[engine.getEntitiesFor(gameManagerFamily).first()]
    }
    private var isOverTime = 0f

    override fun update(deltaTime: Float) {
        if (gm.gameIsOver()) {
            isOverTime += deltaTime
            if (isOverTime > GAME_IS_OVER_PAUSE) {
                navigation.navigate(KeyNavigation.MenuKey)
            }
        }
        if (gm.reset && !gm.gameIsOver()) {
            engine.removeAllEntities()
            gm.reset = false
            addEntities()
        }
    }

    private fun addEntities() {
        factory.addBackground()
        factory.addPlayer()
    }

    companion object {
        private const val GAME_IS_OVER_PAUSE = 2f
    }
}