package com.goncharov.evgeny.obstacleavoid.common

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.goncharov.evgeny.obstacleavoid.components.BoundsComponent
import com.goncharov.evgeny.obstacleavoid.components.PositionComponent
import com.goncharov.evgeny.obstacleavoid.consts.GAME_PLAY
import com.goncharov.evgeny.obstacleavoid.consts.PLAYER_BOUNDS_RADIUS
import com.goncharov.evgeny.obstacleavoid.consts.PLAYER_SIZE
import com.goncharov.evgeny.obstacleavoid.consts.WORLD_WIDTH

class EntityFactory(
    private val engine: PooledEngine,
    private val assetManager: AssetManager
) {

    private val gamePlayAtlas: TextureAtlas = assetManager[GAME_PLAY]

    fun addPlayer() {
        val positionComponent = engine.createComponent(PositionComponent::class.java)
        positionComponent.x = (WORLD_WIDTH - PLAYER_SIZE) / 2f
        positionComponent.y = (1 - PLAYER_SIZE) / 2f
        val boundComponent = engine.createComponent(BoundsComponent::class.java)
        boundComponent.bounds.set(positionComponent.x, positionComponent.y, PLAYER_BOUNDS_RADIUS)

    }

    fun addBackground() {

    }
}