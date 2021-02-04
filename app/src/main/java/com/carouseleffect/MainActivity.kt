package com.carouseleffect

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.carouseleffect.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val listItems = intArrayOf(R.mipmap.img1, R.mipmap.img2, R.mipmap.img3, R.mipmap.img4,
            R.mipmap.img5, R.mipmap.img6, R.mipmap.img7, R.mipmap.img8, R.mipmap.img9, R.mipmap.img10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        setupViewPager()
    }

    /**
     * Initialize all required variables
     */
    private fun init() {

        binding.viewpagerTop.clipChildren = false
        binding.viewpagerTop.pageMargin = resources.getDimensionPixelOffset(R.dimen.pager_margin)
        binding.viewpagerTop.offscreenPageLimit = 3
        binding.viewpagerTop.setPageTransformer(false, CarouselEffectTransformer(this)) // Set transformer
    }

    /**
     * Setup viewpager and it's events
     */
    private fun setupViewPager() {
        // Set Top ViewPager Adapter
        val adapter = MyPagerAdapter( listItems, ADAPTER_TYPE_TOP)
        binding.viewpagerTop.adapter = adapter

        // Set Background ViewPager Adapter
        val adapterBackground = MyPagerAdapter(listItems, ADAPTER_TYPE_BOTTOM)
        binding.viewPagerbackground.adapter = adapterBackground
        binding.viewpagerTop.addOnPageChangeListener(object : OnPageChangeListener {
            private var index = 0
            override fun onPageSelected(position: Int) {
                index = position
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val width = binding.viewPagerbackground.width
                binding.viewPagerbackground.scrollTo((width * position + width * positionOffset).toInt(), 0)
            }

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    binding.viewPagerbackground.currentItem = index
                }
            }
        })
    }

    /**
     * Handle all click event of activity
     */
    fun clickEvent(view: View) {
        when (view.id) {
            R.id.imageCover -> if (view.tag != null) {
                val position = view.tag.toString().toInt()
                //Toast.makeText(getApplicationContext(), "Poistion: " + poisition, Toast.LENGTH_LONG).show();
                val intent = Intent(this, FullScreenActivity::class.java)
                intent.putExtra(EXTRA_IMAGE, listItems[position])
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view.findViewById(R.id.imageCover), EXTRA_TRANSITION_IMAGE)
                ActivityCompat.startActivity(this, intent, options.toBundle())
            }
        }
    }

    companion object {
        const val ADAPTER_TYPE_TOP = 1
        const val ADAPTER_TYPE_BOTTOM = 2
        const val EXTRA_IMAGE = "image"
        const val EXTRA_TRANSITION_IMAGE = "image"
    }
}