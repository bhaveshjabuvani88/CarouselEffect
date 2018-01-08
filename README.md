# CarouselEffect
Carousel Effect with ViewPager

![GifSample](https://github.com/bhaveshjabuvani-credencys/CarouselEffect/blob/master/CarouselEffectDemo.gif?raw=true)

[<img src="/Store/google-play-badge.png" height="116" width="300">](https://play.google.com/store/apps/details?id=com.carouseleffect)

#### Usage:
Simply add two ViewPager in FrameLayout (1. Top ViewPager for CarouselEffect, 2. Background ViewPager)

Set Top ViewPager padding left and right so previsous and next item display and also set android:clipToPadding="false" 

Set Adapter for both ViewPager with same list items

```xml
<android.support.v4.view.ViewPager
        android:id="@+id/viewPagerBackground"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="center" />
        
<android.support.v4.view.ViewPager
        android:id="@+id/viewpagerTop"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:clipToPadding="false"
        android:paddingLeft="50dp"
        android:paddingRight="50dp"
        android:layout_gravity="center" />
```

Parallel scroll both for ViewPagers set OnPageChangeListener to Top ViewPager and override onPageScrolled() and write below logic to 

```java
@Override
public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
         int width = viewPagerBackground.getWidth();
         viewPagerBackground.scrollTo((int) (width * position + width * positionOffset), 0);
}
```
