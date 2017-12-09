package com.wardemo.game.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.utils.TimeUtils
import com.wardemo.game.states.LevelWorld
import com.wardemo.game.states.customButton


class WorldController(world: LevelWorld) {

    private var keyLeft = false
    private var keyRight = false
    private var keyJump = false

    var player : Hero = world.hero
    var _world : LevelWorld = world

    var leftBtn : customButton = customButton(0f,0f, "LeftBtn.png")
    var rightBtn : customButton = customButton(250f,0f,"RightBtn.png")
    var jumpBtn : customButton = customButton(1700f, 0f, "JumpBtn.png")

    private var score : Int = 0
    private var lastDropTime : Long = 0
    private var coinsCount : Int = 10
    var grounded: Boolean = false


    private fun generateCoin() {
        val coin = Item(Vector2(MathUtils.random(20f, 1700f), MathUtils.random(0f, 1080f)))
        _world.items.add(coin)
        lastDropTime = TimeUtils.nanoTime()
    }


    fun update(delta: Float) {
        val platforms = _world.items
        //обновление состояния платформ
        for (item : Item in platforms) {
            item.update(Math.max(1 / 60.0f, delta))
        }
        grounded = isPlayerGrounded(Gdx.graphics.deltaTime)
        //обработка нажатий
        processInput()

        //обработка состояния игрока
        _world.hero.update(delta)



        processInput()
        _world.hero.update(delta)
        for (item: Item in _world.items) item.update(delta)
    }

    //move left
    fun leftPressed() {
        keyLeft = true
        //keys.get(keys.put(Keys.LEFT, true))
    }

    //move right
    fun rightPressed() {
        keyRight = true
        //keys.get(keys.put(Keys.RIGHT, true))
    }

    //move up
    fun upPressed() {
        keyJump = true
    }

    fun releaseKeys() {
        keyLeft = false
        keyRight = false
        keyJump = false
    }

    private fun processInput() {

        val player = _world.hero

        when {
            keyLeft -> { player.velocity.x = -Player.SPEED }
            keyRight -> { player.velocity.x = Player.SPEED }
            keyJump -> {
                //если стоим на земле
                if (grounded)
                //прыгаем
                    player.jump()
                //если не на земле
                if (!grounded)
                //убираем трение
                    _world.hero.setFriction(0f)
                else {
                    if (Gdx.input.x < player.position.x || Gdx.input.x > player.position.x)
                        _world.hero.setFriction(0.2f)
                    else
                        _world.hero.setFriction(100f)
                }//иначе задаём трение
            }
        }
    }

    fun resetWay() {
        player.velocity.x = 0f
        player.velocity.y = 0f
    }


    private fun isPlayerGrounded(deltaTime: Float): Boolean {
       // _world.groundedPlatform = null
        val contactList = _world.world.contactList
        for (i : Contact in contactList) {
            if (i.isTouching && (i.getFixtureA()===_world.hero.playerSensorFixture ||
                                i.getFixtureB()===_world.hero.playerSensorFixture)) {
                val pos = _world.hero.position
                val manifold = i.worldManifold
                var below = true
                for (j in 0 until manifold.numberOfContactPoints) {
                    below = below and (manifold.points[j].y < pos.y - 0.4f)
                }
                if (below) {
                    if (i.fixtureA.userData!=null && i.fixtureA.userData=="p") {
                       // _world.groundedPlatform = i.getFixtureA().getBody().getUserData() as MovingPlatform
                    }
                    if (i.fixtureB.userData!=null && i.fixtureB.userData=="p") {
                      //  _world.groundedPlatform = i.getFixtureB().getBody().getUserData() as MovingPlatform
                    }
                    return true
                }

                return false
            }
        }
        return false
    }
}