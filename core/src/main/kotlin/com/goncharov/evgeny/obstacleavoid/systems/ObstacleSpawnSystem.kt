package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.systems.IntervalSystem
import com.badlogic.gdx.math.MathUtils
import com.goncharov.evgeny.obstacleavoid.common.EntityFactory
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.consts.*

/**
 * Система спавна противника
 */
class ObstacleSpawnSystem(
    private val factory: EntityFactory
) : IntervalSystem(
    OBSTACLE_SPAWN_TIME
) {

    private val gameComponent by lazy {
        Mappers.game[engine.getEntitiesFor(gameManagerFamily).first()]
    }

    override fun updateInterval() {
        if (!gameComponent.gameIsPause) {
            val min = 0f
            val max = WORLD_WIDTH - OBSTACLE_SIZE
            factory.addObstacle(MathUtils.random(min, max), WORLD_HEIGHT)
        }
    }
}