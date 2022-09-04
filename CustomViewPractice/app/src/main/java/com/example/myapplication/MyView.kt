package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class MyView : View {
    private var rect = Rect(10, 10, 110, 110)
    private var circleX = 100F
    private var circleY = 100F
    private var circleR = 50F

    private var triangleX = 100F
    private var triangleY = 100F
    private var triangleW = 100F
    private var color = Color.BLUE
    private var paint = Paint()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = color

        when (Random.nextInt(3) + 1) {  //난수생성
            1 -> {  //1일경우 사각형
                canvas.drawRect(rect, paint)
            }
            2 -> {   //2일경후 원
                canvas.drawCircle(circleX, circleY, circleR, paint)
            }
            3 -> {   //3일경우 삼각형
                val hw = triangleW / 2
                val path = Path()
                path.moveTo(triangleX, triangleY - hw)
                path.lineTo(triangleX - hw, triangleY + hw)
                path.lineTo(triangleX + hw, triangleY + hw)
                path.lineTo(triangleX, triangleY - hw)
                path.close()
                canvas.drawPath(path, paint)
            }
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

            triangleX = event.x
            triangleY = event.y

            invalidate()

            return true
        }
        return super.onTouchEvent(event)
    }
}