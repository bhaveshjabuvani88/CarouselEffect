package com.carouseleffect

import android.content.Context
import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.PageTransformer
import com.carouseleffect.util.nullSafe

class CarouselEffectTransformer(context: Context) : PageTransformer {
    private val maxTranslateOffsetX=dp2px(context, 180f)
    private var viewPager: ViewPager? = null
    override fun transformPage(view: View, position: Float) {
        if (viewPager == null) {
            viewPager = view.parent as ViewPager
        }
        val leftInScreen = view.left - viewPager?.scrollX.nullSafe()
        val centerXInViewPager = leftInScreen + view.measuredWidth / 2
        val offsetX = centerXInViewPager - viewPager?.measuredWidth.nullSafe() / 2
        val offsetRate = offsetX.toFloat() * 0.38f / viewPager?.measuredWidth.nullSafe()
        val scaleFactor = 1 - Math.abs(offsetRate)
        if (scaleFactor > 0) {
            view.scaleX = scaleFactor
            view.scaleY = scaleFactor
            view.translationX = -maxTranslateOffsetX * offsetRate
            //ViewCompat.setElevation(view, 0.0f);
        }
        ViewCompat.setElevation(view, scaleFactor)
    }

    /**
     * Dp to pixel conversion
     */
    private fun dp2px(context: Context, dipValue: Float): Int {
        val m = context.resources.displayMetrics.density
        return (dipValue * m + 0.5f).toInt()
    }
}