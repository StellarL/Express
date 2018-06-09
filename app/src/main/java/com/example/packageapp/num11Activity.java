package com.example.packageapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class num11Activity extends AppCompatActivity {

    private Spinner spinner,startPlace,endPlace;
    private EditText name,payment,phoneNum,info;
    private Button btnCancel,btnSubmit;
    private OrderDBHelper orderDBHelper;
    private SQLiteDatabase sqLiteDatabase;
    //用户id
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num11);

        Intent intent = getIntent();
        //获取用户id
        id = intent.getIntExtra("id",1);

        name=findViewById(R.id.name);
        payment=findViewById(R.id.editText2);
        phoneNum=findViewById(R.id.editText3);
        btnCancel=findViewById(R.id.button3);
        btnSubmit=findViewById(R.id.button2);
        info = findViewById(R.id.editText4);


        /*此段为类型下拉框*/
        spinner=findViewById(R.id.spinner);
        String[] arr=new String[]{
                "大","中","小"
        };
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(num11Activity.this,android.R.layout.simple_list_item_multiple_choice,arr);
        spinner.setAdapter(arrayAdapter);

        /*此段为起点下拉框*/
        startPlace=findViewById(R.id.startPlace);
        String[] arr2=new String[]{
                "D1","D2","D3","D5","D6","D7","D8","D9","D10","D11","C1","C2","C7","A1","A2","A3","A4","A5","A6","A7","A8"
        };
        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(num11Activity.this,android.R.layout.simple_list_item_multiple_choice,arr2);
        startPlace.setAdapter(arrayAdapter2);

        /*此段为终点下拉框*/
        endPlace=findViewById(R.id.endPlace);
        endPlace.setAdapter(arrayAdapter2);

        //开启数据库
        orderDBHelper = new OrderDBHelper(num11Activity.this,"express.db",null,1);
        sqLiteDatabase = orderDBHelper.getReadableDatabase();

        Log.e("beforeClick", "onCreate: beforeClick" );
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //下单人id 从登陆传进来

                //下单人即收件人姓名
                String package_receive = name.getText().toString();
                //电话
                String package_phone = phoneNum.getText().toString();
                //赏金
                int package_payment = Integer.parseInt(payment.getText().toString());
                //类型
                int  package_type = 0;
                switch (spinner.getSelectedItem().toString()){
                    case "大":
                        package_type = 3;
                        break;
                    case "中":
                        package_type = 2;
                        break;
                    case "小":
                        package_type = 1;
                        break;
                }
                //起点
                String package_startPlace = startPlace.getSelectedItem().toString();
                //终点
                String package_endPlace = endPlace.getSelectedItem().toString();
                //信息
                String package_info = info.getText().toString();

                //存入数据库
                String sql = "insert into order1(_id,order_id,order_name,order_phone,receive_id,receive_name,receive_phone,start_place,end_place,payment,type,state,info)" +
                        "values ( null, ? , ? ,?,null,null,null,?,?,?,?,0,?)";
                sqLiteDatabase.execSQL(sql,new Object[]{id,package_receive,package_phone,package_startPlace,package_endPlace,package_payment,package_type,package_info});
            }
        });
    }

}
