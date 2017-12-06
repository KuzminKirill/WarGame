package com.wardemo.game.core

import com.badlogic.gdx.graphics.g2d.TextureRegion


class HeroAnimation(region: TextureRegion, private val frameCount: Int, cycleTime: Float) {
    private val frames: MutableList<TextureRegion>
    private val maxFrameTime: Float
    private var currentFrameTime: Float = 0.toFloat()
    private var frame: Int = 0

    init {
        frames = ArrayList()
        val frameWidth = region.regionWidth / frameCount
        for (i in 0 until frameCount) {
            frames.add(TextureRegion(region, i * frameWidth, 0, frameWidth, region.regionHeight))
        }
        maxFrameTime = cycleTime / frameCount
        frame = 0
    }


    fun update(dt: Float) {
        currentFrameTime += dt
        if (currentFrameTime > maxFrameTime) {
            frame++
            currentFrameTime = 0f
        }
        if (frame >= frameCount)
            frame = 0
    }

    fun getFrame(): TextureRegion {
        return frames[frame]
    }
}