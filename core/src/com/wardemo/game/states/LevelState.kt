package com.wardemo.game.states


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.TimeUtils
import com.wardemo.game.WarDemo
import com.wardemo.game.core.Coin
import com.wardemo.game.core.Hero
import com.wardemo.game.core.Ground

class LevelState(gsm :GameStateManager) : State(gsm) {
    val coins : MutableList<Coin>
   // val ground : MutableList<Ground> //ground array
    var hero : Hero

    //buttons
    val left : customButton
    val right : customButton
    val jump : customButton

    private val back: Texture
    var ground1: Ground
    private val scoreHint: BitmapFont

    var score : Int
    var lastDropTime : Long
    var coinsCount : Int

    init {
        coins = ArrayList()

        hero = Hero(50f,50f)

        //buttons
        left = customButton(0f,0f, "left.png")
        right = customButton(250f,0f,"right.png")
        jump = customButton(1700f, 0f, "jump.png")

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
            //if ((Gdx.input.x > 1600) && (Gdx.input.x < 1700) && (Gdx.input.y > 950) && (Gdx.input.y < 1080)) {
                hero.jump()
            }

            if (left.isTouched(Gdx.input.x.toFloat(), 1080 - Gdx.input.y.toFloat())) {
                hero.move_left()
            }

            if (right.isTouched(Gdx.input.x.toFloat(), 1080 - Gdx.input.y.toFloat())) {
                hero.move_right()
            }

        } else hero.velocity.x = 0f
    }

    override fun update(dt: Float) {
        handleInput()
        hero.update(dt)
        for (coin : Coin in coins) coin.update(dt)
    }

    fun generatecoin() {
            val coin = Coin(MathUtils.random(20f, 600f), MathUtils.random(200f, 400f))
            coins.add(coin)
            lastDropTime = TimeUtils.nanoTime()
    }

    override fun render(sb: SpriteBatch) {
        sb.begin()
        sb.draw(back, 0f, 0f,1920f,1080f)
        sb.draw(ground1.getgr(), ground1.getposgrx(), ground1.getposgry())
        sb.draw(hero.getHero(), hero.position.x, hero.position.y)
        sb.draw(left.button, left.xCor, left.yCor)
        sb.draw(right.button, right.xCor, right.yCor)
        sb.draw(jump.button, jump.xCor, jump.yCor)
        sb.draw(hero.getHero(), hero.position.x, hero.position.y)

        scoreHint.draw(sb, "SCORE: $score coins: $coinsCount", 0f,400f)

        for (coin : Coin in coins) {
            if (coin.isDraw) {
                sb.draw(coin.coin, coin.position.x, coin.position.y)
                if (coin.rect.overlaps(hero.rect)) {
                    score++
                    coin.isDraw = false
                }
            }
        }
        sb.end()
        if (TimeUtils.nanoTime() - lastDropTime > 1*Math.pow(10.0,9.0)) { // time's up?
            if (coinsCount > 0) { //coins remains?
                generatecoin()
                coinsCount--
            }
        }
    }

    override fun dispose() {
        for (coin : Coin in coins){
            coin.free()
        }
        left.free()
        right.free()
        scoreHint.dispose()
        hero.free()
    }
}