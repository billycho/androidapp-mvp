package com.qwerapps.basicappmvp.data;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by IT02106 on 21/04/2018.
 */

public class MData implements Serializable{
    private static final long serialVersionUID = -222864131214757024L;

    @DatabaseField(generatedId = true, columnName = "data_id")
    private int mDataId;

    @DatabaseField(columnName = "data_value")
    private String mData;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    public Categories categories;

    public String getmData()
    {
        return mData;
    }

    public MData()
    {

    }

    public MData(final String value, Categories categories)
    {
        this.mData = value;
        this.categories = categories;

    }
    public int getId()
    {
        return mDataId;
    }

    public void setmData(String mData)
    {
        this.mData = mData;
    }

    public void setId(int id)
    {
        this.mDataId = id;
    }
}
