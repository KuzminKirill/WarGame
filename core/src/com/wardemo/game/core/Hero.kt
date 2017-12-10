package com.wardemo.game.core

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.Fixture

class Hero(box : Body) {
    var playerPhysicsFixture: Fixture
    var playerSensorFixture: Fixture
    var box: Body = box
    private var isJump = false

    val position: Vector2
        get() = box.position
    var velocity: Vector2 = Vector2(0f, 0f)

    init {
        val poly = PolygonShape()
        poly.setAsBox(50f, 50f)
        playerPhysicsFixture = box.createFixture(poly, 3f)
        val circle = CircleShape()
        circle.radius = 45f
        circle.position = Vector2(0f, 0f)
        playerSensorFixture = box.createFixture(circle, 1f)
        setFriction(10f)
        circle.dispose()
        box.isBullet = true
    }

    fun update(delta: Float) {
        val vel = box.linearVelocity
        //velocity.y = vel.y
        box.linearVelocity = velocity
        if (isJump) {
            box.applyLinearImpulse(0f, -40f, box.position.x, box.position.y, true)
            isJump = false
        }

    }


    // set friction force
    fun setFriction(f: Float) {
        playerPhysicsFixture.friction = f
        playerSensorFixture.friction = f
    }

    fun jump() {
        isJump = true
    }

    fun resetVelocity() {
        velocity.x = 0f
        velocity.y = 0f
    }

    fun getBody(): Body {
        return box
    }

    companion object {
        internal val MAX_VELOCITY = 40f
        val SPEED = 1600f
        val SIZE = 90f
    }


}

/*
    init {
        heroRun = HeroAnimation(TextureRegion(heroRunTexture), 6, 1f)
        heroIdle = HeroAnimation(TextureRegion(heroIdleTexture), 6, 1f)
        rec = Rectangle(x,y,heroRunTexture.width/6f,heroRunTexture.height.toFloat())
        center = Vector2(x + (0.5 * rec.width).toFloat(),y + (0.5 * rec.height).toFloat())
    }
   fun update(dt: Float) {
        heroRun.update(dt)
        heroIdle.update(dt)
        if (position.y > EPS) velocity.add(0f, GRAVITY)
        velocity.scl(dt)
        position.add(velocity.x, velocity.y)
        velocity.scl(1 / dt)
        if (position.y < EPS) position.y = 0f
        rec.x = position.x
        rec.y = position.y
    }

    fun jump() {
        if (position.y==0f)
            velocity.y = 800f
    }

    fun cancelJump() {
        velocity.y = 0f
    }

    fun cancelMove() {
        velocity.x = 0f
    }

    fun moveLeft() {
        velocity.x = -400f
    }

    fun moveRight() {
        velocity.x = 400f
    }


    fun getHeroRun(): TextureRegion {
        return heroRun.getFrame()
    }

    fun getHeroIdle(): TextureRegion {
        return heroIdle.getFrame()
    }

    fun free() {
        heroRunTexture.dispose()
        heroIdleTexture.dispose()
    }*/
