package com.goncharov.evgeny.obstacleavoid.common

import com.badlogic.ashley.core.ComponentMapper
import com.goncharov.evgeny.obstacleavoid.components.*

object Mappers {
    val bounds: ComponentMapper<BoundsComponent> =
        ComponentMapper.getFor(BoundsComponent::class.java)
    val movement: ComponentMapper<MovementComponent> =
        ComponentMapper.getFor(MovementComponent::class.java)
    val position: ComponentMapper<PositionComponent> =
        ComponentMapper.getFor(PositionComponent::class.java)
    val obstacle: ComponentMapper<ObstacleComponent> =
        ComponentMapper.getFor(ObstacleComponent::class.java)
    val texture: ComponentMapper<TextureComponent> =
        ComponentMapper.getFor(TextureComponent::class.java)
    val dimension: ComponentMapper<DimensionComponent> =
        ComponentMapper.getFor(DimensionComponent::class.java)
}