package com.wardemo.game.states


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.TimeUtils
import com.wardemo.game.core.Item
import com.wardemo.game.core.Hero
import com.wardemo.game.core.Ground

class LevelState(gsm :GameStateManager) : State(gsm) {
    val items: MutableList<Item>
   // val ground : MutableList<Ground> //ground array
    var hero : Hero

    //buttons
    private val left : customButton
    private val right : customButton
    private val jump : customButton

    private val back: Texture
    var ground1: Ground
    private val scoreHint: BitmapFont

    private var score : Int
    private var lastDropTime : Long
    private var coinsCount : Int

    init {
        items = ArrayList()

        hero = Hero(1700f,50f, "VikingRunSprite.png", "VikingIdleSprite.png")

        //buttons
        left = customButton(0f,0f, "LeftBtn.png")
        right = customButton(250f,0f,"RightBtn.png")
        jump = customButton(1700f, 0f, "JumpBtn.png")

        back = Texture("Sky.jpg")
        ground1 = Ground(0f,0f)
        scoreHint = BitmapFont()
        //camera.setToOrtho(false,WarDemo.WIDTH / 2f, WarDemo.HEIGHT / 2f)
        score = 0
        lastDropTime = 0
        coinsCount = 10
    }

    override fun handleInput() {
        if (Gdx.input.isTouched(0) || Gdx.input.isTouched(1)) {
            if (jump.isTouched(Gdx.input.x.toFloat(), 1080 - Gdx.input.y.toFloat())) {
                hero.jump()
            }

            if (left.isTouched(Gdx.input.x.toFloat(), 1080 - Gdx.input.y.toFloat())) {
                hero.moveLeft()
            }

            if (right.isTouched(Gdx.input.x.toFloat(), 1080 - Gdx.input.y.toFloat())) {
                hero.moveRight()
            }

        } else hero.velocity.x = 0f
    }

    override fun update(dt: Float) {
        handleInput()
        hero.update(dt)
        for (item: Item in items) item.update(dt)
    }

    private fun generateCoin() {
            val coin = Item(MathUtils.random(20f, 600f), MathUtils.random(200f, 400f),"Barrel.png")
            items.add(coin)
            lastDropTime = TimeUtils.nanoTime()
    }

    override fun render(sb: SpriteBatch) {
        sb.begin()
        sb.draw(back, 0f, 0f,1920f,1080f)
        sb.draw(ground1.getgr(), ground1.getposgrx(), ground1.getposgry())

        sb.draw(if (Gdx.input.isTouched) hero.getHeroRun() else hero.getHeroIdle(), hero.position.x, hero.position.y)
        sb.draw(left.button, left.xCor, left.yCor)
        sb.draw(right.button, right.xCor, right.yCor)
        sb.draw(jump.button, jump.xCor, jump.yCor)
        scoreHint.draw(sb, "SCORE: $score items: $coinsCount", 0f,400f)

        for (item: Item in items) {
            if (item.isDraw) {
                sb.draw(item.view, item.position.x, item.position.y)
                if (item.rect.overlaps(hero.rect)) {
                    score++
                    item.isDraw = false
                }
            }
        }
        sb.end()
        if (TimeUtils.nanoTime() - lastDropTime > 1*Math.pow(10.0,9.0)) { // time's up?
            if (coinsCount > 0) { //items remains?
                generateCoin()
                coinsCount--
            }
        }
    }

    override fun dispose() {
        for (item: Item in items){
            item.free()
        }
        left.free()
        right.free()
        scoreHint.dispose()
        hero.free()
    }
}