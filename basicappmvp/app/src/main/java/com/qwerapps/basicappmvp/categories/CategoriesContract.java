package com.qwerapps.basicappmvp.categories;

import com.qwerapps.basicappmvp.data.Categories;

import java.util.List;

/**
 * Created by IT02106 on 29/04/2018.
 */

public interface CategoriesContract {
    interface View
    {
        void showCategories(List<Categories> categories);

        void showMData(int categoryId);
    }

    interface Presenter
    {
        void loadCategories();
    }
}
