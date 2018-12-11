package com.example.vulcan.qq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private EditText userName,password;
    private TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test1();
    }

    public void test1(){
        btn=findViewById(R.id.DengLu);
        userName=findViewById(R.id.qqNumber);
        password=findViewById(R.id.pwd);
        show=findViewById(R.id.show);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1=userName.getText().toString();//获取输入框的用户名
                String input2=password.getText().toString();//获取输入框的密码
                if(input1.equals("qcw")&&input2.equals("555")){
                    Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                    intent.putExtra("userName",input1);
                    intent.putExtra("pwd",input2);
                    startActivity(intent);
                    show.setText("");
                }else if(input1.equals("")||input1==null){
                    show.setText("用户名不能为空！");
                }else if(input2.equals("")||input2==null){
                    show.setText("密码不能为空！");
                }else{
                    show.setText("用户名或密码！");

                }
            }
        });
         }
}
