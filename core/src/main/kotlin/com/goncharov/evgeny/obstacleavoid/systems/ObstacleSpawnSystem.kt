package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.systems.IntervalSystem
import com.badlogic.gdx.math.MathUtils
import com.goncharov.evgeny.obstacleavoid.common.EntityFactory
import com.goncharov.evgeny.obstacleavoid.consts.OBSTACLE_SIZE
import com.goncharov.evgeny.obstacleavoid.consts.OBSTACLE_SPAWN_TIME
import com.goncharov.evgeny.obstacleavoid.consts.WORLD_HEIGHT
import com.goncharov.evgeny.obstacleavoid.consts.WORLD_WIDTH

class ObstacleSpawnSystem(
    private val factory: EntityFactory
) : IntervalSystem(
    OBSTACLE_SPAWN_TIME
) {

    override fun updateInterval() {
        val min = 0f
        val max = WORLD_WIDTH - OBSTACLE_SIZE
        factory.addObstacle(MathUtils.random(min, max), WORLD_HEIGHT)
    }
}