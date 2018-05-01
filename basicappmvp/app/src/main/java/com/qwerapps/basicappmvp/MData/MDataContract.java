package com.qwerapps.basicappmvp.MData;

import com.qwerapps.basicappmvp.data.Categories;
import com.qwerapps.basicappmvp.data.MData;

import java.util.List;

/**
 * Created by IT02106 on 30/04/2018.
 */

public interface MDataContract {
    interface View
    {
        void showMDatas(List<MData> mData);

        void showMData(int position, int categoryId);


    }

    interface Presenter
    {
        void loadMData(int categoryId);
    }
}
