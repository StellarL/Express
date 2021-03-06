package com.example.packageapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.prefs.PreferenceChangeEvent;

public class num1Activity extends AppCompatActivity {

    /*页面登录注册按钮，用户名密码框，记住密码自动登录勾选框*/
    private Button btnLogin, btnRegist;
    private EditText userName, psd;
    private CheckBox rmpsd, autolgn;
    private SQLiteDatabase sqLiteDatabase;
    private int RequestCode = 1;
    private UserDBHelper userDBHelper;

    //存储密码
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        userName.setText(data.getStringExtra("name"));
        psd.setText(data.getStringExtra("psd"));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num1);

        btnLogin = findViewById(R.id.login);
        btnRegist = findViewById(R.id.regist);
        userName = findViewById(R.id.userName);
        psd = findViewById(R.id.password);
        rmpsd = findViewById(R.id.checkBox);

        userDBHelper = new UserDBHelper(num1Activity.this, "expressUserUp.db", null, 1);
        sqLiteDatabase = userDBHelper.getReadableDatabase();

        //记住密码
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = pref.getBoolean("remember_password", false);

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(num1Activity.this, num1eActivity.class);
                startActivityForResult(intent, RequestCode);
            }
        });

            //将账号密码设置到文本框中
            String name = pref.getString("name", "");
            String psdstr = pref.getString("psd", "");
        if (isRemember) {
            userName.setText(name);
            psd.setText(psdstr);
            rmpsd.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入框中的用户名和密码
                String name_input = userName.getText().toString();
                String pass_input = psd.getText().toString();
                if (name_input.length() == 0 || name_input.equals("")) {
//                    Toast.makeText(num1Activity.this, "请输入用户名", Toast.LENGTH_SHORT);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(num1Activity.this);
                    alertDialog.setTitle("提示").setMessage("请输入用户名");
                    alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.create().show();
                } else {
                    //检查 密码
//                sqLiteDatabase = openOrCreateDatabase("expressUserUp.db",MODE_PRIVATE,null);
                    String pwdSql = "select password from user where username = ?";
                    Cursor c = sqLiteDatabase.rawQuery(pwdSql, new String[]{name_input});
                    String pass_true = "";
                    while (c.moveToNext()) {
                        pass_true = c.getString(c.getColumnIndex("password"));
                    }

                    Log.e("pass_true", "onClick: pass_true.length():" + pass_true.length());

                    //查询到的密码为空或不对应，用户名或用户名错误

                    if (pass_true.length() == 0 || pass_true.equals("") || !pass_input.equals(pass_true)) {
//                    Toast.makeText(num1Activity.this, "用户名或密码不正确", Toast.LENGTH_SHORT);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(num1Activity.this);
                        alertDialog.setTitle("提示").setMessage("用户名或密码错误");
                        alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alertDialog.create().show();
                    } else {
                        //检查复选框是否选中
                        editor = pref.edit();
                        editor.putBoolean("remember_password", true);
                        editor.putString("name", name_input);
                        editor.putString("password", pass_input);
                        editor.apply();

                        //如果密码正确，跳转到num3Activity
                        Intent intent = new Intent(num1Activity.this, num3Activity.class);
                        intent.putExtra("username", name_input);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });


        }
}
