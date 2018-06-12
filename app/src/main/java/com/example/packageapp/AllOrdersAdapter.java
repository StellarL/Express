package com.example.packageapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 李馨 on 2018/6/9.
 */

public class AllOrdersAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Order> arrayList;

    public AllOrdersAdapter(Context context, ArrayList<Order> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        MyHolder myHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.layout_all_orders,null);
            myHolder = new MyHolder();
            myHolder.startPlace = view.findViewById(R.id.startPlace);
            myHolder.endPlace = view.findViewById(R.id.endPlace);
            myHolder.payment = view.findViewById(R.id.payment);
            myHolder.type = view.findViewById(R.id.type);
            myHolder.button = view.findViewById(R.id.receiveBtn);
            view.setTag(myHolder);
        }else{
            view = convertView;
            myHolder = (MyHolder) view.getTag();
        }

        final Order order = (Order)getItem(position);
        String startPlace = order.getStartPlace();
        String endPlace = order.getStartPlace();
        int payment = order.getPayment();
        String type=  "";
        switch (order.getType()){
            case 1:
                type = "小";
                break;
            case 2:
                type = "中";
                break;
            case 3:
                type = "大";
                break;
        }
        myHolder.startPlace.setText(startPlace);
        myHolder.endPlace.setText(endPlace);
        myHolder.payment.setText(payment+"");
        myHolder.type.setText(type);
        //点击接单按钮
        myHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("setOnClickListener", "onClick:  myHolder.button.setOnClickListener" );
//                AlertDialog.Builder builder=new AlertDialog.Builder(context);
//                builder.setIcon(android.R.drawable.sym_def_app_icon).setTitle("确认信息").setMessage(
//                        "您确定接下此订单吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent intent=new Intent(context,num6Activity.class);
//                        intent.putExtra("id",order.get_id());
//                        context.startActivity(intent);
//                    }
//                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
                Intent intent = new Intent(context,num6Activity.class);
                intent.putExtra("id",order.get_id());
                context.startActivity(intent);
            }
        });
        return view;
    }

    class MyHolder{
        TextView startPlace,endPlace,type,payment;
        Button button;
    }
}
