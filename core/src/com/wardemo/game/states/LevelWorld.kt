package com.wardemo.game.states


import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Disposable
import com.wardemo.game.core.*
import com.badlogic.gdx.physics.box2d.PolygonShape



//GameWorld
class LevelWorld : Disposable {
    override fun dispose() {
        world.dispose()
    }


    var world: World = World(Vector2(0f, -2000f), true)
    lateinit var items: MutableList<Item>
    lateinit var ground: MutableList<Ground>
    lateinit var hero: Hero



    init {
        createWorld()
    }

    //test world
    private fun createWorld() {
        val def = BodyDef()
        def.type = BodyDef.BodyType.DynamicBody
        val boxP = world.createBody(def)
        hero = Hero(boxP)

        hero.getBody().setTransform(50f, 500f, 0f)
        hero.getBody().isFixedRotation = true
        items = ArrayList()
        ground = ArrayList()
        ground.add(Ground(0f, 0f))
        ground.add(Ground(100f, 0f))
        ground.add(Ground(200f, 0f))
        ground.add(Ground(300f, 0f))
        ground.add(Ground(400f, 0f))
        ground.add(Ground(500f, 0f))
        ground.add(Ground(600f, 0f))



        //    score = 0
        //    lastDropTime = 0
        //    coinsCount = 10

        //создание платформы

        //создание блоков
        var boxGround = createBox(BodyDef.BodyType.StaticBody, 960f, 5f, 2f)
        boxGround.setTransform(960f, 0f, 0f)
        boxGround.fixtureList.get(0).userData = "bd"
        var boxleftWall = createBox(BodyDef.BodyType.StaticBody, 5f, 540f, 2f)
        boxleftWall.setTransform(-5f, 540f, 0f)
        boxleftWall.fixtureList.get(0).userData = "bd"
        var boxRightWall = createBox(BodyDef.BodyType.StaticBody, 5f, 540f, 2f)
        boxRightWall.setTransform(1920f, 540f, 0f)
        boxRightWall.fixtureList.get(0).userData = "bd"
        var boxRoof = createBox(BodyDef.BodyType.StaticBody, 960f, 5f, 2f)
        boxRoof.setTransform(960f, 1080f, 0f)
        boxRoof.fixtureList.get(0).userData = "bd"

        var platform = createBox(BodyDef.BodyType.StaticBody, 250f, 50f, 2f)
        platform.setTransform(1000f, 500f, 0f)
        platform.fixtureList.get(0).userData = "bd"




        /*    for (i in 0 until 1900 step 100) {
                var boxGround = createBox(BodyDef.BodyType.StaticBody, 25f, 25f, 2f)
                boxGround.setTransform(i.toFloat(), 200f, 0f)
                boxGround.fixtureList.get(0).userData = "bd"*/
         //   boxGround = createBox(BodyDef.BodyType.StaticBody, 25f, 25f, 0f)
         //   boxGround.setTransform(i.toFloat(),  0f, 0f)
         //   boxGround.fixtureList.get(0).userData = "b"
       // }


    }

    private fun createBox(type: BodyDef.BodyType, width: Float, height: Float, density: Float): Body {
        val def = BodyDef()
        def.type = type
        val box = world.createBody(def)
        val poly = PolygonShape()
        poly.setAsBox(width, height)
        box.createFixture(poly, density)
        poly.dispose()
        return box
    }



}
















