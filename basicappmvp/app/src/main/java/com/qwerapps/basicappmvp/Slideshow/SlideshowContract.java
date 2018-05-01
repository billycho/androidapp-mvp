package com.qwerapps.basicappmvp.Slideshow;

import com.qwerapps.basicappmvp.data.MData;

import java.util.List;

/**
 * Created by IT02106 on 30/04/2018.
 */

public interface SlideshowContract {
    interface View
    {
        void showMDatas(List<MData> mDatas);

        void nextData();
        void previousData();
        void shareData();
        void copyData();
    }

    interface Presenter
    {
        void loadMData(int categoryId);
    }
}
