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

    private Context context;

    public UserDBHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists User");
        String create_sql = "create table user(_id integer primary key autoincrement, username text ,password text,id_card text,id_img text)";
        Toast.makeText(context,"create success",Toast.LENGTH_SHORT).show();
        db.execSQL(create_sql);
        Log.e("UserDBHelper", "onCreate: insert success");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertUser(String username,String password,SQLiteDatabase db) {
        String insertSql = "insert into user(_id,username,password,id_card,id_img) values (null,?,?,null,null)";
        db.execSQL(insertSql,new Object[]{username,password});
        Log.e("insertUser", "register.onClick: insert finish"  );
    }
}
