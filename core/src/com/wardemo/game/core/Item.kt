package com.wardemo.game.core

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

class Item(x : Float, y : Float, itemView:String) {

    var isDraw : Boolean = true
    val EPS = 0f
    val GRAVITY = -15f
    var rect : Rectangle = Rectangle(x,y,32f,32f)
    var position : Vector2 = Vector2(x,y)
    var velocity : Vector2 = Vector2(0f,0f)
    var view: Texture = Texture(itemView)

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
        view.dispose()
    }
}