package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.ashley.core.Family
import com.goncharov.evgeny.obstacleavoid.common.EntityFactory
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.components.DebugComponent
import com.goncharov.evgeny.obstacleavoid.components.GameManagerComponent
import com.goncharov.evgeny.obstacleavoid.consts.gameManagerFamily
import com.goncharov.evgeny.obstacleavoid.managers.SavedManagers
import com.goncharov.evgeny.obstacleavoid.navigation.KeyNavigation
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation

/**
 * Система завершения игры
 */
class GameOverSystem(
    private val navigation: Navigation,
    private val factory: EntityFactory,
    private val savedManagers: SavedManagers
) : EntitySystem() {

    private val gameComponent by lazy {
        Mappers.game[engine.getEntitiesFor(gameManagerFamily).first()]
    }
    private var isOverTime = 0f
    private val removeFamily = Family.exclude(
        GameManagerComponent::class.java,
        DebugComponent::class.java
    ).get()

    override fun update(deltaTime: Float) {
        if (gameComponent.gameIsOver()) {
            isOverTime += deltaTime
            if (isOverTime > GAME_IS_OVER_PAUSE) {
                savedManagers.savedHighScoreScore()
                navigation.navigate(KeyNavigation.MenuKey)
            }
        }
        if (gameComponent.reset && !gameComponent.gameIsOver()) {
            engine.removeAllEntities(removeFamily)
            gameComponent.reset = false
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