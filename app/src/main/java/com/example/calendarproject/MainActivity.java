package com.example.calendarproject;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private final String[] WEEK = {"일", "월", "화", "수", "목", "금", "토"};
    private  ArrayList<Day> dayEventList;
    private TextView tvDate;
    private GridAdapter gridAdapter;
    private ArrayList<String> dayList;
    private GridView gridView;
    private Calendar mCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDate = (TextView) findViewById(R.id.tv_date);
        gridView = (GridView) findViewById(R.id.gridview);

        // 오늘에 날짜를 세팅 해준다.
        long now = System.currentTimeMillis();
        final Date date = new Date(now);

        //연,월,일을 따로 저장
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        //현재 날짜 텍스트뷰에 뿌려줌
        tvDate.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));

        //gridview 요일 표시
        dayList = new ArrayList<String>();
        for(String week : WEEK){
            dayList.add(week);
        }
        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal = Calendar.getInstance();
        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);
        //1일 - 요일 매칭 시키기 위해 공백 add
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        for (int i = 1; i < dayNum; i++) {
            dayList.add("");
        }

        setCalendarDate(mCal.get(Calendar.MONTH) + 1);

        dayEventList = new ArrayList<>();
        for(int i=1; i<=31; i++){
            dayEventList.add(new Day(i, Integer.parseInt(curMonthFormat.format(date)) - 1, Integer.parseInt(curYearFormat.format(date))));
        }



        gridAdapter = new GridAdapter(getApplicationContext(), dayList, dayEventList);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Popup.class);
                intent.putExtra("position", position);
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                startActivityForResult(intent, 1);
            }
        });
    }


    /**
     * 해당 월에 표시할 일 수 구함
     */


    private void setCalendarDate(int month) {
        mCal.set(Calendar.MONTH, month - 1);

        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            dayList.add("" + (i + 1));
        }
    }

   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                int position = data.getIntExtra("popo", 0);
                int a = data.getIntExtra("a", 0);
                int b = data.getIntExtra("b", 0);
                int c = data.getIntExtra("c", 0);

                Day dayEvent = dayEventList.get(Integer.parseInt(dayList.get(position)) - 1);

                dayEvent.setMeet(a);
                dayEvent.setPro(b);
                dayEvent.setWork(c);
                gridAdapter.notifyDataSetChanged();
            }
        }

    }
}





