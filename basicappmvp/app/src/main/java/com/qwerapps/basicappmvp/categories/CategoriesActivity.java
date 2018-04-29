package com.qwerapps.basicappmvp.categories;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.qwerapps.basicappmvp.R;
import com.qwerapps.basicappmvp.data.Categories;
import com.qwerapps.basicappmvp.data.DatabaseHelper;
import com.qwerapps.basicappmvp.data.MData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesActivity extends AppCompatActivity {

    RecyclerView.LayoutManager mLayoutManager;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    CategoriesAdapter mAdapter;

    private DatabaseHelper databaseHelper;
    private Dao<Categories, Integer> categoryDao;
    private Dao<MData, Integer> mDataDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        databaseHelper = new DatabaseHelper(this);
        try {
            categoryDao = databaseHelper.getCategoryDao();
            mDataDao = databaseHelper.getMDataDao();

//            Categories newCategory = new Categories("category #1");
//            categoryDao.create(newCategory);
//
//            MData newMData = new MData("value #1",newCategory);
//            mDataDao.create(newMData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<MData> mdatas = new ArrayList<MData>();
        for(int i = 1;i<=10;i++)
        {
            MData data1 = new MData();
            data1.setId(i);
            data1.setmData("Categories " + i);

            mdatas.add(data1);
        }


        for(int i = 0;i<mdatas.size();i++)
        {
            Log.d("asda", mdatas.get(i).getmData());
        }

        mAdapter = new CategoriesAdapter(mdatas, this);
        mRecyclerView.setAdapter(mAdapter);


    }
}
