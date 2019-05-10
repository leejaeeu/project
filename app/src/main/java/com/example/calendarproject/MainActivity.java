package com.example.calendarproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView tvDate;
    private GridAdapter gridAdapter;
    private ArrayList<String> dayList;
    private GridView gridView;
    private Calendar mCal;
//    private Button btn_mitting=(Button)findViewById(R.id.btn_mitting);
//    private Button btn_promiss=(Button)findViewById(R.id.btn_promiss);
//    private Button btn_ex11=(Button)findViewById(R.id.btn_ex11);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDate = (TextView)findViewById(R.id.tv_date);

        gridView = (GridView)findViewById(R.id.gridview);


        // 오늘에 날짜를 세팅 해준다.

        long now = System.currentTimeMillis();

        final Date date = new Date(now);

        //연,월,일을 따로 저장

        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);

        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);

        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);




        //현재 날짜 텍스트뷰에 뿌려줌

        tvDate.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date)+ "/" + curDayFormat.format(date));



        //gridview 요일 표시

        dayList = new ArrayList<String>();

        dayList.add("일");

        dayList.add("월");

        dayList.add("화");

        dayList.add("수");

        dayList.add("목");

        dayList.add("금");

        dayList.add("토");


        mCal = Calendar.getInstance();

        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)

        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);

        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);

        //1일 - 요일 매칭 시키기 위해 공백 add

        for (int i = 1; i < dayNum; i++) {

            dayList.add("");

        }

        setCalendarDate(mCal.get(Calendar.MONTH) + 1);


        gridAdapter = new GridAdapter(getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);

        AdapterView.OnItemClickListener mClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tv = (String) parent.getAdapter().getItem(position);
                Intent intent = new Intent(MainActivity.this, Popup.class);
                startActivity(intent);
            }
        };

        gridView.setOnItemClickListener(mClickListener);

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

    /**
     * 그리드뷰 어댑터
     */

    private class GridAdapter extends BaseAdapter {


        private final List<String> list;

        private final LayoutInflater inflater;


        /**
         생성자
         */

        public GridAdapter(Context context, List<String> list) {

            this.list = list;

            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override

        public int getCount() {

            return list.size();

        }

        @Override

        public String getItem(int position) {

            return list.get(position);

        }

        @Override

        public long getItemId(int position) {

            return position;

        }

        @Override

        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {

                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);

                holder = new ViewHolder();

                holder.tvItemGridView = (TextView)convertView.findViewById(R.id.tv_item_gridview);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.tvItemGridView.setText("" + getItem(position));


            //해당 날짜 텍스트 컬러,배경 변경

            mCal = Calendar.getInstance();

            //오늘 day 가져옴

            Integer today = mCal.get(Calendar.DAY_OF_MONTH);

            String sToday = String.valueOf(today);

            if (sToday.equals(getItem(position))) {
                holder.tvItemGridView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }


                return convertView;

        }

    }

    private class ViewHolder {

        TextView tvItemGridView;

    }


}


