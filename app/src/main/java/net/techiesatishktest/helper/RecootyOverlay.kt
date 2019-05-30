package net.techiesatishktest.helper

import android.graphics.*

/**
 * Created by Satish on 26/05/2019 12:59 AM.
 */
 class RecootyOverlay internal  constructor(overlay: GraphicOverlay,private val boud:Rect?):GraphicOverlay.Graphic(overlay){
  private val reactPaint:Paint

    init {
        reactPaint= Paint()
        reactPaint.color=REACT_COLOR
        reactPaint.strokeWidth=REACT_WIDTH
        reactPaint.style=Paint.Style.STROKE
        postInvalidate()
    }


    companion object{
        private val REACT_COLOR=Color.RED
        private val REACT_WIDTH=8.0f
    }


    override fun draw(canvas: Canvas) {

        val  rect=RectF(boud)
        canvas.drawRect(rect,reactPaint)
    }


}