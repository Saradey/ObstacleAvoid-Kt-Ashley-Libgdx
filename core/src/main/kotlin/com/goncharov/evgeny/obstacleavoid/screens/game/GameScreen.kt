package com.goncharov.evgeny.obstacleavoid.screens.game

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.goncharov.evgeny.obstacleavoid.common.BaseScreen
import com.goncharov.evgeny.obstacleavoid.common.EntityFactory
import com.goncharov.evgeny.obstacleavoid.consts.*
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.FONT_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.HIT_SOUND_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.managers.GameManager
import com.goncharov.evgeny.obstacleavoid.navigation.KeyNavigation
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation
import com.goncharov.evgeny.obstacleavoid.systems.*
import com.goncharov.evgeny.obstacleavoid.systems.collision.CollisionListener
import com.goncharov.evgeny.obstacleavoid.systems.collision.CollisionSystem
import com.goncharov.evgeny.obstacleavoid.systems.debug.DebugCameraSystem
import com.goncharov.evgeny.obstacleavoid.systems.debug.DebugRenderSystem
import com.goncharov.evgeny.obstacleavoid.systems.debug.FpsMonitorSystem
import com.goncharov.evgeny.obstacleavoid.systems.debug.GridRenderSystem
import com.goncharov.evgeny.obstacleavoid.util.GdxUtils

class GameScreen(
    private val assetManager: AssetManager,
    private val shapeRenderer: ShapeRenderer,
    private val batch: SpriteBatch,
    private val navigation: Navigation,
) : BaseScreen() {

    private val gameCamera = OrthographicCamera()
    private val gameViewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT, gameCamera)
    private val uiViewport = FitViewport(UI_WIDTH, UI_HEIGHT)

    private val engine = PooledEngine()

    private val factory = EntityFactory(engine, assetManager)

    private val hit = assetManager[HIT_SOUND_DESCRIPTOR]

    private var reset = false

    override fun show() {
        debug("show")
        val font = assetManager[FONT_DESCRIPTOR]
        val listener = object : CollisionListener {
            override fun hitObstacle() {
                GameManager.decrementLives()
                hit.play()
                if (GameManager.isGameOver()) {
                    GameManager.updateHighScore()
                } else {
                    engine.removeAllEntities()
                    reset = true
                }
            }
        }
        engine.addSystem(PlayerSystem())
        engine.addSystem(MovementSystem())
        engine.addSystem(WorldWrapSystem(gameViewport))
        engine.addSystem(BoundsSystem())
        engine.addSystem(ObstacleSpawnSystem(factory))
        engine.addSystem(CleanUpSystem())
        engine.addSystem(CollisionSystem(listener))
        engine.addSystem(ScoreSystem())
        engine.addSystem(RenderSystem(gameViewport, batch))
        engine.addSystem(UiRenderSystem(font, uiViewport, batch))
        if (DEBUG) {
            engine.addSystem(GridRenderSystem(gameViewport, shapeRenderer))
            engine.addSystem(DebugRenderSystem(gameViewport, shapeRenderer))
            engine.addSystem(DebugCameraSystem(gameCamera))
            engine.addSystem(FpsMonitorSystem(batch, font, uiViewport))
        }
        addEntities()
    }

    override fun render(delta: Float) {
        GdxUtils.clearScreen()
        engine.update(delta)
        if (GameManager.isGameOver()) {
            GameManager.reset()
            navigation.navigate(KeyNavigation.MenuKey)
        }
        if (reset) {
            reset = false
            addEntities()
        }
    }

    override fun resize(width: Int, height: Int) {
        debug("resize")
        gameViewport.update(width, height, true)
        uiViewport.update(width, height, true)
    }

    override fun dispose() {
        Gdx.input.inputProcessor = null
    }

    private fun addEntities() {
        factory.addBackground()
        factory.addPlayer()
    }
}