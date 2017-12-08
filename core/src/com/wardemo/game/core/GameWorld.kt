package com.wardemo.game.core

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Disposable

class GameWorld : Disposable{
    var world : World
    //массив блоков
    //получить массив блоков
    var bricks = Array<Brick>()
        internal set
    //наш персонаж
    //получить игрока
    lateinit var player: Player

    //ширина мира
    var width : Int = 0
    //высота мира
    var height : Int = 0

    init {
        width = 8
        height = 5
        world = World(Vector2(0f,-20f), true)
        createWorld()
    }

    //test world
    private fun createWorld() {
        val def = BodyDef()
        def.type = BodyDef.BodyType.DynamicBody
        val boxP = world.createBody(def)
        player = Player(boxP)

        player.getBody().setTransform(3.0f, 4.0f, 0f)
        player.getBody().isFixedRotation = true

        bricks.add(Brick(Vector2(0f, 0f))) // "Barrel.png"))
        bricks.add(Brick(Vector2(100f, 20f))) //, "barrel.png"))
        bricks.add(Brick(Vector2(200f, 40f))) //, "barrel.png"))
        bricks.add(Brick(Vector2(300f, 60f))) //, "barrel.png"))
        bricks.add(Brick(Vector2(400f, 80f))) //, "barrel.png"))
        bricks.add(Brick(Vector2(500f, 100f))) //, "barrel.png"))
        bricks.add(Brick(Vector2(600f, 120f))) //, "barrel.png"))
        bricks.add(Brick(Vector2(700f, 140f))) //, "barrel.png"))
    }

    override fun dispose() {
       world.dispose()
    }

}
