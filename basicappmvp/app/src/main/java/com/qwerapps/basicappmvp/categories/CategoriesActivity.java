package com.qwerapps.basicappmvp.categories;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.RelativeLayout;

import com.dynamitechetan.flowinggradient.FlowingGradientClass;
import com.qwerapps.basicappmvp.MData.MDataActivity;
import com.qwerapps.basicappmvp.R;
import com.qwerapps.basicappmvp.data.Categories;
import com.qwerapps.basicappmvp.data.DatabaseHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesActivity extends AppCompatActivity implements CategoriesContract.View{

    RecyclerView.LayoutManager mLayoutManager;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    CategoryAdapter mAdapter;

    private DatabaseHelper databaseHelper;

    private CategoriesPresenter categoriesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.layout);
        FlowingGradientClass grad = new FlowingGradientClass();
        grad.setBackgroundResource(R.drawable.gradient)
                .onRelativeLayout(rl)
                .setTransitionDuration(4000)
                .start();

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        databaseHelper = new DatabaseHelper(this);
        categoriesPresenter = new CategoriesPresenter(databaseHelper,this);
        categoriesPresenter.loadCategories();



    }

    @Override
    public void showCategories(List<Categories> categories) {
        mAdapter = new CategoryAdapter(categories, this, categoriesPresenter);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showMData(int categoryId) {
        Intent intent  = new Intent(this,MDataActivity.class);
        intent.putExtra("categoryId",categoryId);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
