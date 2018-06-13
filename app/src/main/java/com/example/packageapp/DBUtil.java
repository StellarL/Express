package com.example.packageapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by 李馨 on 2018/6/13.
 */

public class DBUtil {

    private SQLiteDatabase sqLiteDatabase;

    public DBUtil(Context context,String dbname){
        sqLiteDatabase = context.openOrCreateDatabase(dbname,Context.MODE_PRIVATE,null);
    }

    //根据订单id 查询 Order
    public Order queryById(int id) {
        String sql = "select * from order1 where _id = ? ";
        Cursor c = sqLiteDatabase.rawQuery(sql, new String[]{String.valueOf(id)});

        Order order = new Order();
        while (c.moveToNext()) {
            //订单id
            int _id = c.getInt(c.getColumnIndex("_id"));
            //下单人id  即 用户名 电话号码
            int order_id = c.getInt(c.getColumnIndex("order_id"));
            //下单人name 即 收货人name
            String order_name = c.getString(c.getColumnIndex("order_name"));
            //下单人phone
            String order_phone = c.getString(c.getColumnIndex("order_phone"));
            //接单人id
            int receive_id = c.getInt(c.getColumnIndex("receive_id"));
            //接单人name
            String receive_name = c.getString(c.getColumnIndex("receive_name"));
            //接单人phone
            String receive_phone = c.getString(c.getColumnIndex("receive_phone"));
            //起点
            String start_place = c.getString(c.getColumnIndex("start_place"));
            //终点
            String end_place = c.getString(c.getColumnIndex("end_place"));
            //金额
            int payment = c.getInt(c.getColumnIndex("payment"));
            //包裹类型
            int type = c.getInt(c.getColumnIndex("type"));
            //是否接单
            int state = c.getInt(c.getColumnIndex("state"));
            //取货信息
            String info = c.getString(c.getColumnIndex("info"));
            order = new Order(_id,order_id,order_name,order_phone,receive_id,receive_name,receive_phone,start_place,end_place,payment,type,state,info);
        }
        return order;
    }

    /**
     * 用户点击接单 更新数据库 state = 1 receiveID receiveName receivePhone
     */
    public void updateStete(int id,String username) {
        String sql = "update order1 set receive_id=?,state=1 where _id=?";
        sqLiteDatabase.execSQL(sql,new String[]{username,String.valueOf(id)});
        Log.e("updateStete", "updateStete: success" );
    }
}
