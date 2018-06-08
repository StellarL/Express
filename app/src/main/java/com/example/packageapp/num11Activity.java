package com.example.packageapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class num11Activity extends AppCompatActivity {

    private Spinner spinner,startPlace,endPlace;
    private EditText name,payment,phoneNum;
    private Button btnCancel,btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num11);

        name=findViewById(R.id.name);
        payment=findViewById(R.id.editText2);
        phoneNum=findViewById(R.id.editText3);
        btnCancel=findViewById(R.id.button3);
        btnSubmit=findViewById(R.id.button2);


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
        spinner.setAdapter(arrayAdapter2);

        /*此段为终点下拉框*/
        endPlace=findViewById(R.id.endPlace);
        spinner.setAdapter(arrayAdapter2);
    }

}
