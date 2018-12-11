package com.example.vulcan.demo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //加载布局文件
             test();
    }

    private  void test(){
        //查找到文本框
        editText=(EditText) MainActivity.this.findViewById(R.id.editText);
        //找到按钮
        Button btn=(Button)this.findViewById(R.id.btn);
        //方式1.按钮的点击事件 采用了匿名内部类创建的
       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callphone();
            }
        });*/
       //方式2.通常用的点击   去实现点击事件
       btn.setOnClickListener(this);
    }
     private void callphone(){
         //获取输入的电话号码 trim()去空格
         String  number=editText.getText().toString().trim();
         if(TextUtils.isEmpty(number)){
             Toast.makeText(MainActivity.this,"号码不能为空",Toast.LENGTH_LONG).show();
             return;
         }
         //创建大电话的意图
         Intent intent=new Intent();
         //设置拨打电话的动作
         intent.setAction(Intent.ACTION_CALL);
         //设置拨打电话的号码
         intent.setData(Uri.parse("tel:"+number));
         //开启打电话的意图
         startActivity(intent);
     }

     //判断点击
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn:
               callphone();
               break;
       }
    }
}
