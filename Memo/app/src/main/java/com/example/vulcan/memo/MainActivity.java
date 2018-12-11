package com.example.vulcan.memo;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private Button btn;
    private TextView tv;
    private  int count;
    private  StringBuffer sb;
    private String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    private  void test(){
        sb=new StringBuffer();
        Bundle bundle=this.getIntent().getExtras();
        if(bundle!=null){
            new AlertDialog.Builder(MainActivity.this).setIcon(null).setTitle("温馨提示")
                    .setMessage("备忘录时间到了，请注意！")
                    .setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finish();
                        }
                    })
                    .create().show();
        }
        InitViews();
    }

    private  void InitViews(){
        et=(EditText) findViewById(R.id.et);
        btn=(Button) findViewById(R.id.btn);
        tv=(TextView)findViewById(R.id.tv);

        final Calendar c=Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());//设置当前时间为c的时间
        final int hour=c.get(Calendar.HOUR_OF_DAY);
        final  int minute=c.get(Calendar.MINUTE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg=et.getText().toString().trim();
                sb.append(count++);
                sb.append("备忘录的内容为：");
                sb.append(msg);
                sb.append("\n");
                tv.setText(sb.toString().trim());
                new TimePickerDialog(
                        MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                c.setTimeInMillis(System.currentTimeMillis());
                                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                c.set(Calendar.MINUTE,minute);
                                c.set(Calendar.SECOND,0);
                                c.set(Calendar.MILLISECOND,0);
                                AlarmManager am=(AlarmManager)MainActivity.this.getSystemService(Context.ALARM_SERVICE);
                                Intent intent=new Intent(MainActivity.this,AlarmReceive.class);
                                PendingIntent pi=PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                                am.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);

                                String tempHour=(hour+"").length()>1?hour+"":"0"+hour;
                                String tempMinute=(minute+"").length()>1?minute+"":"0"+minute;
                                Toast.makeText(MainActivity.this,"设置时间为："+tempHour+":"+tempMinute,Toast.LENGTH_SHORT).show();
                            }
                        },
                        hour,
                        minute,
                        true).show();

            }
        });
    }
}
