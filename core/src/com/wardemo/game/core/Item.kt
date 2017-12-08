package com.wardemo.game.core

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

class Item(pos : Vector2) {

    var isDraw : Boolean = true
    val EPS = 0f
    val GRAVITY = -15f
    var rect : Rectangle = Rectangle(pos.x,pos.y,32f,32f)
    var position : Vector2 = Vector2(pos.x, pos.y)
    var velocity : Vector2 = Vector2(0f,0f)

    fun update(dt : Float) {
        if (position.y > EPS) velocity.add(0f, GRAVITY)
        velocity.scl(dt)
        position.add(0f, velocity.y)
        velocity.scl(1 / dt)
        if (position.y < EPS) position.y = 0f
        rect.x=position.x
        rect.y=position.y
    }

    fun free() {
    }
}