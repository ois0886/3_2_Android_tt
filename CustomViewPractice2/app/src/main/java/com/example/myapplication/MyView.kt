package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MyView : View {
    private var rect = Rect(10, 10, 110, 110)
    private var circleX = 100F
    private var circleY = 100F
    private var circleR = 50F

    private var color = Color.BLUE
    private val paint = Paint()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var newFigure: String = "rect"

    fun setFigure(figure: String) {
        newFigure = figure
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = color

        when (newFigure) {
            "circle" -> canvas.drawCircle(circleX, circleY, circleR, paint)
            "rect" -> canvas.drawRect(rect, paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN ||
            event.action == MotionEvent.ACTION_MOVE
        ) {
            rect.left = event.x.toInt()
            rect.top = event.y.toInt()
            rect.right = rect.left + 100
            rect.bottom = rect.top + 100

            circleX = event.x
            circleY = event.y

            invalidate()
            return true
        }
        return super.onTouchEvent(event)
    }
}