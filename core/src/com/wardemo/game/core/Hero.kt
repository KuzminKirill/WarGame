package com.wardemo.game.core

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

class Hero(x: Float, y: Float, runTexture : String, idleTexture : String) {

    val EPS = 0f
    val GRAVITY = -15f
    var center : Vector2
    var rec: Rectangle
    var position : Vector2 = Vector2(x,y)
    var velocity : Vector2 = Vector2(0f,0f)
    val heroIdleTexture : Texture = Texture(idleTexture)
    val heroRunTexture : Texture = Texture(runTexture)
    private val heroRun : HeroAnimation
    private val heroIdle : HeroAnimation

    fun update(dt : Float) {
        heroRun.update(dt)
        heroIdle.update(dt)
        if (position.y > EPS) velocity.add(0f,GRAVITY)
        velocity.scl(dt)
        position.add(velocity.x,velocity.y)
        velocity.scl(1/dt)
        if (position.y < EPS) position.y = 0f
        rec.x=position.x
        rec.y=position.y
    }

    fun jump() {
        if (position.y == 0f)
            velocity.y = 800f
    }

    fun cancelJump() {
        velocity.y = 0f
    }

    fun cancelMove(){
        velocity.x = 0f
    }

    fun moveLeft() {
        velocity.x = -400f
    }

    fun moveRight() {
        velocity.x = 400f
    }

    init {
        heroRun = HeroAnimation(TextureRegion(heroRunTexture), 6, 1f)
        heroIdle = HeroAnimation(TextureRegion(heroIdleTexture), 6, 1f)
        rec = Rectangle(x,y,heroRunTexture.width/6f,heroRunTexture.height.toFloat())
        center = Vector2(x + (0.5 * rec.width).toFloat(),y + (0.5 * rec.height).toFloat())
    }


    fun getHeroRun(): TextureRegion {
        return heroRun.getFrame()
    }

    fun getHeroIdle() : TextureRegion {
        return heroIdle.getFrame()
    }

    fun free() {
        heroRunTexture.dispose()
        heroIdleTexture.dispose()
    }
}
