package com.wardemo.game.core

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Hero(x: Float, y: Float, runTexture : String, idleTexture : String) {

    val EPS = 0f
    val GRAVITY = -15f

    var rect : Rectangle
    var position : Vector3 = Vector3(x,y,0f)
    var velocity : Vector3 = Vector3(0f,0f,0f)
    val heroIdleTexture : Texture = Texture(idleTexture)
    val heroRunTexture : Texture = Texture(runTexture)
    private val heroRun : HeroAnimation
    private val heroIdle : HeroAnimation

    fun update(dt : Float) {
        heroRun.update(dt)
        if (position.y > EPS) velocity.add(0f,GRAVITY,0f)
        velocity.scl(dt)
        position.add(velocity.x,velocity.y,0f)
        velocity.scl(1/dt)
        if (position.y < EPS) position.y = 0f
        rect.x=position.x
        rect.y=position.y
    }

    fun jump() {
        if (position.y == 0f)
            velocity.y = 450f
    }

    fun moveLeft() {
        velocity.x = -200f
    }

    fun moveRight() {
        velocity.x = 200f
    }

    init {
        heroRun = HeroAnimation(TextureRegion(heroRunTexture), 6, 1f)
        heroIdle = HeroAnimation(TextureRegion(heroRunTexture), 6, 1f)
        rect = Rectangle(x,y,heroRunTexture.width/6f,heroRunTexture.height.toFloat())
    }

    fun free() {
        heroRunTexture.dispose()
        heroIdleTexture.dispose()
    }

    fun getHeroRun(): TextureRegion {
        return heroRun.getFrame()
    }

    fun getHeroIdle() : TextureRegion {
        return heroIdle.getFrame()
    }
}
