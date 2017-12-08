package com.wardemo.game.core

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.utils.Disposable


class Player(b: Body) : Disposable{
    override fun dispose() {

    }

    var playerPhysicsFixture : Fixture
    var playerSensorFixture : Fixture
    var box: Body = b
    val position : Vector2
        get() = box.position
    var velocity = Vector2()
        internal set
    internal var isJump = false

    init {
        val poly = PolygonShape()
        poly.setAsBox(0.4f, 0.4f)
        playerPhysicsFixture = box.createFixture(poly, 3f)
        poly.dispose()
        val circle = CircleShape()
        circle.radius = 0.41f
        circle.position = Vector2(0f, 0f)
        playerSensorFixture = box.createFixture(circle, 1f)
        setFriction(10f)
        circle.dispose()
        box.isBullet = true

    }

    fun getBody() : Body {
        return box
    }

    // установка силы трения
    fun setFriction(f: Float) {
        playerSensorFixture.friction = f
        playerPhysicsFixture.friction = f
    }
    fun update(delta: Float) {
        val vel = box.getLinearVelocity()
        velocity.y = vel.y
        box.linearVelocity = velocity
        if (isJump) {
            box.applyLinearImpulse(0f, 8f,
                    box.position.x, box.position.y,true)
            isJump = false
        }
    }

    fun jump() {
        isJump = true
    }

    fun resetVelocity() {
        velocity.x = 0f
        velocity.y = 0f
    }

    companion object {
        internal val MAX_VELOCITY = 3f
        val SPEED = 5f
        val SIZE = 0.8f
    }
}