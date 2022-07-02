package com.goncharov.evgeny.obstacleavoid.components

import com.badlogic.ashley.core.Component

data class PositionComponent(
    var x: Float,
    var y: Float
) : Component