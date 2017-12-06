package com.wardemo.game.core

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Item(x : Float, y : Float, itemView:String) {

    var isDraw : Boolean = true
    val EPS = 0f
    val GRAVITY = -15f
    var rect : Rectangle = Rectangle(x,y,32f,32f)
    var position : Vector3 = Vector3(x,y,0f)
    var velocity : Vector3 = Vector3(0f,0f,0f)
    var view: Texture = Texture(itemView)

    fun update(dt : Float) {
        if (position.y > EPS) velocity.add(0f, GRAVITY, 0f)
        velocity.scl(dt)
        position.add(0f, velocity.y, 0f)
        velocity.scl(1 / dt)
        if (position.y < EPS) position.y = 0f
        rect.x=position.x
        rect.y=position.y
    }

    fun free() {
        view.dispose()
    }
}