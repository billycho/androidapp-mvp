package com.qwerapps.basicappmvp.data;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by IT02106 on 29/04/2018.
 */

public class Categories implements Serializable {
    private static final long serialVersionUID = -222864131214757024L;

    @DatabaseField(generatedId =  true, columnName = "category_id")
    public int categoryId;

    @DatabaseField(columnName =  "category_name")
    public String categoryName;



    public Categories()
    {

    }

    public Categories(final String name)
    {
        this.categoryName = name;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


}
