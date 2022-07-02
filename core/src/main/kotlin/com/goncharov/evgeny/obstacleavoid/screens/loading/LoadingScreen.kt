package com.goncharov.evgeny.obstacleavoid.screens.loading

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.goncharov.evgeny.obstacleavoid.common.BaseScreen
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.FONT_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.GAME_PLAY_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.HIT_SOUND_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.UI_SKIN_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.consts.UI_HEIGHT
import com.goncharov.evgeny.obstacleavoid.consts.UI_WIDTH
import com.goncharov.evgeny.obstacleavoid.navigation.KeyNavigation
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation
import com.goncharov.evgeny.obstacleavoid.util.GdxUtils

class LoadingScreen(
    private val assetManager: AssetManager,
    private val shapeRenderer: ShapeRenderer,
    private val navigation: Navigation
) : BaseScreen() {

    private val camera = OrthographicCamera()
    private val viewport = FitViewport(UI_WIDTH, UI_HEIGHT, camera)
    private var progress = 0f
    private var waitTime = 0.75f
    private var changeScreen = false

    override fun show() {
        debug("show")
        assetManager.load(FONT_DESCRIPTOR)
        assetManager.load(GAME_PLAY_DESCRIPTOR)
        assetManager.load(UI_SKIN_DESCRIPTOR)
        assetManager.load(HIT_SOUND_DESCRIPTOR)
    }

    override fun render(delta: Float) {
        update(delta)
        GdxUtils.clearScreen()
        viewport.apply()
        shapeRenderer.projectionMatrix = camera.combined
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        draw()
        shapeRenderer.end()
        if (changeScreen) {
            navigation.navigate(KeyNavigation.MenuKey)
        }
    }

    private fun update(delta: Float) {
        progress = assetManager.progress
        if (assetManager.update()) {
            waitTime -= delta
            if (waitTime <= 0) {
                changeScreen = true
            }
        }
    }

    private fun draw() {
        shapeRenderer.rect(
            PROGRESS_BAR_X,
            PROGRESS_BAR_Y,
            progress * PROGRESS_BAR_WIDTH,
            PROGRESS_BAR_HEIGHT
        )
    }

    override fun resize(width: Int, height: Int) {
        debug("resize")
        viewport.update(width, height, true)
    }

    override fun hide() {
        debug("hide")
        dispose()
    }

    override fun dispose() {
        debug("dispose")
    }

    companion object {
        private const val PROGRESS_BAR_WIDTH = UI_WIDTH / 2f
        private const val PROGRESS_BAR_HEIGHT = 60f
        private const val PROGRESS_BAR_X = (UI_WIDTH - PROGRESS_BAR_WIDTH) / 2f
        private const val PROGRESS_BAR_Y = (UI_HEIGHT - PROGRESS_BAR_HEIGHT) / 2f
    }
}