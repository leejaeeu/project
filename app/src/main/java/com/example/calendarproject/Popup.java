package com.example.calendarproject;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;


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
        final int mitcount=0;
        final int meetcount=0;
        final int workcount=0;
        final Button mit = (Button)findViewById(R.id.pop_mitting);
        final Button meet = (Button)findViewById(R.id.pop_promiss);
        final Button work = (Button)findViewById(R.id.pop_work);
        Button save = (Button) findViewById(R.id.pop_save);
        Button exit = (Button) findViewById(R.id.pop_exit);
        save.setOnClickListener(btn);
        exit.setOnClickListener(btn);
        OnClickListener btn2=new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == mit) {
                    finish();
                } else if (v == meet) {
                    finish();

                } else if (v == work) {
                    finish();

                }
            }
        };
        mit.setOnClickListener(btn2);
        meet.setOnClickListener(btn2);
        work.setOnClickListener(btn2);
    }

    //    save 와 exit 눌렀을시 동작
    View.OnClickListener btn= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.pop_exit:
                        finish();
                    case R.id.pop_save:
                        finish();
                }
            }

    };


}




