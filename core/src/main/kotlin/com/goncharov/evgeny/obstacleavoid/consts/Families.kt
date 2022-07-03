package com.goncharov.evgeny.obstacleavoid.consts

import com.badlogic.ashley.core.Family
import com.goncharov.evgeny.obstacleavoid.components.DebugComponent
import com.goncharov.evgeny.obstacleavoid.components.GameManagerComponent

val gameManagerFamily: Family = Family.all(
    GameManagerComponent::class.java,
    DebugComponent::class.java
).get()