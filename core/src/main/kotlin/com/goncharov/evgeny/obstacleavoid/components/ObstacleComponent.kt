package com.goncharov.evgeny.obstacleavoid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool

data class ObstacleComponent(var hit: Boolean) : Component, Pool.Poolable {

    override fun reset() {
        hit = false
    }
}