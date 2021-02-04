package com.carouseleffect

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.viewpager.widget.PagerAdapter
import com.carouseleffect.databinding.ItemCoverBinding
import com.carouseleffect.util.GlideApp

class MyPagerAdapter(private val listItems: IntArray, var adapterType: Int) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemCoverBinding.inflate(LayoutInflater.from(container.context), null, false).apply {
            imageCover.tag = position
            when (adapterType) {
                MainActivity.ADAPTER_TYPE_TOP -> imageCover.setBackgroundResource(R.drawable.shadow)
                MainActivity.ADAPTER_TYPE_BOTTOM -> imageCover.setBackgroundResource(0)
            }
            GlideApp.with(root)
                    .load(listItems[position])
                    .into(imageCover)
            container.addView(root)
        }
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return listItems.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}