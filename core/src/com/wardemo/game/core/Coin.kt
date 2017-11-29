package com.wardemo.game.core

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector3

class Coin(x : Float, y : Float) {

    val EPS = 0f
    val GRAVITY = -15f

    var position : Vector3
    var velocity : Vector3
    var coin : Texture

    fun update(dt : Float) {
        if (position.y > EPS) velocity.add(0f, GRAVITY, 0f)
        velocity.scl(dt)
        position.add(0f, velocity.y, 0f)
        velocity.scl(1 / dt)
        if (position.y < EPS) position.y = 0f
    }


    init {
        position = Vector3(x,y,0f)
        velocity = Vector3(0f,0f,0f)
        coin = Texture("coin.png")
    }
}