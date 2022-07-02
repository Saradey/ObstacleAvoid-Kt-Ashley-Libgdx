package com.goncharov.evgeny.obstacleavoid.components

import com.badlogic.ashley.core.Component

data class DimensionComponent(
    val width: Float,
    val height: Float
) : Component
