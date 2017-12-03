package com.wardemo.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.TimeUtils
import com.wardemo.game.WarDemo
import com.wardemo.game.core.Coin
import com.wardemo.game.core.Hero
import com.sun.awt.SecurityWarning.setPosition
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.wardemo.game.core.Ground


class LevelState(gsm :GameStateManager) : State(gsm) {

    var coins : MutableList<Coin>
    var hero : Hero
    var coin : Coin

    //var left : Button
    //var right : Button
    private val leftBtn: Texture
    private val rightBtn: Texture

    private val back: Texture
    //private val ground: Texture
    var ground1: Ground
    private var font : BitmapFont
    var score : Int
    var lastDropTime : Long
    var coinsCount : Int

    init {
        hero = Hero(50f,50f)
        coin = Coin( 100f, 200f)
        camera.setToOrtho(false,WarDemo.WIDTH / 2f, WarDemo.HEIGHT / 2f)
        score = 0
        font = BitmapFont()
        leftBtn = Texture("left.png")
        rightBtn = Texture("right.png")
        coins = ArrayList()
        //left = Button(0f,0f,300f,300f, "left.png")
        //right = Button(400f,0f,700f,300f, "left.png")
        back = Texture("Sky.jpg")
        ground1 = Ground()

        lastDropTime = 0
        coinsCount = 10
    }

    override fun handleInput() {
        if (Gdx.input.isTouched) {
            if ((Gdx.input.x > 1600) && (Gdx.input.x < 1700) && (Gdx.input.y > 950) && (Gdx.input.y < 1080)) {
                hero.jump()
            }

      //          if (left.isTouched(Gdx.input.x.toFloat(),Gdx.input.y.toFloat())) {
                if ((Gdx.input.x > 900) && (Gdx.input.x < 1100) && (Gdx.input.y > 950) && (Gdx.input.y < 1080)) {
                    hero.move_right()
                }
      //          if (left.isTouched(Gdx.input.x.toFloat(),Gdx.input.y.toFloat())) {
                if ((Gdx.input.x > 0) && (Gdx.input.x < 150) && (Gdx.input.y > 950) && (Gdx.input.y < 1080)) {
                    hero.move_left()
                }
            }
            else hero.velocity.x = 0f
    }

    override fun update(dt: Float) {
        handleInput()
        hero.update(dt)
        for (coin : Coin in coins) coin.update(dt)
    }

    fun generatecoin() {
            var coin: Coin
            coin = Coin(MathUtils.random(0f, 600f), MathUtils.random(0f, 400f))
            coins.add(coin)
            lastDropTime = TimeUtils.nanoTime()
            coinsCount--
    }




    override fun render(sb: SpriteBatch) {

        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        camera.update()
        sb.projectionMatrix = camera.combined
        sb.begin()

        sb.draw(back, 0f, 0f)
        sb.draw(leftBtn, 0f, 0f)
        sb.draw(ground1.getgr(), ground1.getposgrx(), ground1.getposgry())
        sb.draw(hero.hero, hero.position.x,hero.position.y)

        font.draw(sb, "SCORE: " + score, 0f,400f)
        sb.draw(leftBtn, 0f, 0f)
        sb.draw(rightBtn, 25f, 0f)
       // sb.draw(left.button,left.leftDownPosition.x,left.leftDownPosition.y)
       // sb.draw(right.button,right.leftDownPosition.x,right.leftDownPosition.y)
        sb.draw(hero.hero, hero.position.x, hero.position.y)


       /* val iterator = coins.listIterator()
        while (iterator.hasNext()) {
            sb.draw(iterator.next().coin, iterator.next().position.x, iterator.next().position.y)
            if (iterator.next().rect.overlaps(hero.rect)) {
                score++
                iterator.remove()
            }
        }*/
        for (coin : Coin in coins) {
            if (coin.isDraw) {
                sb.draw(coin.coin, coin.position.x, coin.position.y)
                if (coin.rect.overlaps(hero.rect)) {
                    score++
                    coin.isDraw = false
                    //coins.remove(coin)
                }
            }
        }


        sb.end()
        if (TimeUtils.nanoTime() - lastDropTime > 2*Math.pow(10.0,9.0)) { // time's up?
            if (coinsCount > 0) { //coins remains?
                generatecoin()
                coinsCount--
            }
        }
    }

    override fun dispose() {
        rightBtn.dispose()
        leftBtn.dispose()
        font.dispose()
        hero.free()
    }
}