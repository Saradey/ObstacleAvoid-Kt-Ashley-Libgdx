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
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.HIT_SOUND_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation
import com.goncharov.evgeny.obstacleavoid.util.FpsMonitorManager
import com.goncharov.evgeny.obstacleavoid.util.GdxUtils

class GameScreen(
    private val assetManager: AssetManager,
    private val shapeRenderer: ShapeRenderer,
    private val batch: SpriteBatch,
    private val navigation: Navigation,
    private val fpsMonitorManager: FpsMonitorManager
) : BaseScreen() {

    private val gameCamera = OrthographicCamera()
    private val gameViewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT, gameCamera)
    private val uiViewport = FitViewport(UI_WIDTH, UI_HEIGHT)

    private val engine = PooledEngine()

    private val entityFactory = EntityFactory(engine, assetManager)

    private val sound = assetManager[HIT_SOUND_DESCRIPTOR]

    private var reset = false

//    private val gameController = GameController(assetManager, navigation, fpsMonitorManager)
//    private val gameRender = GameRender(
//        shapeRenderer, assetManager, gameController, batch
//    )

    override fun show() {
        debug("show")
//        Gdx.input.inputProcessor = gameController
    }

    override fun render(delta: Float) {
        GdxUtils.clearScreen()
//        gameController.update(delta)
//        gameRender.render()
    }

    override fun resize(width: Int, height: Int) {
        debug("resize")
//        gameRender.resize(width, height)
    }

    override fun dispose() {
        Gdx.input.inputProcessor = null
    }

    companion object {
        private const val DEBUG = false
    }
}