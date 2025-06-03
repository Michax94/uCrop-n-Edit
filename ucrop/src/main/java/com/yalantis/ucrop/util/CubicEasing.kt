package com.yalantis.ucrop.util

object CubicEasing {
    fun easeOut(time: Float, start: Float, end: Float, duration: Float): Float {
        val t = time / duration - 1.0f
        return end * (t * t * t + 1.0f) + start
    }

    fun easeIn(time: Float, start: Float, end: Float, duration: Float): Float {
        val t = time / duration
        return end * t * t * t + start
    }

    fun easeInOut(time: Float, start: Float, end: Float, duration: Float): Float {
        var t = time / (duration / 2.0f)
        return if (t < 1.0f) {
            end / 2.0f * t * t * t + start
        } else {
            t -= 2.0f
            end / 2.0f * (t * t * t + 2.0f) + start
        }
    }
}