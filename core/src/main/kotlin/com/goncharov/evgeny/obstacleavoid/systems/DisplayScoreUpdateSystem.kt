package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.consts.gameManagerFamily
import kotlin.math.min

/**
 * Для более плавного вывода результата
 */
class DisplayScoreUpdateSystem : IteratingSystem(
    gameManagerFamily
) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val gameComponent = Mappers.game[entity]
        if (gameComponent.displayScore < gameComponent.score) {
            gameComponent.displayScore = min(
                gameComponent.score,
                gameComponent.displayScore + (60 * deltaTime).toInt()
            )
        }
    }
}