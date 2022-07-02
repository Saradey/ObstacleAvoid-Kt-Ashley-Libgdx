package com.goncharov.evgeny.obstacleavoid.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.goncharov.evgeny.obstacleavoid.common.BaseScreen
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation
import com.goncharov.evgeny.obstacleavoid.util.FpsMonitorManager
import com.goncharov.evgeny.obstacleavoid.util.GdxUtils

class GameScreen(
    assetManager: AssetManager,
    shapeRenderer: ShapeRenderer,
    batch: SpriteBatch,
    navigation: Navigation,
    fpsMonitorManager: FpsMonitorManager
) : BaseScreen() {

    private val gameController = GameController(assetManager, navigation, fpsMonitorManager)
    private val gameRender = GameRender(
        shapeRenderer, assetManager, gameController, batch
    )

    override fun show() {
        debug("show")
        Gdx.input.inputProcessor = gameController
    }

    override fun render(delta: Float) {
        GdxUtils.clearScreen()
        gameController.update(delta)
        gameRender.render()
    }

    override fun resize(width: Int, height: Int) {
        debug("resize")
        gameRender.resize(width, height)
    }

    override fun dispose() {
        Gdx.input.inputProcessor = null
    }
}