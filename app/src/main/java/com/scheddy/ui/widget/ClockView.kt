package com.scheddy.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import java.util.*

class ClockView : View {

  companion object {
    const val DESIRED_WIDTH = 200
    const val DESIRED_HEIGHT = 200
  }

  val minutePointerPaint: Paint = Paint()
  val hourPointerPaint: Paint = Paint()
  val circlePaint: Paint = Paint()
  val time: Calendar = Calendar.getInstance()


  constructor(context: Context?) : this(context, null)

  constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
  super(context, attrs, defStyleAttr) {
    initPaints()
  }

  private fun initPaints() {
    val density = context.resources.displayMetrics.density

    minutePointerPaint.style = Paint.Style.FILL
    minutePointerPaint.strokeWidth = 3 * density
    minutePointerPaint.color = ContextCompat.getColor(context, android.R.color.black)
    minutePointerPaint.isAntiAlias = true

    hourPointerPaint.style = Paint.Style.STROKE
    hourPointerPaint.strokeWidth = 5 * density
    hourPointerPaint.color = ContextCompat.getColor(context, android.R.color.black)
    hourPointerPaint.isAntiAlias = true

    circlePaint.style = Paint.Style.STROKE
    circlePaint.strokeWidth = 4 * density
    circlePaint.color = ContextCompat.getColor(context, android.R.color.holo_blue_dark)
    circlePaint.isAntiAlias = true
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    val widthMode = MeasureSpec.getMode(widthMeasureSpec)
    val widthSize = MeasureSpec.getSize(widthMeasureSpec)

    val heightMode = MeasureSpec.getMode(heightMeasureSpec)
    val heightSize = MeasureSpec.getSize(heightMeasureSpec)

    val measuredWidth = when (widthMode) {
      MeasureSpec.EXACTLY -> widthSize
      MeasureSpec.AT_MOST -> Math.min(DESIRED_WIDTH, widthSize)
      else -> DESIRED_WIDTH
    }

    val measuredHeight = when (heightMode) {
      MeasureSpec.EXACTLY -> heightSize
      MeasureSpec.AT_MOST -> Math.min(DESIRED_HEIGHT, heightSize)
      else -> DESIRED_HEIGHT
    }

    setMeasuredDimension(measuredWidth, measuredHeight)
  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    if (canvas != null) {
      drawImpl(canvas)
    }
  }

  private fun drawImpl(canvas: Canvas) {
    val size = Math.min(width, height)

    val firstRoundRadius = (size / 2f) - 20
    val secondRoundedRadius = (size / 2f) - 40

    val cx = width / 2f
    val cy = height / 2f

    canvas.drawArc(cx - secondRoundedRadius, cy - secondRoundedRadius, cx + secondRoundedRadius, cy + secondRoundedRadius, -90f, 354f, false, circlePaint)

    val sAngle = Math.toRadians(186.0)
    val sx = cx + secondRoundedRadius * Math.sin(sAngle)
    val sy = cy + secondRoundedRadius * Math.cos(sAngle)

    val eAngle = Math.toRadians(180.0)
    val ex = cx + firstRoundRadius * Math.sin(eAngle)
    val ey = cy + firstRoundRadius * Math.cos(eAngle)

    drawMinutesPointer(canvas, size, cx, cy)
    drawHourPointer(canvas, size, cx, cy)

    canvas.drawLine(sx.toFloat(), sy.toFloat(), ex.toFloat(), ey.toFloat(), circlePaint)
    canvas.drawArc(cx - firstRoundRadius, cy - firstRoundRadius, cx + firstRoundRadius, cy + firstRoundRadius, -90f, 354f, false, circlePaint)
  }

  private fun drawHourPointer(canvas: Canvas, size: Int, centerX: Float, centerY: Float) {
    val hourRadius = (size / 2f) - 100
    time.timeInMillis = System.currentTimeMillis()

    val minutes = time.get(Calendar.MINUTE)
    val degreePerMinute = 720 / 1440f

    val degree = -37 * degreePerMinute
    val radian = Math.toRadians(degree.toDouble() - 180)
    val endX = centerX + hourRadius * Math.sin(radian)
    val endY = centerY + hourRadius * Math.cos(radian)

    canvas.drawLine(centerX, centerY, endX.toFloat(), endY.toFloat(), hourPointerPaint)
  }

  private fun drawMinutesPointer(canvas: Canvas, size: Int, centerX: Float, centerY: Float) {
    val minuteRadius = (size / 2f) - 80
    time.timeInMillis = System.currentTimeMillis()

    val minutes = time.get(Calendar.MINUTE)
    val degreePerMinute = 360 / 60f

    val degree = -37 * degreePerMinute
    val radian = Math.toRadians(degree.toDouble() - 180)
    val endX = centerX + minuteRadius * Math.sin(radian)
    val endY = centerY + minuteRadius * Math.cos(radian)

    canvas.drawLine(centerX, centerY, endX.toFloat(), endY.toFloat(), minutePointerPaint)
  }
}
