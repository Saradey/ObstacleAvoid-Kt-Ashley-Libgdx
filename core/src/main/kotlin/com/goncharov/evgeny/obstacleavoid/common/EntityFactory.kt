package com.goncharov.evgeny.obstacleavoid.common

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.goncharov.evgeny.obstacleavoid.components.*
import com.goncharov.evgeny.obstacleavoid.consts.*
import com.goncharov.evgeny.obstacleavoid.managers.GameManager


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
        val movementComponent = engine.createComponent(MovementComponent::class.java)
        val playerComponent = engine.createComponent(PlayerComponent::class.java)
        val worldWrapComponent = engine.createComponent(WorldWrapComponent::class.java)
        val textureComponent = engine.createComponent(TextureComponent::class.java)
        textureComponent.textureRegion = gamePlayAtlas.findRegion(PLAYER)
        val dimensionComponent = engine.createComponent(DimensionComponent::class.java)
        dimensionComponent.width = PLAYER_SIZE
        dimensionComponent.height = PLAYER_SIZE
        val entity = Entity()
        entity.add(boundComponent)
        entity.add(movementComponent)
        entity.add(playerComponent)
        entity.add(positionComponent)
        entity.add(worldWrapComponent)
        entity.add(textureComponent)
        entity.add(dimensionComponent)
        engine.addEntity(entity)
    }

    fun addObstacle(x: Float, y: Float) {
        val bounds = engine.createComponent(BoundsComponent::class.java)
        bounds.bounds.set(x, y, OBSTACLE_BOUNDS_RADIUS)
        val movement = engine.createComponent(MovementComponent::class.java)
        movement.ySpeed = -GameManager.getDifficultyLevel().obstacleSpeed
        val position = engine.createComponent(PositionComponent::class.java)
        position.x = x
        position.y = y
        val cleanUp = engine.createComponent(CleanUpComponent::class.java)
        val obstacle = engine.createComponent(ObstacleComponent::class.java)
        val texture = engine.createComponent(TextureComponent::class.java)
        texture.textureRegion = gamePlayAtlas.findRegion(OBSTACLE)
        val dimension = engine.createComponent(DimensionComponent::class.java)
        dimension.width = OBSTACLE_SIZE
        dimension.height = OBSTACLE_SIZE
        val entity = engine.createEntity()
        entity.add(bounds)
        entity.add(movement)
        entity.add(position)
        entity.add(cleanUp)
        entity.add(obstacle)
        entity.add(texture)
        entity.add(dimension)
        engine.addEntity(entity)
    }

    fun addBackground() {
        val texture = engine.createComponent(TextureComponent::class.java)
        texture.textureRegion = gamePlayAtlas.findRegion(BACKGROUND)
        val position = engine.createComponent(PositionComponent::class.java)
        position.x = 0f
        position.y = 0f
        val dimension = engine.createComponent(DimensionComponent::class.java)
        dimension.width = WORLD_WIDTH
        dimension.height = WORLD_HEIGHT
        val entity = engine.createEntity()
        entity.add(texture)
        entity.add(position)
        entity.add(dimension)
        engine.addEntity(entity)
    }
}