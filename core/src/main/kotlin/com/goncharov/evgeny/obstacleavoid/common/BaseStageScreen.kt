package com.goncharov.evgeny.obstacleavoid.common

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
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
import com.goncharov.evgeny.obstacleavoid.consts.UI_HEIGHT
import com.goncharov.evgeny.obstacleavoid.consts.UI_WIDTH
import com.goncharov.evgeny.obstacleavoid.consts.gameManagerFamily
import com.goncharov.evgeny.obstacleavoid.managers.SavedManagers
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation
import com.goncharov.evgeny.obstacleavoid.systems.debug.DebugUiCameraSystem
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
    protected val engine = Engine()
    private val factory = EntityFactory(engine, assetManager)

    override fun show() {
        debug("show")
        factory.addGameManager()
        val multiplexer = InputMultiplexer()
        multiplexer.addProcessor(stage)
        multiplexer.addProcessor(this)
        Gdx.input.inputProcessor = multiplexer
        stage.addActor(initUi())
        engine.addSystem(FpsMonitorSystem(batch, assetManager[FONT_DESCRIPTOR], viewport))
        engine.addSystem(DebugUiCameraSystem(camera))
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

    override fun keyDown(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.C -> stage.isDebugAll = !stage.isDebugAll
            Input.Keys.B -> {
                val debug = Mappers.debug[engine.getEntitiesFor(gameManagerFamily).first()]
                debug.renderFps = !debug.renderFps
            }
        }
        return true
    }

    protected abstract fun initUi(): Actor
}