package com.carouseleffect;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;


public class MyPagerAdapter extends PagerAdapter{

    Context context;
    int[] listItems;
    int adapterType;

    public MyPagerAdapter(Context context, int[] listItems, int adapterType) {
        this.context = context;
        this.listItems = listItems;
        this.adapterType=adapterType;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cover, null);
        try {

            LinearLayout linMain = (LinearLayout) view.findViewById(R.id.linMain);
            ImageView imageCover = (ImageView) view.findViewById(R.id.imageCover);
            linMain.setTag(position);

            switch (adapterType)
            {
                case MainActivity.ADAPTER_TYPE_TOP:
                    linMain.setBackgroundResource(R.drawable.shadow);
                    break;
                case MainActivity.ADAPTER_TYPE_BOTTOM:
                    linMain.setBackgroundResource(0);
                    break;
            }

            Glide.with(context)
                    .load(listItems[position])
                    .into(imageCover);

            container.addView(view);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return listItems.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

}