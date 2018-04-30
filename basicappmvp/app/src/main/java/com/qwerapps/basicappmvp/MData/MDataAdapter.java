package com.qwerapps.basicappmvp.MData;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qwerapps.basicappmvp.R;
import com.qwerapps.basicappmvp.data.MData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IT02106 on 21/04/2018.
 */

public class MDataAdapter extends RecyclerView.Adapter<MDataAdapter.ViewHolder>{

    List<MData> mItems;
    Activity activity;

    MDataContract.Presenter mDataPresenter;

    MDataAdapter(List<MData> mdatas, Activity activity, MDataContract.Presenter mDataPresenter)
    {
        super();
        mItems = mdatas;
        this.activity = activity;

        this.mDataPresenter = mDataPresenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        Log.d("asdaxxx","123");
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {
        final MData mData = mItems.get(i);
        final int x = i;
        viewHolder.dataText.setText(Html.fromHtml(mData.getmData()));

        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick)
                {
                    Log.d("asdaxxx","Long Click");
                }
                else
                {
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

    public ItemClickListener clickListener;

    public ViewHolder(View itemView)
    {

        super(itemView);
        ButterKnife.bind(this,itemView);
        cv.setTag(itemView);
        cv.setOnClickListener(this);
        cv.setOnClickListener(this);
    }

    public void setClickListener(ItemClickListener itemClickListener)
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
