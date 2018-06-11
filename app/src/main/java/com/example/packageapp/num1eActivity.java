package com.example.packageapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class num1eActivity extends AppCompatActivity {

    //注册按钮
    private Button btnregist;
    //手机号，密码，确认密码
    private EditText phoneNum,psd,conPsd;
    //数据库
//    private SQLiteDatabase sqLiteDatabase;

    private int ResultCode=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num1e);

        phoneNum=findViewById(R.id.phone);
        psd=findViewById(R.id.psd);
        conPsd=findViewById(R.id.conpsd);
        btnregist=findViewById(R.id.regist);

//        sqLiteDatabase = openOrCreateDatabase("express.db",MODE_PRIVATE,null);

        btnregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=phoneNum.getText().toString();
                String pwd=psd.getText().toString();
                String conpwd=conPsd.getText().toString();
                if(pwd.equals(conpwd)) {
                    //存入数据库
//                    String insSq;
//                    Intent intent = getIntent();
//                    intent.putExtra("name", name);
//                    intent.putExtra("psd", pwd);
//                    setResult(ResultCode, intent);
                    Intent intent = new Intent(num1eActivity.this,num2Activity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("psd",pwd);
                    startActivity(intent);
                    finish();
                } else {
                    /*密码与确认密码不一致*/
                    Toast.makeText(num1eActivity.this,"密码与确认密码不一致",Toast.LENGTH_SHORT);
                }
            }
        });
    }
}
