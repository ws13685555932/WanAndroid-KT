package com.example.wanandroid_kt.view//package com.example.wanandroid_kt.view

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.util.AttributeSet
import android.view.View
import android.view.View.MeasureSpec
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.utils.ColorUtil


class ScoreView : View {
    private var defaultSize = 300
    private lateinit var mPaint: Paint
    private lateinit var mTextPaint: Paint
    private lateinit var mFillPaint: Paint
    private var progress = 0
    var total = 100
    private val margin = 30
    private var level = 0
    private var gradient: SweepGradient? = null
    private val mMatrix: Matrix = Matrix()
    var centerX = 0F
    var radius = 220F
    var centerY = 0F
    private var isAnim = false
    private var isError = false

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
            color = ColorUtil.getColor(R.color.white)
            style = Paint.Style.STROKE
            strokeWidth = 30F
            isAntiAlias =true
            strokeCap = Paint.Cap.ROUND
        }

        mFillPaint = Paint()
        mFillPaint.apply {
            color = ColorUtil.getColor(R.color.colorAccent)
            style = Paint.Style.FILL
            isAntiAlias = true
        }

        mTextPaint = Paint()
        mTextPaint.apply {
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
            textSize = 100F
            color = ColorUtil.getColor(R.color.white)
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
        val white: Int = ColorUtil.getColor(R.color.white)
        val whiteTrans: Int = ColorUtil.getColor(R.color.white_trans)
        gradient = SweepGradient(centerX, centerY, whiteTrans, white)
        mMatrix.setRotate(90F, centerX, centerY) //旋转mRotate度,圆心为(x,y)
        gradient!!.setLocalMatrix(mMatrix)
    }

    var currAngle = 0F
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isError) {
            isAnim = false
        }
        val ratio = calculateRatio()
        val sweepAngle = (ratio * 270)
        drawMaxAndProgress(canvas, sweepAngle)
        drawLittleIndicator(canvas, sweepAngle)
        drawScore(canvas, sweepAngle, ratio)
//        drawLevel(canvas)
        if (isAnim) {
            currAngle += 15
            postInvalidateDelayed(20)
        }
    }

    private fun drawLevel(canvas: Canvas) {
        mTextPaint.setTextSize(50F)
        val fmBottom: Paint.FontMetrics = mTextPaint.getFontMetrics()
        val heightfmBottom: Float = fmBottom.bottom - fmBottom.top
        val baseLinefmBottom =
            (heightfmBottom / 2 - fmBottom.bottom + centerY + radius * 0.707 + margin) as Float
        if (isError) {
            canvas.drawText("Lv.?", centerX, baseLinefmBottom, mTextPaint)
        } else {
            canvas.drawText("Lv.$level", centerX, baseLinefmBottom, mTextPaint)
        }
    }

    private fun drawScore(canvas: Canvas, sweepAngle: Float, ratio: Float) {
        mTextPaint.setTextSize(100F)
        val fm: Paint.FontMetrics = mTextPaint.getFontMetrics()
        val height: Float = fm.bottom - fm.top
        val baseLine: Float = height / 2 - fm.bottom + centerY
        if (currAngle < sweepAngle && isAnim) {
            canvas.drawText(
                "" + (total * (currAngle * 1.0 / 270)).toInt(),
                centerX,
                baseLine,
                mTextPaint
            )
        } else if (isError) {
            canvas.drawText("???", centerX, baseLine, mTextPaint)
        } else {
            canvas.drawText("" + (total * ratio).toInt(), centerX, baseLine, mTextPaint)
        }
    }

    private fun drawLittleIndicator(canvas: Canvas, sweepAngle: Float) {
        canvas.save()
        canvas.translate(centerX, centerY)
        canvas.rotate(-135F)
        if (currAngle < sweepAngle && isAnim) {
            canvas.rotate(currAngle)
        } else {
            canvas.rotate(sweepAngle)
        }
        canvas.drawCircle(0F, -radius, 10F, mFillPaint)
        canvas.restore()
    }

    private fun drawMaxAndProgress(canvas: Canvas, sweepAngle: Float) {
        mPaint.setColor(getResources().getColor(R.color.white_trans))
        canvas.drawArc(
            RectF(
                (centerX - radius).toFloat(),
                (centerY - radius).toFloat(),
                (centerX + radius).toFloat(),
                (centerY + radius).toFloat()
            )
            , 135F, 270F, false, mPaint
        )
        mPaint.setColor(getResources().getColor(R.color.white))
        //        mPaint.setShader(gradient);
        if (currAngle < sweepAngle && isAnim) {
            canvas.drawArc(
                RectF(
                    (centerX - radius).toFloat(),
                    (centerY - radius).toFloat(),
                    (centerX + radius).toFloat(),
                    (centerY + radius).toFloat()
                )
                , 135F, currAngle, false, mPaint
            )
        } else {
            isAnim = false
            canvas.drawArc(
                RectF(
                    (centerX - radius).toFloat(),
                    (centerY - radius).toFloat(),
                    (centerX + radius).toFloat(),
                    (centerY + radius).toFloat()
                )
                , 135F, sweepAngle, false, mPaint
            )
        }
    }

    private fun calculateRatio(): Float {
        var ratio = 0f
        if (total != 0) {
            ratio = (progress * 1.0 / total).toFloat()
        }
        return ratio
    }

    fun dp2px(values: Int): Int {
        val density: Float = getResources().getDisplayMetrics().density
        return (values * density + 0.5f).toInt()
    }

    fun getProgress(): Int {
        return progress
    }

    fun setProgress(progress: Int) {
        this.progress = progress
        postInvalidate()
    }

    fun getLevel(): Int {
        return level
    }

    fun setLevel(level: Int) {
        this.level = level
        postInvalidate()
    }

    fun startAnim() {
        isAnim = true
        currAngle = 0F
        postInvalidate()
    }

    fun isError(isError: Boolean) {
        this.isError = isError
        postInvalidate()
    }
}