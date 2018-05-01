package com.qwerapps.basicappmvp.Slideshow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qwerapps.basicappmvp.data.MData;

import java.util.List;

/**
 * Created by IT02106 on 30/04/2018.
 */

public class MDataPageAdapter extends FragmentPagerAdapter {
    private List<MData> mDataList;
    private int totalPages;

    public MDataPageAdapter (FragmentManager fm, List<MData> mDataList) {
        super(fm);
        this.mDataList = mDataList;
        this.totalPages = this.mDataList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return newInstance(mDataList.get(position).getmData());
    }

    public String getMData(int position)
    {
        return mDataList.get(position).getmData();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    public static final MDataFragment newInstance(String mData)
    {
        MDataFragment fragment = new MDataFragment();
        Bundle bundle = new Bundle(2);
        bundle.putString("data",mData);
        fragment.setArguments(bundle);

        return fragment;
    }
}
