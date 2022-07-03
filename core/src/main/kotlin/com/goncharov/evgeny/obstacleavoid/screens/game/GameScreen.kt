package com.goncharov.evgeny.obstacleavoid.screens.game

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.goncharov.evgeny.obstacleavoid.common.BaseScreen
import com.goncharov.evgeny.obstacleavoid.common.EntityFactory
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.consts.*
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.FONT_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.HIT_SOUND_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.managers.GameManager
import com.goncharov.evgeny.obstacleavoid.navigation.KeyNavigation
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation
import com.goncharov.evgeny.obstacleavoid.systems.*
import com.goncharov.evgeny.obstacleavoid.systems.collision.CollisionListener
import com.goncharov.evgeny.obstacleavoid.systems.collision.CollisionSystem
import com.goncharov.evgeny.obstacleavoid.systems.debug.DebugGameCameraSystem
import com.goncharov.evgeny.obstacleavoid.systems.debug.DebugRenderSystem
import com.goncharov.evgeny.obstacleavoid.systems.debug.FpsMonitorSystem
import com.goncharov.evgeny.obstacleavoid.systems.debug.GridRenderSystem
import com.goncharov.evgeny.obstacleavoid.util.GdxUtils

class GameScreen(
    assetManager: AssetManager,
    shapeRenderer: ShapeRenderer,
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

    private val font = assetManager[FONT_DESCRIPTOR]

    private val gridRenderSystem = GridRenderSystem(gameViewport, shapeRenderer)
    private val fpsMonitorSystem = FpsMonitorSystem(batch, font, uiViewport)
    private val debugRenderSystem = DebugRenderSystem(gameViewport, shapeRenderer)

    override fun show() {
        debug("show")
        addEntities()
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
        engine.addSystem(DebugGameCameraSystem(gameCamera))
        Gdx.input.inputProcessor = this
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

    override fun keyDown(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.C -> {
                if (engine.systems.contains(gridRenderSystem, true)) {
                    engine.removeSystem(gridRenderSystem)
                    engine.removeSystem(debugRenderSystem)
                } else {
                    engine.addSystem(gridRenderSystem)
                    engine.addSystem(debugRenderSystem)
                }
            }
            Input.Keys.B -> {
                if (engine.systems.contains(gridRenderSystem, true)) {
                    engine.removeSystem(fpsMonitorSystem)
                } else {
                    engine.addSystem(fpsMonitorSystem)
                }
            }
            Input.Keys.SPACE -> {
                val entity = engine.getEntitiesFor(gameManagerFamily).first()
                val gameComponent = Mappers.game[entity]
                gameComponent.gameIsPause = !gameComponent.gameIsPause
            }
        }
        return true
    }

    private fun addEntities() {
        factory.addBackground()
        factory.addPlayer()
        factory.addGameManager()
    }
}