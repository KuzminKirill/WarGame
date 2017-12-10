package com.wardemo.game.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.wardemo.game.states.LevelWorld


class WorldRenderer(lolWorld: LevelWorld, lolcontrol : Control) {
    var control : Control = lolcontrol
    var world : LevelWorld = lolWorld
    var renderer : Box2DDebugRenderer = Box2DDebugRenderer()
    var cam: OrthographicCamera = OrthographicCamera(1920f,1080f)
    var ground : Texture
    var player : Texture
    private var left : Texture
    private var right : Texture
    private var jump : Texture
    private var background : Texture
    private var sb : SpriteBatch
    private var hint : BitmapFont

    init {
        SetCamera(1920f / 2,1080f / 2 )
        sb = SpriteBatch()
        ground = Texture("Dirt.png")
        player = Texture("Barrel.png")
        left = Texture("LeftBtn.png")
        right = Texture("RightBtn.png")
        jump = Texture("JumpBtn.png")
        background = Texture("LevelBackground.jpg")
        hint = BitmapFont()
    }

    fun drawPlayer() {
        sb.draw(player, world.hero.position.x, world.hero.position.y)
    }

    private fun drawButtons() {
        hint.draw(sb,"left: ${control.x}\nright: ${control.y}\njump: ${control.z}",450f,450f)
        sb.draw(control.leftBtn.button, control.leftBtn.x, control.leftBtn.y)
        sb.draw(control.rightBtn.button, control.rightBtn.x, control.rightBtn.y)
        sb.draw(control.jumpBtn.button, control.jumpBtn.x, control.jumpBtn.y)
    }

    fun drawBackground() {
        sb.draw(background,0f,0f,1920f,1080f)
    }


    fun drawGround() {
        for (brick : Ground in world.ground)
            sb.draw(ground, brick.posgr.x, brick.posgr.y)
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
        drawBackground()
    //    drawPlayer()
        drawButtons()
    //    drawGround()
        sb.end()
        renderer.render(world.world, cam.combined)
        world.world.step(delta, 4, 4)
    }
}