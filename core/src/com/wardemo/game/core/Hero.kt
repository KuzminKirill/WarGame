package com.wardemo.game.core

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector3

class Hero(x: Float, y: Float) {

    val GRAVITY = -15f

    var position : Vector3
    var velocity : Vector3
    var hero : Texture


    fun update(dt : Float){
        velocity.add(0f,GRAVITY,0f)
        velocity.scl(dt)
        position.add(0f,velocity.y,0f)
        velocity.scl(1/dt)


    }

    init {
        position = Vector3(x,y,0f)
        velocity = Vector3(0f,0f,0f)
        hero = Texture("hero.jpg")
    }
}