package com.example.packageapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 李馨 on 2018/6/13.
 */

public class DBUtil {

    private SQLiteDatabase sqLiteDatabase;

    public DBUtil(Context context,String dbname){
        sqLiteDatabase = context.openOrCreateDatabase(dbname,Context.MODE_PRIVATE,null);
    }



    //下单事件
    public void insert(String username, String package_receive, String package_phone, String package_startPlace, String package_endPlace, int package_payment, int package_type, String package_info) {
        String sql = "insert into order2(_id,order_id,order_name,order_phone,receive_id,receive_name,receive_phone,start_place,end_place,payment,type,state,info,finish)" +
                "values ( null, ? , ? ,?,null,null,null,?,?,?,?,0,?,?)";
        sqLiteDatabase.execSQL(sql,new Object[]{username,package_receive,package_phone,package_startPlace,package_endPlace,package_payment,package_type,package_info,"未完成"});
    }

    //查询未接单的所有订单
    public ArrayList<Order> queryAllState0(){
        ArrayList<Order> arrayList ;
        String strSelect = "select * from order2 where state = 0";
        Cursor c = sqLiteDatabase.rawQuery(strSelect,null);
        arrayList = new ArrayList<Order>();
        while (c.moveToNext()) {
            //订单id
            int _id = c.getInt(c.getColumnIndex("_id"));
            //下单人id 即用户名 即电话号码
//            int orderId = c.getInt(c.getColumnIndex("order_id"));
            String startPlace = c.getString(c.getColumnIndex("start_place"));
            String endPlace = c.getString(c.getColumnIndex("end_place"));
            int payment = c.getInt(c.getColumnIndex("payment"));
            int type = c.getInt(c.getColumnIndex("type"));
            Order order1 = new Order(_id, startPlace, endPlace, payment, type);
//            Log.e("order", "initData: " + order.toString());
            arrayList.add(order1);
        }
        return arrayList;
    }

    //根据订单id 查询 Order
    public Order queryById(int id) {
        String sql = "select * from order2 where _id = ? ";
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
            //是否完成
            String finish = c.getString(c.getColumnIndex("finish"));
            order = new Order(_id,order_id,order_name,order_phone,receive_id,receive_name,receive_phone,start_place,end_place,payment,type,state,info,finish);
        }
        return order;
    }

    /**
     * 用户点击接单 更新数据库 state = 1 receiveID receiveName receivePhone
     */
    public void updateStete(int id,String username) {
        String sql = "update order2 set receive_id=?,state=1 where _id=?";
        sqLiteDatabase.execSQL(sql,new String[]{username,String.valueOf(id)});
        Log.e("updateStete", "updateStete: success" );
    }

    //查询我的下单
    public ArrayList<Order> selectMyOrder(int id){
        ArrayList<Order> arrayList = new ArrayList<>();
        String sql = "select * from order2 where order_id = ?";
        Cursor c = sqLiteDatabase.rawQuery(sql, new String[]{id+""});

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
            //是否完成
            String finish = c.getString(c.getColumnIndex("finish"));

            Order order = new Order(_id,order_id,order_name,order_phone,receive_id,receive_name,receive_phone,start_place,end_place,payment,type,state,info,finish);
            arrayList.add(order);
        }
        return arrayList;
    }

    //查询我的接单
    public ArrayList<Order> selectMyReceive(int id) {
        Log.e("id", "selectMyReceive: id:"+id );
        ArrayList<Order> arrayList = new ArrayList<>();
        String sql = "select * from order2 where receive_id = ?";
        Cursor c = sqLiteDatabase.rawQuery(sql, new String[]{id + ""});

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
            //是否完成
            String finish = c.getString(c.getColumnIndex("finish"));

            Order order = new Order(_id, order_id, order_name, order_phone, receive_id, receive_name, receive_phone, start_place, end_place, payment, type, state, info, finish);
            Log.e("selectMyReceive", "selectMyReceive: " +order.toString());
            arrayList.add(order);
        }
        return arrayList;
    }
    //更新订单已完成
    public void updateFinish(int id){
        String sql = "update order2 set finish =? where _id =?";
        sqLiteDatabase.execSQL(sql,new String[]{"已完成",id+""});
    }



}
