package com.example.packageapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class num2Activity extends AppCompatActivity {

    private int TAKE_PHOTO_WITH_DATA=1;
    private Button btnSubmit;
    private EditText name,userId;
    private ImageView cardUp,cardBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num2);

        btnSubmit=findViewById(R.id.submit);
        name=findViewById(R.id.name);
        userId=findViewById(R.id.Id);
        cardUp=findViewById(R.id.imageView);
        cardBack=findViewById(R.id.imageView2);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=getIntent();
                setResult(0,intent);
                finish();
            }
        });

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final Bitmap photo = data.getParcelableExtra("data");
        //不做处理直接粘贴
        cardUp.setImageBitmap(photo);
    }

}
