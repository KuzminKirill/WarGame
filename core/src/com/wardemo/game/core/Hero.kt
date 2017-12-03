package com.wardemo.game.core

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector3

class Hero(x: Float, y: Float) {

    val EPS = 0f
    val GRAVITY = -15f
    val BORDER = 0f

    var position : Vector3
    var velocity : Vector3
    var hero : Texture


    fun update(dt : Float) {
        if (position.y > EPS) velocity.add(0f,GRAVITY,0f)
        velocity.scl(dt)
        position.add(velocity.x,velocity.y,0f)
        velocity.scl(1/dt)
        if (position.y < EPS) position.y = 0f
    }

    fun jump() {
        if (position.y == 0f)
            velocity.y = 250f
    }

    fun move_left() {
        velocity.x = -50f
    }

    fun move_right() {
        velocity.x = 50f
    }

    init {
        position = Vector3(x,y,0f)
        velocity = Vector3(0f,0f,0f)
        hero = Texture("hero.png")
    }

    fun free() {
        hero.dispose()
    }
}
