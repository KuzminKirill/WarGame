package com.wardemo.game.core


import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import javax.xml.soap.Text

class Hero(x: Float, y: Float) {

    val EPS = 0f
    val GRAVITY = -15f
    val BORDER = 0f
    var rect : Rectangle
    var position : Vector3
    var velocity : Vector3
    //var hero : Texture
    val hero : HeroAnimation
    var texture : Texture


    fun update(dt : Float) {
        hero.update(dt)
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
            velocity.y = 250f
    }

    fun move_left() {
        velocity.x = -50f
    }

    fun move_right() {
        velocity.x = 50f
    }

    init {
        rect = Rectangle(x,y,64f,75f)
        position = Vector3(x,y,0f)
        velocity = Vector3(0f,0f,0f)
        //hero = Texture("hero.png")
        texture = Texture("hero_run.png")
        hero = HeroAnimation(TextureRegion(texture), 6, 1f)
    }

    fun free() {
        texture.dispose()
    }

    fun getHero(): TextureRegion {
        return hero.getFrame()
    }
}
