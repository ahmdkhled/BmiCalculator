package com.ahmedkhaled.bmicalculator.backend;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ahmedkhaled.bmicalculator.model.history;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Ahmed Khaled on 9/27/2017.
 */

public class HistoryDatabase extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME="bmi";
    private static final String  create_sql="create table history (id INTEGER PRIMARY KEY ,weight VARCHAR(255),height VARCHAR(255)," +
                                                "result VARCHAR(255),date VARCHAR(255))";
    private static final String drop_sql="drop table if exists history";


    public HistoryDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(drop_sql);
        onCreate(sqLiteDatabase);
    }

    public void insertData(String weight,String height,String result){
        SQLiteDatabase db=getWritableDatabase();
        String insert_sql ="insert into history (weight,height,result,date) values " +
                "('"+weight+"','"+height+"','"+result+"','"+getDate()+"')";
        db.execSQL(insert_sql);

    }

    public ArrayList<history> getData(){
        ArrayList<history> list=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from history",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            list.add(new history(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4)));
            cursor.moveToNext();
        }
        return list;
    }

    public void delete(int id){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("delete from history where id = "+id);
    }

    String getDate(){
        int mYear=  Calendar.getInstance().get(Calendar.YEAR);
        int mMonth=Calendar.getInstance().get(Calendar.MONTH);
        int day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        return day+"."+mMonth+"."+mYear;
    }

}
