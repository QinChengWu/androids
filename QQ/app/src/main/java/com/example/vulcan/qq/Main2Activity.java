package com.example.vulcan.qq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        test2();
    }

    private TextView show;
    private  Button btn;

    public void test2(){
        show = findViewById(R.id.showName);//获取要显示的框
        Intent intent=getIntent();
        String name=intent.getStringExtra("userName");
        show.setText("登录成功!\n欢迎"+name+"使用！！");
        btn=findViewById(R.id.TuiChu);//获取退出按钮
        btn.setOnClickListener(new View.OnClickListener() {//单击事件
            @Override
            public void onClick(View v) {
                Main2Activity.this.finish();//结束
            }
        });

    }
}
