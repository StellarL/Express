package com.example.packageapp;

import android.content.Context;
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

//        HashMap map = (HashMap) getItem(position);
//        String startPlace = map.get("startPlace").toString();
//        String endPlace = map.get("endPlace").toString();
//        String payment = map.get("payment").toString();
//        String type = map.get("type").toString();
        Order order = (Order)getItem(position);
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
                //todo
            }
        });
        return view;
    }

    class MyHolder{
        TextView startPlace,endPlace,type,payment;
        Button button;
    }
}
