package com.goncharov.evgeny.obstacleavoid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool

class ObstacleComponent : Component, Pool.Poolable {

    var hit: Boolean = false

    override fun reset() {
        hit = false
    }
}