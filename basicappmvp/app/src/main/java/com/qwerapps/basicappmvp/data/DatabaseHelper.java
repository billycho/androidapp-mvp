package com.qwerapps.basicappmvp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.qwerapps.basicappmvp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by IT02106 on 29/04/2018.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static String DB_PATH = "/data/data/com.qwerapps.basicappmvp/databases/";
    private static final String DATABASE_NAME = "basicdatabase.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Categories,Integer> categoryDao;
    private Dao<MData, Integer> mDataDao;

    private Context myContext;
    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config );
        this.myContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
//        try
//        {
//            TableUtils.createTable(connectionSource, MData.class);
//            TableUtils.createTable(connectionSource, Categories.class);
//        }
//        catch (SQLException e)
//        {
//            Log.e(DatabaseHelper.class.getName(), "Unable to create databases",e);
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
//            try {
//                TableUtils.dropTable(connectionSource, Categories.class, true);
//                TableUtils.dropTable(connectionSource, MData.class, true);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
    }

    public Dao<Categories, Integer> getCategoryDao() throws  SQLException
    {
        if(categoryDao == null)
        {
            categoryDao = getDao(Categories.class);
        }

        return categoryDao;
    }

    public Dao<MData, Integer> getMDataDao() throws  SQLException
    {
        if(mDataDao == null)
        {
            mDataDao = getDao(MData.class);
        }

        return mDataDao;
    }

    public void createDataBase() throws IOException
    {
        boolean dbExist = checkDataBase();

        if(dbExist)
        {

        }
        else
        {
            this.getReadableDatabase();

            try
            {
                copyDataBase();
            }
            catch (IOException e)
            {
                throw new Error("Error copying database");
            }

        }

    }
    private boolean checkDataBase()
    {
//        File dbtest = new File("DB_PATH + DATABASE_NAME");
//        return dbtest.exists();

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch(SQLiteException e)
        {

        }

        if(checkDB!=null)
        {
            checkDB.close();
        }

        return checkDB != null ? true : false;

    }

    private void copyDataBase() throws IOException
    {

        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

        Log.d("asdaxxx",myInput.toString());
        String outFileName = DB_PATH + DATABASE_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while((length = myInput.read(buffer))>0)
        {
            myOutput.write(buffer, 0 , length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

}
