package com.example.vulcan.node;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_phone,et_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }
    private void test(){
        et_phone=(EditText) findViewById(R.id.et_phone);
        et_content=(EditText)findViewById(R.id.et_context);
        Button fs=(Button) findViewById(R.id.fasong);
        //点击事件
        fs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.fasong:
            String content= et_content.getText().toString().trim();
            String phone= et_phone.getText().toString().trim();
            if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(content)){
                Toast.makeText(this,"电话号码或者内容不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else {
               SmsManager smsManager= SmsManager.getDefault();
                ArrayList<String> messages=smsManager.divideMessage(content);
                for(String text:messages){
                    smsManager.sendTextMessage(phone,null,text,null,null);

                }
            }
                break;
        }
    }
}
