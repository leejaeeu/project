package com.example.calendarproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class Popup extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.9), (int) (height * 0.85));

        final Button mit = (Button)findViewById(R.id.pop_mitting);
        final Button meet = (Button)findViewById(R.id.pop_promiss);
        final Button work = (Button)findViewById(R.id.pop_work);
        final Button save = (Button) findViewById(R.id.pop_save);
        final Button exit = (Button) findViewById(R.id.pop_exit);

        OnClickListener btn2=new OnClickListener() {
            int mitcount=1;
            int meetcount=1;
            int workcount=1;
            int a,b,c;
            int position;

            @Override

            public void onClick(View v) {
                if (v == mit) {
                    mitcount++;
                    if(mitcount%2==1){
                        a=100;
                        Intent getintent=getIntent();
                        position=getintent.getIntExtra("position",0);
//                        Toast.makeText(Popup.this, "확인"+position,Toast.LENGTH_SHORT).show();
                    }else if(mitcount%2==0){
                        a=200;
                        Intent getintent=getIntent();
                        position=getintent.getIntExtra("position",0);

                    }
                } else if (v == meet) {
                    meetcount++;
                    if(meetcount%2==1){
                        b=100;
                        Intent getintent=getIntent();
                        position=getintent.getIntExtra("position",0);
                    }else if(meetcount%2==0){
                        b=200;
                        Intent getintent=getIntent();
                        position=getintent.getIntExtra("position",0);
                    }
                } else if (v == work) {
                    workcount++;
                    if(workcount%2==1){
                        c=100;
                        Intent getintent=getIntent();
                        position=getintent.getIntExtra("position",0);
                    }else if(workcount%2==0){
                        c=200;
                        Intent getintent=getIntent();
                        position=getintent.getIntExtra("position",0);
                    }
                }else if (v==save){
                    Intent intent=new Intent();
                    intent.putExtra("popo",position);
                    intent.putExtra("a",a);
                    intent.putExtra("b",b);
                    intent.putExtra("c",c);
                    setResult(RESULT_OK, intent);
                    finish();
                }else if (v==exit){
                    finish();
                }
            }
        };
        mit.setOnClickListener(btn2);
        meet.setOnClickListener(btn2);
        work.setOnClickListener(btn2);
        save.setOnClickListener(btn2);
        exit.setOnClickListener(btn2);
    }
}




