package com.example.packageapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class num1eActivity extends AppCompatActivity {

    private String name,pwd;
    private int RequestCode=2;
    //注册按钮
    private Button btnregist;
    //手机号，密码，确认密码
    private EditText phoneNum,psd,conPsd;
    //数据库
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num1e);

        phoneNum=findViewById(R.id.phone);
        psd=findViewById(R.id.psd);
        conPsd=findViewById(R.id.conpsd);
        btnregist=findViewById(R.id.regist);

        btnregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=phoneNum.getText().toString();
                pwd=psd.getText().toString();
                String conpwd=conPsd.getText().toString();
                if(pwd.equals(conpwd)) {
//                    存入数据库
                    sqLiteDatabase = openOrCreateDatabase("expressUserUp.db",MODE_PRIVATE,null);
                    String insertSql = "insert into user(_id,username,password,relname,id_card,id_img) values (null,?,?,null,null,null)";
                    sqLiteDatabase.execSQL(insertSql,new Object[]{name,pwd});
                    Log.e("insert", "onClick: insert success");
                    Intent intent1=getIntent();
                    Intent intent=new Intent(num1eActivity.this,num2Activity.class);
                    intent.putExtra("name",name);
                    intent1.putExtra("name",name);
                    intent1.putExtra("psd",pwd);
                    startActivityForResult(intent,1);
                    setResult(0,intent1);
                    finish();
                } else {
                    /*密码与确认密码不一致*/
                    Toast.makeText(num1eActivity.this,"密码与确认密码不一致",Toast.LENGTH_SHORT);
                }
            }
        });
    }

}
