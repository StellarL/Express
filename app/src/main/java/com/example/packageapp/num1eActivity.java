package com.example.packageapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class num1eActivity extends AppCompatActivity {

    //注册按钮
    private Button btnregist;
    //手机号，密码，确认密码
    private EditText phoneNum,psd,conPsd;

    private int ResultCode=0;
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
                String name=phoneNum.getText().toString();
                String pwd=psd.getText().toString();
                String conpwd=conPsd.getText().toString();
                if(pwd.equals(conpwd)) {
                    Intent intent = getIntent();
                    intent.putExtra("name", name);
                    intent.putExtra("psd", pwd);
                    setResult(ResultCode, intent);
                    finish();
                }
                /*密码与确认密码不一致*/
            }
        });
    }
}
