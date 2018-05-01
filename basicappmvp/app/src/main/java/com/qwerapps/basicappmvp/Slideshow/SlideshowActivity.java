package com.qwerapps.basicappmvp.Slideshow;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qwerapps.basicappmvp.R;
import com.qwerapps.basicappmvp.data.Categories;
import com.qwerapps.basicappmvp.data.DatabaseHelper;
import com.qwerapps.basicappmvp.data.MData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SlideshowActivity extends AppCompatActivity implements View.OnClickListener,SlideshowContract.View {

    private MDataPageAdapter mAdapter;

    @BindView(R.id.pager)
    ViewPager mPager;

    @BindView(R.id.next)
    Button nextPage;

    @BindView(R.id.copy)
    Button copy;

    @BindView(R.id.share)
    Button share;

    @BindView(R.id.previous)
    Button previousPage;

    private DatabaseHelper databaseHelper;
    private SlideshowPresenter slideshowPresenter;

    private int position, categoryId;

    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        ButterKnife.bind(this);

        copy.setOnClickListener(this);
        share.setOnClickListener(this);
        nextPage.setOnClickListener(this);
        previousPage.setOnClickListener(this);
        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        Intent intent = getIntent();
        categoryId = intent.getIntExtra("categoryId",0);
        position = intent.getIntExtra("position",0);
        databaseHelper = new DatabaseHelper(this);
        slideshowPresenter = new SlideshowPresenter(databaseHelper,this);
        slideshowPresenter.loadMData(categoryId);

    }

    @Override
    public void showMDatas(List<MData> mDatas) {
        mAdapter = new MDataPageAdapter(getSupportFragmentManager(), mDatas);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(position);
    }

    @Override
    public void nextData() {
        mPager.setCurrentItem(mPager.getCurrentItem()+1);
    }

    @Override
    public void previousData() {
        mPager.setCurrentItem(mPager.getCurrentItem()-1);
    }

    @Override
    public void shareData() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");

        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        String content;

        TextView dataContent = (TextView) findViewById(R.id.view_pager_text);
        content = dataContent.getText().toString();

        share.putExtra(Intent.EXTRA_TEXT,content);

        startActivity(Intent.createChooser(share,"Share this awesome content!"));
    }

    @Override
    public void copyData() {
        myClip = ClipData.newPlainText("text",Html.fromHtml(mAdapter.getMData(mPager.getCurrentItem())) );
        myClipboard.setPrimaryClip(myClip);
        Toast.makeText(this, "Awesome content copied", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.next:nextData();break;
            case R.id.previous:previousData();break;
            case R.id.share:shareData();break;
            case R.id.copy:copyData();break;
        }
    }
}
