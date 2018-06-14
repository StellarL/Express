package com.example.packageapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by 李馨 on 2018/6/9.
 */

public class AllOrdersAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Order> arrayList;
    private String username;

    public AllOrdersAdapter(Context context, ArrayList<Order> arrayList,String username) {
        this.context = context;
        this.arrayList = arrayList;
        this.username = username;
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
        final View view;
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
        String endPlace = order.getEndPlace();
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

                 final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final View view1 = View.inflate(context,R.layout.layout_dialog,null);
                builder.setView(view1);
                builder.setTitle("请填写你的联系方式");
                final Dialog dialog=builder.create();
                dialog.show();
                /*builder.setMessage("您确定要接下此订单吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        context.startActivity(new Intent(context,num6Activity.class).putExtra("id",order.get_id()).putExtra("username",username));

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });*/
                Button buttonCon = view1.findViewById(R.id.button5);
                buttonCon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText receive_name,receive_phone;
                        receive_name = view1.findViewById(R.id.editText4);
                        receive_phone = view1.findViewById(R.id.editText5);
                        String name = receive_name.getText().toString();
                        String phone = receive_phone.getText().toString();
                        context.startActivity(new Intent(context,num6Activity.class).putExtra("id",order.get_id()).putExtra("username",username).putExtra("receive_name",name).putExtra("receive_phone",phone));

                    }
                });
                Button buttonCancel = view1.findViewById(R.id.button4);
                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


            }
        });

        return view;
    }

    class MyHolder{
        TextView startPlace,endPlace,type,payment;
        Button button;
    }
}
