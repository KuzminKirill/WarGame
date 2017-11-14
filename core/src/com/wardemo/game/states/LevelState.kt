package com.wardemo.game.states


import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wardemo.game.WarDemo
import com.wardemo.game.core.Hero

class LevelState(gsm :GameStateManager) : State(gsm) {

    var hero : Hero

    init {
        hero = Hero(50f,50f)
        camera.setToOrtho(false,WarDemo.WIDTH / 2f, WarDemo.HEIGHT / 2f)
    }
    override fun handleInput() {

    }

    override fun update(dt: Float) {
        hero.update(dt)
    }

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = camera.combined
        sb.begin()
        sb.draw(hero.hero, hero.position.x,hero.position.y)
        sb.end()
    }

    override fun dispose() {

    }
}