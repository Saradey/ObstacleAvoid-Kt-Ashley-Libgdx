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

    override fun update(deltaTime: Float) {
        if (gm.lives <= 0) {
            navigation.navigate(KeyNavigation.MenuKey)
        }
        if (gm.reset) {
            engine.removeAllEntities()
            gm.reset = false
            addEntities()
        }
    }

    private fun addEntities() {
        factory.addBackground()
        factory.addPlayer()
    }
}