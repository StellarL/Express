package com.example.packageapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class num2Activity extends AppCompatActivity {

    private int TAKE_PHOTO_WITH_DATA=1;
    private Button btnSubmit;
    private EditText name,userId;
    private ImageView cardUp,cardBack;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num2);

        btnSubmit=findViewById(R.id.submit);
        name=findViewById(R.id.name);
        userId=findViewById(R.id.Id);
        cardUp=findViewById(R.id.imageView);
        cardBack=findViewById(R.id.imageView2);


        cardUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,TAKE_PHOTO_WITH_DATA);
            }
        });
        cardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        /**
         * 提交按钮点击 将 姓名、 身份证 、身份证照片传入数据库（此处照片未做处理）
         */
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openOrCreateDatabase("expressUserUp.db",MODE_PRIVATE,null);
                Intent intent=getIntent();
                String username = intent.getStringExtra("name");
                String relname = name.getText().toString();
                String idCard = userId.getText().toString();
//                照片未做处理
                //更新数据库
                String sql = "update user set relname =?,id_card=? where username=?";
                sqLiteDatabase.execSQL(sql,new String[]{relname,idCard,username});
                Log.e("update", "onClick: update success");
                setResult(0,intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final Bitmap photo = data.getParcelableExtra("data");
        //不做处理直接粘贴
        cardUp.setImageBitmap(photo);
    }

}
