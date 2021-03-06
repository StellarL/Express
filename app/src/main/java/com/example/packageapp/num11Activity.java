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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class num11Activity extends AppCompatActivity {

    private Spinner spinner,startPlace,endPlace;
    private EditText name,payment,phoneNum,info;
    private Button btnCancel,btnSubmit;
    private OrderDBHelper orderDBHelper;
    private SQLiteDatabase sqLiteDatabase;
    //用户名
    private String username;
    private DBUtil dbUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num11);

        Intent intent = getIntent();
        //获取用户名
        username = intent.getStringExtra("username");

        name=findViewById(R.id.textpersonName);
        payment=findViewById(R.id.editText2);
        phoneNum=findViewById(R.id.editText3);
        btnCancel=findViewById(R.id.button3);
        btnSubmit=findViewById(R.id.button2);
        info = findViewById(R.id.textPs);

        orderDBHelper = new OrderDBHelper(num11Activity.this,"express1.db",null,1);
        sqLiteDatabase = orderDBHelper.getReadableDatabase();

        /*此段为类型下拉框*/
        spinner=findViewById(R.id.spinner);
        final String[] arr=new String[]{
                "大","中","小"
        };
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(num11Activity.this,android.R.layout.simple_spinner_item,arr);
        spinner.setAdapter(arrayAdapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String num=arr[i];
                ((TextView)view).setText(num);
                adapterView.setVisibility(View.VISIBLE);
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        /*此段为起点下拉框*/
        startPlace=findViewById(R.id.startPlace);
        final String[] arr2=new String[]{
                "D1","D2","D3","D5","D6","D7","D8","D9","D10","D11","C1","C2","C7","A1","A2","A3","A4","A5","A6","A7","A8"
        };
        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(num11Activity.this,android.R.layout.simple_spinner_item,arr2);
        startPlace.setAdapter(arrayAdapter2);

        /*startPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String num=arr2[i];
                ((TextView)view).setText(num);
                adapterView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
*/

        /*此段为终点下拉框*/
        endPlace=findViewById(R.id.endPlace);
        endPlace.setAdapter(arrayAdapter2);
        /*endPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String num=arr2[i];
                ((TextView)view).setText(num);
                adapterView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/



        Log.e("beforeClick", "onCreate: beforeClick" );

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //下单人username 从登陆传进来

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
                dbUtil = new DBUtil(num11Activity.this,"express1.db");
                dbUtil.insert(username,package_receive,package_phone,package_startPlace,package_endPlace,package_payment,package_type,package_info);
                Log.e("insert","onClick: insert success" );
                Intent intent1 = new Intent(num11Activity.this,num3Activity.class);
                startActivity(intent1);
                finish();
            }
        });
    }

}
