package com.example.calendarproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private ArrayList<Day> eventList;

    public GridAdapter(Context context, List<String> list, ArrayList<Day> eventList) {
        this.context = context;
        this.list = list;
        this.eventList = eventList;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
            holder = new ViewHolder();
            holder.tvItemGridView = (TextView) convertView.findViewById(R.id.tv_item_gridview);
            holder.textViewMeet = (TextView)convertView.findViewById(R.id.meet_view);
            holder.textViewPro = (TextView)convertView.findViewById(R.id.promiss_view);
            holder.textViewWork = (TextView)convertView.findViewById(R.id.work_view);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvItemGridView.setText("" + getItem(position));


        //해당 날짜 텍스트 컬러,배경 변경
        Calendar mCal = Calendar.getInstance();

        //오늘 Day 가져옴
        Integer today = mCal.get(Calendar.DAY_OF_MONTH);

        String sToday = String.valueOf(today);

        if (sToday.equals(getItem(position))) {
            holder.tvItemGridView.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }

        if(isInteger(getItem(position))){
            int index = Integer.parseInt(getItem(position)) - 1;
            Day todayEvent = eventList.get(index);

            if(todayEvent.getMeet()==200){
                holder.textViewMeet.setVisibility(View.VISIBLE);
            }else {
                holder.textViewMeet.setVisibility(View.INVISIBLE);
            }

            if (todayEvent.getPro()==200){
                holder.textViewPro.setVisibility(View.VISIBLE);
            }else {
                holder.textViewPro.setVisibility(View.INVISIBLE);
            }

            if(todayEvent.getWork()==200){
                holder.textViewWork.setVisibility(View.VISIBLE);
            }
            else{
                holder.textViewWork.setVisibility(View.INVISIBLE);
            }
        }

        return convertView;
    }

    private class ViewHolder {
        TextView tvItemGridView;
        TextView textViewMeet;
        TextView textViewPro;
        TextView textViewWork;
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}