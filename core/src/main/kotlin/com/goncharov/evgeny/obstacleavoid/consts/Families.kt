package com.goncharov.evgeny.obstacleavoid.consts

import com.badlogic.ashley.core.Family
import com.goncharov.evgeny.obstacleavoid.components.GameManagerComponent

val gameManagerFamily: Family = Family.all(
    GameManagerComponent::class.java
).get()