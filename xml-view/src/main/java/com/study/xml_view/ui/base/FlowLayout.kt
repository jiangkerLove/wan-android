package com.study.xml_view.ui.base

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

class FlowLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measureSpecWidth = MeasureSpec.getSize(widthMeasureSpec)
        val measureSpecHeight = MeasureSpec.getSize(heightMeasureSpec)
        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)


        var lineWidth = 0
        var lineHeight = 0
        var height = 0
        var width = 0

        repeat(childCount) {
            val child = getChildAt(it)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val layoutParams = child.layoutParams as MarginLayoutParams
            val childWidth = child.measuredWidth + layoutParams.marginStart + layoutParams.marginEnd
            val childHeight =
                child.measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin

            if (lineWidth + childWidth > measureSpecWidth) {
                width = lineWidth.coerceAtLeast(childWidth)
                height += lineHeight;
                lineWidth = childWidth;
                lineHeight = childHeight
            } else {
                lineHeight = lineHeight.coerceAtLeast(childHeight)
                lineWidth += childWidth
            }

            if (it == (childCount - 1)) {
                height += lineHeight
                width = width.coerceAtLeast(lineWidth)
            }
        }

        setMeasuredDimension(
            if (widthSpecMode == MeasureSpec.EXACTLY) measureSpecWidth else width,
            if (heightSpecMode == MeasureSpec.EXACTLY) measureSpecHeight else height
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var lineHeight = 0
        var lineWidth = 0
        var top = 0
        var start = 0
        repeat(childCount) {
            val child = getChildAt(it)
            val layoutParams = child.layoutParams as MarginLayoutParams
            val childWidth = child.measuredWidth + layoutParams.topMargin + layoutParams.rightMargin
            val childHeight =
                child.measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin

            if ((childWidth + lineWidth) > measuredWidth) {
                top += lineHeight
                start = 0
                lineHeight = childHeight
                lineWidth = childWidth
            } else {
                lineHeight = lineHeight.coerceAtLeast(childHeight)
                lineWidth += childWidth
            }

            val lc = start + layoutParams.marginStart
            val tc = top + layoutParams.topMargin
            val rc = lc + child.measuredWidth
            val bc = tc + child.measuredHeight

            child.layout(lc, tc, rc, bc)
            start += childWidth
        }
    }

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

}