package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.systems.IntervalSystem
import com.goncharov.evgeny.obstacleavoid.common.EntityFactory
import com.goncharov.evgeny.obstacleavoid.consts.OBSTACLE_SPAWN_TIME

class ObstacleSpawnSystem(
    private val factory: EntityFactory
) : IntervalSystem(
    OBSTACLE_SPAWN_TIME
) {

    override fun updateInterval() {

    }
}