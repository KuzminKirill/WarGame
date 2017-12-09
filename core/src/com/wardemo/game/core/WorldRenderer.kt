package com.wardemo.game.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.wardemo.game.states.LevelWorld


class WorldRenderer(lolWorld: LevelWorld,w: Float, h: Float, debug: Boolean) {
    var world : LevelWorld = lolWorld
    var renderer : Box2DDebugRenderer = Box2DDebugRenderer()
    var ppuX: Float = 0.toFloat()
    var ppuY: Float = 0.toFloat()
    var cam: OrthographicCamera
    var width : Int = 0
    var height : Int = 0
    var barrel : Texture
    var player : Texture
    var left : Texture
    var right : Texture
    var jump : Texture
    var background : Texture
    private var sb : SpriteBatch

    init {
        CAMERA_WIDTH = w
        CAMERA_HEIGHT = h
        ppuX = Gdx.graphics.width.toFloat() / CAMERA_WIDTH
        ppuY = Gdx.graphics.height.toFloat() / CAMERA_HEIGHT
        this.cam = OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT)
        SetCamera(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f)
        sb = SpriteBatch()
        barrel = Texture("Barrel.png")
        player = Texture("Dirt.png")
        left = Texture("LeftBtn.png")
        right = Texture("RightBtn.png")
        jump = Texture("JumpBtn.png")
        background = Texture("LevelBackground.jpg")
    }

    fun drawPlayer() {
        sb.draw(player, world.hero.position.x, world.hero.position.y)
    }

    private fun drawButtons() {
        sb.draw(left, 0f,0f)
        sb.draw(right, 250f,0f)
        sb.draw(jump, 1725f, 0f)
    }



    fun drawBrick() {
        for (brick : Item in world.items)
            sb.draw(barrel, brick.position.x, brick.position.y)
    }

    fun setSize(w: Int, h: Int) {
        this.width = w
        this.height = h
        ppuX = width / CAMERA_WIDTH
        ppuY = height / CAMERA_HEIGHT
    }


    fun SetCamera(x: Float, y: Float) {
        this.cam.position.set(x, y, 0f)
        this.cam.update()
    }

    fun dispose() {
        world.dispose()
    }

    fun render(delta: Float) {
        sb.begin()
        sb.draw(background,0f,0f,1920f,1080f)
        drawPlayer()
        drawButtons()
        drawBrick()
        sb.end()
        renderer.render(world.world, cam.combined)
        world.world.step(delta, 4, 4)
    }

    companion object {
        var CAMERA_WIDTH = 8f
        var CAMERA_HEIGHT = 5f
    }
}