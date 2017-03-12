package com.haynhanh.troll.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.haynhanh.troll.fragment.GifFragment;
import com.haynhanh.troll.fragment.IframeFragment;
import com.haynhanh.troll.fragment.PhotoFragment;
import com.haynhanh.troll.fragment.YoutubeFragment;

public class PagerAdapter  extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position){
            case 0:
                frag = new GifFragment();
                break;
            case 1:
                frag = new IframeFragment();
                break;
            case 2:
                frag = new PhotoFragment();
                break;
            case 3:
                frag = new YoutubeFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Gif";
                break;
            case 1:
                title = "iFrame";
                break;
            case 2:
                title = "Photo";
                break;
            case 3:
                title = "Youtube";
                break;
        }

        return title;
    }

}
