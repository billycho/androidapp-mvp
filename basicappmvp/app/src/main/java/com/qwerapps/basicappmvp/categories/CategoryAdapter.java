package com.qwerapps.basicappmvp.categories;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dynamitechetan.flowinggradient.FlowingGradientClass;
import com.qwerapps.basicappmvp.MData.MDataAdapter;
import com.qwerapps.basicappmvp.R;
import com.qwerapps.basicappmvp.data.Categories;
import com.qwerapps.basicappmvp.data.MData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT02106 on 29/04/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<Categories> mItems;
    CategoriesContract.View  activity;
    CategoriesPresenter categoriesPresenter;

    CategoryAdapter (List<Categories> categories, CategoriesContract.View activity,CategoriesPresenter categoriesPresenter)
    {
        super();
        mItems = categories;
        this.activity = activity;
        this.categoriesPresenter = categoriesPresenter;

    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup, false);
        CategoryAdapter.ViewHolder viewHolder = new CategoryAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder viewHolder, int i)
    {
        final Categories category = mItems.get(i);
        final int x = i;
        viewHolder.dataText.setText(Html.fromHtml(category.getCategoryName()));



        FlowingGradientClass grad = new FlowingGradientClass();
        grad.setBackgroundResource(R.drawable.translate)
                .onRelativeLayout( viewHolder.rv)
                .setTransitionDuration(4000)
                .start();

        viewHolder.setClickListener(new CategoryAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick)
                {
                    Log.d("asdaxxx","Long Click");
                }
                else
                {
                    activity.showMData(category.getCategoryId());

                    Log.d("asdaxxx","Click");

                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        @BindView(R.id.cv)
        public CardView cv;

        @BindView(R.id.mdata)
        public TextView dataText;

        @BindView(R.id.gradient_layout)
        public RelativeLayout rv;

        public CategoryAdapter.ItemClickListener clickListener;

        public ViewHolder(View itemView)
        {

            super(itemView);
            ButterKnife.bind(this,itemView);
            cv.setTag(itemView);
            cv.setOnClickListener(this);
            cv.setOnClickListener(this);
        }

        public void setClickListener(CategoryAdapter.ItemClickListener itemClickListener)
        {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view)
        {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view)
        {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }

    public interface ItemClickListener
    {
        void onClick(View view, int position, boolean isLongClick);
    }
}