/*
    override fun handleInput() {
        if (Gdx.input.isTouched) {
            /*
            if (jump.isTouched(Gdx.input.getX(0).toFloat(),
                    1080 - Gdx.input.getY(0).toFloat()) ||
                    jump.isTouched(Gdx.input.getX(1).toFloat(),
                            1080 - Gdx.input.getY(1).toFloat())) {
                hero.jump()
            }   else hero.velocity.y = 0f




            if (left.isTouched(Gdx.input.getX(0).toFloat(),
                    1080 - Gdx.input.getY(0).toFloat()) ||
                    left.isTouched(Gdx.input.getX(1).toFloat(),
                            1080 - Gdx.input.getY(1).toFloat())) {
                hero.moveLeft()
            }

            if (right.isTouched(Gdx.input.getX(0).toFloat(),
                    1080 - Gdx.input.getY(0).toFloat()) ||
                    right.isTouched(Gdx.input.getX(1).toFloat(),
                            1080 - Gdx.input.getY(1).toFloat())) {
                hero.moveRight()
            }*/
            if (jump.isTouched(Gdx.input.x.toFloat(), 1080 - Gdx.input.y.toFloat())) {
                hero.jump()
            }
            if (left.isTouched(Gdx.input.x.toFloat(), 1080 - Gdx.input.y.toFloat())) {
                hero.moveLeft()
            }
            if (right.isTouched(Gdx.input.x.toFloat(), 1080 - Gdx.input.y.toFloat())) {
                hero.moveRight()
            }
        } else hero.cancelMove()
    }

    override fun update(dt: Float) {
        handleInput()
        hero.update(dt)
        for (item: Item in items) item.update(dt)
    }



    override fun render(sb: SpriteBatch) {
        sb.begin()
        sb.draw(back, 0f, 0f,1920f,1080f)
        for (land : Ground in ground)
            sb.draw(land.getGrTexture(), land.getPosGrX(), land.getPosGrY())
        sb.draw(if (Gdx.input.isTouched) hero.getHeroRun()
                else hero.getHeroIdle(), hero.position.x, hero.position.y)
        sb.draw(left.button, left.x, left.y)
        sb.draw(right.button, right.x, right.y)
        sb.draw(jump.button, jump.x, jump.y)
        scoreHint.draw(sb, "SCORE: $score items: $coinsCount", 0f,1080f)

        for (item: Item in items) {
            if (item.isDraw) {
                sb.draw(item.view, item.position.x, item.position.y)
                for (land : Ground in ground) {
                    if (item.rect.overlaps(land.rec)) {
                        item.velocity.y = 0f
                        item.position.y = land.getPosGrY()+land.gr.height
                    }
                    if (hero.rec.overlaps(land.rec)) {
                        when{
                            overlapsUp(hero.center,land.center) -> {
                                hero.position.y = land.rec.y + land.rec.height
                            }
                            overlapsRight(hero.center,land.center) -> {
                                hero.position.x = land.rec.x - hero.rec.width
                                hero.cancelMove()
                            }
                            overlapsDown(hero.center,land.center) -> {
                                hero.position.y = land.rec.y - land.rec.height
                            }
                            overlapsLeft(hero.center,land.center) -> {
                                hero.position.x = land.rec.x + hero.rec.width
                                hero.cancelMove()
                            }
                        }
                        hero.cancelMove()
                    }
                }
                if (item.rect.overlaps(hero.rec)) {
                    score++
                    item.isDraw = false
                }
            }
        }
        sb.end()

        //generate new item
        if (TimeUtils.nanoTime() - lastDropTime > 1*Math.pow(10.0,9.0)) { // time's up?
            if (coinsCount > 0) { //items remains?
                generateCoin()
                coinsCount--
            }
        }
    }



    fun overlapsUp(obj: Vector2, upObj : Vector2) : Boolean {
        return (upObj.y - obj.y >= 0.001f &&
                (upObj.x - obj.x >= 0.001f || obj.x - upObj.x >= 0.001f))
    }

    fun overlapsDown(obj: Vector2, downObj : Vector2) : Boolean {
        return (obj.y - downObj.y >= 0.001f &&
                (downObj.x - obj.x >= 0.001f || obj.x - downObj.x >= 0.001f))
    }

    fun overlapsLeft(obj : Vector2, leftObj : Vector2) : Boolean {
        return (obj.x - leftObj.x >= 0.001f &&
                (leftObj.y - obj.y >= 0.001f || obj.y - leftObj.y >= 0.001f))
    }

    fun overlapsRight(obj : Vector2, rightObj : Vector2) : Boolean {
        return (rightObj.x - obj.x >= 0.001f &&
                (rightObj.y - obj.y >= 0.001f || obj.x - rightObj.x >= 0.001f))
    }
}*/