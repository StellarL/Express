package com.example.packageapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * UserDBHelper
 * 创建User表
 */

public class UserDBHelper extends SQLiteOpenHelper{


    public UserDBHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_sql = "create table user(_id integer primary key autoincrement, username text ,password text,relname text,id_card text,id_img text)";
        db.execSQL(create_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
