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

            @Override

            public void onClick(View v) {
                if (v == mit) {
                    mitcount++;
                    if(mitcount%2==1){
                        a=0;
                        Intent intent = getIntent();
                        String date = intent.getStringExtra("year");
                        Toast.makeText(Popup.this, "."+date, Toast.LENGTH_SHORT).show();
                    }else if(mitcount%2==0){
                        a=1;

//                        Toast.makeText(Popup.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else if (v == meet) {
                    meetcount++;
                    if(meetcount%2==1){
                        b=0;

//                        Toast.makeText(Popup.this, "선택되었습니다.", Toast.LENGTH_SHORT).show();
                    }else if(meetcount%2==0){
                        b=1;

//                        Toast.makeText(Popup.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else if (v == work) {
                    workcount++;
                    if(workcount%2==1){
                        c=0;

//                        Toast.makeText(Popup.this, "선택되었습니다.", Toast.LENGTH_SHORT).show();
                    }else if(workcount%2==0){
                        c=1;

//                        Toast.makeText(Popup.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                }else if (v==save){
                    Intent intent = new Intent();
                    Bundle bundle =new Bundle();
//                    bundle.putInt("posi",);
                    bundle.putInt("a",a);
                    bundle.putInt("b", b);
                    bundle.putInt("c", c);
                    intent.putExtras(bundle);
                    setResult(Activity.RESULT_OK, intent);
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

    //    save 와 exit 눌렀을시 동작


}




