package com.example.packageapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class num2Activity extends AppCompatActivity {

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
                startActivity(intent);
            }
        });

    }
}
