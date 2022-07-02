package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.goncharov.evgeny.obstacleavoid.components.MovementComponent
import com.goncharov.evgeny.obstacleavoid.components.PositionComponent

class MovementSystem : IteratingSystem(
    Family.all(
        PositionComponent::class.java,
        MovementComponent::class.java
    ).get()
) {

    override fun processEntity(entity: Entity, deltaTime: Float) {

    }
}