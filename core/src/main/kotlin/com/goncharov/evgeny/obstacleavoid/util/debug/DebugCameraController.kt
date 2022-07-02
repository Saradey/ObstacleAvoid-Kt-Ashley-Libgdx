package com.goncharov.evgeny.obstacleavoid.util.debug

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.goncharov.evgeny.obstacleavoid.consts.UI_HEIGHT
import com.goncharov.evgeny.obstacleavoid.consts.UI_WIDTH
import com.goncharov.evgeny.obstacleavoid.consts.WORLD_HEIGHT
import com.goncharov.evgeny.obstacleavoid.consts.WORLD_WIDTH

object DebugCameraController {

    fun updateCameraUi(camera: OrthographicCamera) {
        when {
            Gdx.input.isKeyPressed(Input.Keys.LEFT) -> camera.position.x -= TRANSFORM_POSITION_SPEED_UI_CAMERA
            Gdx.input.isKeyPressed(Input.Keys.RIGHT) -> camera.position.x += TRANSFORM_POSITION_SPEED_UI_CAMERA
            Gdx.input.isKeyPressed(Input.Keys.UP) -> camera.position.y += TRANSFORM_POSITION_SPEED_UI_CAMERA
            Gdx.input.isKeyPressed(Input.Keys.DOWN) -> camera.position.y -= TRANSFORM_POSITION_SPEED_UI_CAMERA
            Gdx.input.isKeyPressed(Input.Keys.R) -> {
                camera.position.x = UI_WIDTH / 2f
                camera.position.y = UI_HEIGHT / 2f
                camera.zoom = 1f
            }
        }
        zoomControlling(camera)
    }

    fun updateCamera(camera: OrthographicCamera) {
        when {
            Gdx.input.isKeyPressed(Input.Keys.LEFT) -> camera.position.x -= TRANSFORM_POSITION_SPEED_CAMERA
            Gdx.input.isKeyPressed(Input.Keys.RIGHT) -> camera.position.x += TRANSFORM_POSITION_SPEED_CAMERA
            Gdx.input.isKeyPressed(Input.Keys.UP) -> camera.position.y += TRANSFORM_POSITION_SPEED_CAMERA
            Gdx.input.isKeyPressed(Input.Keys.DOWN) -> camera.position.y -= TRANSFORM_POSITION_SPEED_CAMERA
            Gdx.input.isKeyPressed(Input.Keys.R) -> {
                camera.position.x = WORLD_WIDTH / 2f
                camera.position.y = WORLD_HEIGHT / 2f
                camera.zoom = 1f
            }
        }
        zoomControlling(camera)
    }

    private fun zoomControlling(camera: OrthographicCamera) {
        when {
            Gdx.input.isKeyPressed(Input.Keys.Z) -> camera.zoom += ZOOM_SPEED
            Gdx.input.isKeyPressed(Input.Keys.X) -> camera.zoom -= ZOOM_SPEED
        }
    }

    private const val TRANSFORM_POSITION_SPEED_UI_CAMERA = 1f
    private const val TRANSFORM_POSITION_SPEED_CAMERA = 0.1f
    private const val ZOOM_SPEED = 0.01f
}