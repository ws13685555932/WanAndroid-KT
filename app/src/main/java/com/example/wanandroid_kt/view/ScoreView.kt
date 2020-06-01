package com.example.wanandroid_kt.view//package com.example.wanandroid_kt.view
//
//import android.R
//import android.content.Context
//import android.graphics.*
//import android.opengl.ETC1.getHeight
//import android.opengl.ETC1.getWidth
//import android.util.AttributeSet
//import android.view.View
//import android.view.View.MeasureSpec
//import androidx.annotation.Nullable
//
//
//class ScoreView : View {
//    private var defaultSize = 300
//    private var mPaint: Paint? = null
//    private var mTextPaint: Paint? = null
//    private var mFillPaint: Paint? = null
//    private var progress = 0
//    var total = 10
//    private val margin = 30
//    private var level = 0
//    private var gradient: SweepGradient? = null
//    private val mMatrix: Matrix = Matrix()
//    var centerX = 0
//    var radius = 220
//    var centerY = 0
//    private var isAnim = false
//    private var isError = false
//
//    constructor(context: Context) : super(context) {
//        initData(context)
//    }
//
//    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs) {
//        initData(context)
//    }
//
//    constructor(
//        context: Context,
//        @Nullable attrs: AttributeSet?,
//        defStyleAttr: Int
//    ) : super(context, attrs, defStyleAttr) {
//        initData(context)
//    }
//
//    private fun initData(context: Context) {
//        defaultSize = dp2px(defaultSize)
//        mPaint = Paint()
//        mPaint?.setColor(getResources().getColor(R.color.white))
//        mPaint?.setStyle(Paint.Style.STROKE)
//        mPaint?.setStrokeWidth(30)
//        mPaint?.setAntiAlias(true)
//        mPaint?.setStrokeCap(Paint.Cap.ROUND)
//        mFillPaint = Paint()
//        mFillPaint?.setColor(getResources().getColor(R.color.c))
//        mFillPaint?.setStyle(Paint.Style.FILL)
//        mFillPaint?.setAntiAlias(true)
//        mTextPaint = Paint()
//        mTextPaint?.setAntiAlias(true)
//        mTextPaint?.setTextAlign(Paint.Align.CENTER)
//        mTextPaint?.setTextSize(100)
//        mTextPaint?.setColor(getResources().getColor(R.color.white))
//    }
//
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
//        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)
//        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
//        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)
//        val width: Int
//        val height: Int
//        width = if (modeWidth == MeasureSpec.EXACTLY) {
//            sizeWidth
//        } else {
//            Math.min(sizeWidth, defaultSize)
//        }
//        height = if (modeHeight == MeasureSpec.EXACTLY) {
//            sizeHeight
//        } else {
//            Math.min(sizeHeight, defaultSize)
//        }
//        setMeasuredDimension(width, height)
//    }
//
//    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
//        super.onSizeChanged(w, h, oldw, oldh)
//        centerX = getWidth() / 2
//        centerY = getHeight() / 2
//        val white: Int = getResources().getColor(R.color.white)
//        val whiteTrans: Int = getResources().getColor(R.color.white_trans)
//        gradient = SweepGradient(centerX, centerY, whiteTrans, white)
//        mMatrix.setRotate(90, centerX, centerY) //旋转mRotate度,圆心为(x,y)
//        gradient!!.setLocalMatrix(mMatrix)
//    }
//
//    var currAngle = 0
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//        if (isError) {
//            isAnim = false
//        }
//        val ratio = calculateRatio()
//        val sweepAngle = (ratio * 270).toInt()
//        drawMaxAndProgress(canvas, sweepAngle)
//        drawLittleIndicator(canvas, sweepAngle)
//        drawScore(canvas, sweepAngle, ratio)
//        drawLevel(canvas)
//        if (isAnim) {
//            currAngle += 15
//            postInvalidateDelayed(20)
//        }
//    }
//
//    private fun drawLevel(canvas: Canvas) {
//        mTextPaint.setTextSize(50)
//        val fmBottom: Paint.FontMetrics = mTextPaint.getFontMetrics()
//        val heightfmBottom: Float = fmBottom.bottom - fmBottom.top
//        val baseLinefmBottom =
//            (heightfmBottom / 2 - fmBottom.bottom + centerY + radius * 0.707 + margin) as Float
//        if (isError) {
//            canvas.drawText("Lv.?", centerX, baseLinefmBottom, mTextPaint)
//        } else {
//            canvas.drawText("Lv.$level", centerX, baseLinefmBottom, mTextPaint)
//        }
//    }
//
//    private fun drawScore(canvas: Canvas, sweepAngle: Int, ratio: Float) {
//        mTextPaint.setTextSize(100)
//        val fm: Paint.FontMetrics = mTextPaint.getFontMetrics()
//        val height: Float = fm.bottom - fm.top
//        val baseLine: Float = height / 2 - fm.bottom + centerY
//        if (currAngle < sweepAngle && isAnim) {
//            canvas.drawText(
//                "" + (total * (currAngle * 1.0 / 270)).toInt(),
//                centerX,
//                baseLine,
//                mTextPaint
//            )
//        } else if (isError) {
//            canvas.drawText("???", centerX, baseLine, mTextPaint)
//        } else {
//            canvas.drawText("" + (total * ratio).toInt(), centerX, baseLine, mTextPaint)
//        }
//    }
//
//    private fun drawLittleIndicator(canvas: Canvas, sweepAngle: Int) {
//        canvas.save()
//        canvas.translate(centerX, centerY)
//        canvas.rotate(-135)
//        if (currAngle < sweepAngle && isAnim) {
//            canvas.rotate(currAngle)
//        } else {
//            canvas.rotate(sweepAngle)
//        }
//        canvas.drawCircle(0, -radius, 10, mFillPaint)
//        canvas.restore()
//    }
//
//    private fun drawMaxAndProgress(canvas: Canvas, sweepAngle: Int) {
//        mPaint.setColor(getResources().getColor(R.color.white_trans))
//        canvas.drawArc(
//            RectF(
//                (centerX - radius).toFloat(),
//                (centerY - radius).toFloat(),
//                (centerX + radius).toFloat(),
//                (centerY + radius).toFloat()
//            )
//            , 135, 270, false, mPaint
//        )
//        mPaint.setColor(getResources().getColor(R.color.white))
//        //        mPaint.setShader(gradient);
//        if (currAngle < sweepAngle && isAnim) {
//            canvas.drawArc(
//                RectF(
//                    (centerX - radius).toFloat(),
//                    (centerY - radius).toFloat(),
//                    (centerX + radius).toFloat(),
//                    (centerY + radius).toFloat()
//                )
//                , 135, currAngle, false, mPaint
//            )
//        } else {
//            isAnim = false
//            canvas.drawArc(
//                RectF(
//                    (centerX - radius).toFloat(),
//                    (centerY - radius).toFloat(),
//                    (centerX + radius).toFloat(),
//                    (centerY + radius).toFloat()
//                )
//                , 135, sweepAngle, false, mPaint
//            )
//        }
//    }
//
//    private fun calculateRatio(): Float {
//        var ratio = 0f
//        if (total != 0) {
//            ratio = (progress * 1.0 / total).toFloat()
//        }
//        return ratio
//    }
//
//    fun dp2px(values: Int): Int {
//        val density: Float = getResources().getDisplayMetrics().density
//        return (values * density + 0.5f).toInt()
//    }
//
//    fun getProgress(): Int {
//        return progress
//    }
//
//    fun setProgress(progress: Int) {
//        this.progress = progress
//        postInvalidate()
//    }
//
//    fun getLevel(): Int {
//        return level
//    }
//
//    fun setLevel(level: Int) {
//        this.level = level
//        postInvalidate()
//    }
//
//    fun startAnim() {
//        isAnim = true
//        currAngle = 0
//        postInvalidate()
//    }
//
//    fun isError(isError: Boolean) {
//        this.isError = isError
//        postInvalidate()
//    }
//}