package com.goncharov.evgeny.obstacleavoid.common

import com.badlogic.ashley.core.ComponentMapper
import com.goncharov.evgeny.obstacleavoid.components.*

object Mappers {

    val bounds = ComponentMapper.getFor(BoundsComponent::class.java)
    val movement = ComponentMapper.getFor(MovementComponent::class.java)
    val position = ComponentMapper.getFor(PositionComponent::class.java)
    val obstacle = ComponentMapper.getFor(ObstacleComponent::class.java)
    val texture = ComponentMapper.getFor(TextureComponent::class.java)
    val dimension = ComponentMapper.getFor(DimensionComponent::class.java)
}