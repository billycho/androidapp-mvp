package com.qwerapps.basicappmvp.MData;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.j256.ormlite.dao.Dao;
import com.qwerapps.basicappmvp.R;
import com.qwerapps.basicappmvp.categories.CategoriesPresenter;
import com.qwerapps.basicappmvp.categories.CategoryAdapter;
import com.qwerapps.basicappmvp.data.Categories;
import com.qwerapps.basicappmvp.data.DatabaseHelper;
import com.qwerapps.basicappmvp.data.MData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MDataActivity extends AppCompatActivity implements MDataContract.View {
    RecyclerView.LayoutManager mLayoutManager;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    MDataAdapter mAdapter;

    private DatabaseHelper databaseHelper;

    private MDataPresenter mDataPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdata);

        ButterKnife.bind(this);
        Intent intent = getIntent();

        int categoryId = intent.getIntExtra("categoryId",0);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        databaseHelper = new DatabaseHelper(this);
        mDataPresenter = new MDataPresenter(databaseHelper,this);
        mDataPresenter.loadMData(categoryId);
    }

    @Override
    public void showMDatas(List<MData> mData) {
        mAdapter = new MDataAdapter(mData, this, mDataPresenter);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showMData(int dataId) {

    }
}
