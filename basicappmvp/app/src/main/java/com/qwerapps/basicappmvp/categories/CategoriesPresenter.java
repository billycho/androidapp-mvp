package com.qwerapps.basicappmvp.categories;

import com.j256.ormlite.dao.Dao;
import com.qwerapps.basicappmvp.data.Categories;
import com.qwerapps.basicappmvp.data.DatabaseHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IT02106 on 29/04/2018.
 */

public class CategoriesPresenter implements CategoriesContract.Presenter {
    private DatabaseHelper databaseHelper;
    private Dao<Categories, Integer> categoriesDao;
    private List<Categories> categoriesList = null;

    private final CategoriesContract.View categoriesView;

    public CategoriesPresenter(DatabaseHelper databaseHelper, CategoriesContract.View categoriesView) {
        this.databaseHelper = databaseHelper;
        this.categoriesView = categoriesView;

        try {
            this.databaseHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            categoriesDao = this.databaseHelper.getCategoryDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadCategories() {

        try {
            categoriesList = categoriesDao.queryForAll();
            categoriesView.showCategories(categoriesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
