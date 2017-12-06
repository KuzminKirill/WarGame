package com.wardemo.game.core

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Hero(x: Float, y: Float) {

    val EPS = 0f
    val GRAVITY = -20f
    var rect : Rectangle
    var position : Vector3
    var velocity : Vector3
    var hero : Texture


    fun update(dt : Float) {
        if (position.y > EPS) velocity.add(0f,GRAVITY,0f)
        velocity.scl(dt)
        position.add(velocity.x,velocity.y,0f)
        velocity.scl(1/dt)
        if (position.y < EPS) position.y = 0f
        if (position.x <0) position.x = 0f
        if (position.x >1800) position.x = 1800f
        rect.x=position.x
        rect.y=position.y

    }

    fun jump() {
        if (position.y == 0f)
            velocity.y = 350f
    }

    fun move_left() {
        velocity.x = -150f
    }

    fun move_right() {
        velocity.x = 150f
    }

    init {
        position = Vector3(x,y,0f)
        velocity = Vector3(0f,0f,0f)
        hero = Texture("hero.png")
        rect = Rectangle(x,y, hero.width.toFloat(),hero.height.toFloat())
    }

    fun free() {
        hero.dispose()
    }
}
