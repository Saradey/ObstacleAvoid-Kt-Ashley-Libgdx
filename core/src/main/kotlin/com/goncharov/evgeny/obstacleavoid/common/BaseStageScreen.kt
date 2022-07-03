package com.goncharov.evgeny.obstacleavoid.common

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.FitViewport
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.FONT_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.consts.DEBUG
import com.goncharov.evgeny.obstacleavoid.consts.UI_HEIGHT
import com.goncharov.evgeny.obstacleavoid.consts.UI_WIDTH
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation
import com.goncharov.evgeny.obstacleavoid.systems.debug.FpsMonitorSystem
import com.goncharov.evgeny.obstacleavoid.util.GdxUtils

abstract class BaseStageScreen(
    protected val navigation: Navigation,
    protected val assetManager: AssetManager,
    private val batch: SpriteBatch
) : BaseScreen() {

    private val camera = OrthographicCamera()
    private val viewport = FitViewport(UI_WIDTH, UI_HEIGHT, camera)
    private val stage = Stage(viewport, batch)
    protected val uiSkin: Skin = assetManager[AssetDescriptors.UI_SKIN_DESCRIPTOR]
    private val engine = Engine()

    override fun show() {
        debug("show")
        val inputMultiplexer = InputMultiplexer()
        inputMultiplexer.addProcessor(stage)
        Gdx.input.inputProcessor = inputMultiplexer
        stage.addActor(initUi())
        if (DEBUG) {
            engine.addSystem(FpsMonitorSystem(batch, assetManager[FONT_DESCRIPTOR], viewport))
            stage.isDebugAll = true
        }
    }

    override fun resize(width: Int, height: Int) {
        debug("resize")
        viewport.update(width, height, true)
    }

    override fun render(delta: Float) {
        GdxUtils.clearScreen()
        stage.act()
        stage.draw()
        engine.update(delta)
    }

    override fun hide() {
        debug("hide")
        dispose()
    }

    override fun dispose() {
        debug("dispose")
        stage.dispose()
        Gdx.input.inputProcessor = null
    }

    protected abstract fun initUi(): Actor
}