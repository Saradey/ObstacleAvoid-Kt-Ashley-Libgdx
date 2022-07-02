package com.goncharov.evgeny.obstacleavoid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.TextureRegion

data class TextureComponent(
    val textureRegion: TextureRegion
) : Component