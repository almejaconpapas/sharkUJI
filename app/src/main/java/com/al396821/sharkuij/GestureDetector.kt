package com.al396821.sharkuij

import android.graphics.Path
import com.jcamenatuji.sharkuji.model.Direction
import kotlin.math.abs

internal class GestureDetector {
    enum class Gestures {
        SWIPE, CLICK, NONE
    }

    private var xOrigin = 0f
    private var yOrigin = 0f
    private var detecting = false
    var direction: Path.Direction = Path.Direction.RIGHT
        private set

    fun onTouchDown(x: Float, y: Float) {
        xOrigin = x
        yOrigin = y
        detecting = true
    }

    fun onTouchUp(x: Float, y: Float): Gestures {
        if (!detecting) return Gestures.NONE
        detecting = false
        if (abs(x - xOrigin) <= CLICK_THRESHOLD && abs(y - yOrigin) <= CLICK_THRESHOLD) {
            return Gestures.CLICK
        }
        if (abs(x - xOrigin) >= SWIPE_THRESHOLD && abs(y - yOrigin) <= SWIPE_MARGIN) {
            direction = if (x < xOrigin) Path.Direction.LEFT else Path.Direction.RIGHT
            return Gestures.SWIPE
        }
        if (abs(y - yOrigin) >= SWIPE_THRESHOLD && abs(x - xOrigin) <= SWIPE_MARGIN) {
            direction = if (y < yOrigin) Direction.UP else Direction.DOWN
            return Gestures.SWIPE
        }
        return Gestures.NONE
    }

    companion object {
        private const val SWIPE_THRESHOLD = 20f
        private const val SWIPE_MARGIN = .5f * SWIPE_THRESHOLD
        private const val CLICK_THRESHOLD = SWIPE_THRESHOLD / 10f
    }
}