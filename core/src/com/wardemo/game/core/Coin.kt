package com.wardemo.game.core

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import kotlin.concurrent.fixedRateTimer

class Coin(x : Float, y : Float) {


    var isDraw : Boolean
    val EPS = 0f
    val GRAVITY = -15f
    var rect : Rectangle
    var position : Vector3
    var velocity : Vector3
    var coin : Texture

    fun update(dt : Float) {
        if (position.y > EPS) velocity.add(0f, GRAVITY, 0f)
        velocity.scl(dt)
        position.add(0f, velocity.y, 0f)
        velocity.scl(1 / dt)
        if (position.y < EPS) position.y = 0f
        rect.x=position.x
        rect.y=position.y
    }


    init {
        rect = Rectangle(x,y,32f,32f)
        position = Vector3(x,y,0f)
        velocity = Vector3(0f,0f,0f)
        coin = Texture("coin.png")
        isDraw = true
    }

    fun free() {
        coin.dispose()
    }

}