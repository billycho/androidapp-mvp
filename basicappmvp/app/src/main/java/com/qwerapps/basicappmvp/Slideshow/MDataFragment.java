package com.qwerapps.basicappmvp.Slideshow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qwerapps.basicappmvp.R;

/**
 * Created by IT02106 on 30/04/2018.
 */

public class MDataFragment extends Fragment {
    private String text;
    public MDataFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        text = getArguments().getString("data");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.view_pager_text, container,false);
        TextView textView = (TextView) view.findViewById(R.id.view_pager_text);
        textView.setText(Html.fromHtml(text));

        return view;
    }

}
