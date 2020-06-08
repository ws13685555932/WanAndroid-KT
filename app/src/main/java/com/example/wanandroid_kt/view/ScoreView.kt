package com.example.wanandroid_kt.view//package com.example.wanandroid_kt.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.ext.getColor
import com.example.wanandroid_kt.ext.log
import com.example.wanandroid_kt.utils.ColorUtil


class ScoreView : View {
    private var defaultSize = 300
    private lateinit var mPaint: Paint
    private lateinit var mTextPaint: Paint
    private lateinit var mFillPaint: Paint
    private var progress = 0
    private var total = 100
    private val margin = 30F
    private var gradient: SweepGradient? = null
    private val mMatrix: Matrix = Matrix()
    private var centerX = 0F
    private var centerY = 0F
    private var radius = 220F

    private val totalAngle = 270F
    private val startAngle = 135F
    private var sweepAngle = 0F

    constructor(context: Context) : super(context) {
        initData(context)
    }

    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs) {
        initData(context)
    }

    constructor(
        context: Context,
        @Nullable attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initData(context)
    }


    private fun initData(context: Context) {
        defaultSize = dp2px(defaultSize)
        mPaint = Paint()
        mPaint.apply {
            color = R.color.white.getColor()
            style = Paint.Style.STROKE
            strokeWidth = 30F
            isAntiAlias = true
            strokeCap = Paint.Cap.ROUND
        }

        mFillPaint = Paint()
        mFillPaint.apply {
            color = R.color.colorAccent.getColor()
            style = Paint.Style.FILL
            isAntiAlias = true
        }

        mTextPaint = Paint()
        mTextPaint.apply {
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
            textSize = 100F
            color = R.color.white.getColor()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)
        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)
        val width: Int
        val height: Int
        width = if (modeWidth == MeasureSpec.EXACTLY) {
            sizeWidth
        } else {
            Math.min(sizeWidth, defaultSize)
        }
        height = if (modeHeight == MeasureSpec.EXACTLY) {
            sizeHeight
        } else {
            Math.min(sizeHeight, defaultSize)
        }
        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = (width / 2).toFloat()
        centerY = (height / 2).toFloat()
        val white: Int = R.color.white.getColor()
        val whiteTrans: Int = R.color.white_trans.getColor()
        gradient = SweepGradient(centerX, centerY, whiteTrans, white)
        mMatrix.setRotate(90F, centerX, centerY) //旋转mRotate度,圆心为(x,y)
        gradient!!.setLocalMatrix(mMatrix)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val ratio : Float = calculateRatio()
        sweepAngle = ratio * totalAngle
        drawTotalAndProgress(canvas)
        drawIndicator(canvas)
        drawScore(canvas)
        drawTintText(canvas)
//        drawLevel(canvas)
    }

    private fun calculateRatio(): Float {
        return (progress * 1.0 / total).toFloat()
    }

    private fun drawTotalAndProgress(canvas: Canvas) {
        mPaint.color = R.color.white_trans.getColor()
        canvas.drawArc(
            RectF(
                (centerX - radius),
                (centerY - radius),
                (centerX + radius),
                (centerY + radius)
            )
            , startAngle, totalAngle, false, mPaint
        )
        mPaint.color = R.color.white.getColor()
//        mPaint.shader = gradient
        canvas.drawArc(
            RectF(
                (centerX - radius).toFloat(),
                (centerY - radius).toFloat(),
                (centerX + radius).toFloat(),
                (centerY + radius).toFloat()
            )
            , startAngle, sweepAngle, false, mPaint
        )

    }

    private fun drawIndicator(canvas: Canvas) {
        canvas.save()
        canvas.translate(centerX, centerY)
        canvas.rotate(-135F)
        canvas.rotate(sweepAngle)
        canvas.drawCircle(0F, -radius, 10F, mFillPaint)
        canvas.restore()
    }

//    private fun drawLevel(canvas: Canvas) {
//        mTextPaint.setTextSize(50F)
//        val fmBottom: Paint.FontMetrics = mTextPaint.getFontMetrics()
//        val heightfmBottom: Float = fmBottom.bottom - fmBottom.top
//        val baseLinefmBottom =
//            (heightfmBottom / 2 - fmBottom.bottom + centerY + radius * 0.707 + margin) as Float
//        canvas.drawText("Lv.$level", centerX, baseLinefmBottom, mTextPaint)
//    }

    private fun drawScore(canvas: Canvas) {
        mTextPaint.textSize = 100F
        mTextPaint.color = R.color.white.getColor()
        val fm: Paint.FontMetrics = mTextPaint.getFontMetrics()
        val height: Float = fm.bottom - fm.top
        val baseLine: Float = height / 2 - fm.bottom + centerY

        canvas.drawText(
            progress.toString(),
            centerX,
            baseLine,
            mTextPaint
        )
    }

    private fun drawTintText(canvas: Canvas) {
        mTextPaint.textSize = 50F
        mTextPaint.color = R.color.light_pink.getColor()
        val fmBottom: Paint.FontMetrics = mTextPaint.fontMetrics
        val heightfmBottom: Float = fmBottom.bottom - fmBottom.top
        val baseLinefmBottom = heightfmBottom / 2 - fmBottom.bottom + centerY + radius * 0.707
        canvas.drawText("我的积分", centerX, baseLinefmBottom.toFloat(), mTextPaint)
    }



    fun dp2px(values: Int): Int {
        val density: Float = getResources().getDisplayMetrics().density
        return (values * density + 0.5f).toInt()
    }

    fun getCoin(): Int {
        return progress
    }

    fun setCoin(coin: Int){
        this.progress = coin
        postInvalidate()
    }

    fun setMaxCoin(coin : Int){
        total = coin
    }

    fun getMaxCoin() : Int{
        return total
    }

}