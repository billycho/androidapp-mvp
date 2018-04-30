package com.qwerapps.basicappmvp.MData;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.qwerapps.basicappmvp.categories.CategoriesContract;
import com.qwerapps.basicappmvp.data.Categories;
import com.qwerapps.basicappmvp.data.DatabaseHelper;
import com.qwerapps.basicappmvp.data.MData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IT02106 on 30/04/2018.
 */

public class MDataPresenter implements MDataContract.Presenter  {
    private DatabaseHelper databaseHelper;
    private Dao<MData, Integer> mDataDao;
    private List<MData> mDataList = null;

    private final MDataContract.View mDataView;
    public MDataPresenter(DatabaseHelper databaseHelper, MDataContract.View mDataView) {
        this.databaseHelper = databaseHelper;
        this.mDataView = mDataView;

        try {
            this.databaseHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.mDataDao = this.databaseHelper.getMDataDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadMData(int categoryId) {
        try {
              final QueryBuilder<MData, Integer> queryBuilder = mDataDao.queryBuilder();
              queryBuilder.where().eq(MData.CATEGORY_ID_FIELD,categoryId);
              final PreparedQuery<MData> preparedQuery = queryBuilder.prepare();

              mDataList = mDataDao.query(preparedQuery);

              mDataView.showMDatas(mDataList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
