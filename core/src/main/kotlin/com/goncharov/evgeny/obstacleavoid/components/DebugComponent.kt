package com.goncharov.evgeny.obstacleavoid.components

import com.badlogic.ashley.core.Component

class DebugComponent : Component {
    var renderDebug = false
    var renderFps = false
    var drawTexture = true
}