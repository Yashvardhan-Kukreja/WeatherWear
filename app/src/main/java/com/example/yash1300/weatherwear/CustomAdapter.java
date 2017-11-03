package com.example.yash1300.weatherwear;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Yash 1300 on 17-06-2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    List<Weather> weatherList;
    Context context;

    public CustomAdapter(List<Weather> weatherList, Context context) {
        this.weatherList = weatherList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);
        return (new ViewHolder(rootView));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Weather weather = weatherList.get(position);
        holder.date.setText(weather.getDate());
        holder.day.setText(weather.getDay());
        holder.high.setText(weather.getHigh());
        holder.low.setText(weather.getLow());
        holder.text.setText(weather.getText());
        holder.link.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, WearWithDetailActivity.class);
                i.putExtra("condition",weather.getText());
                context.startActivity(i);
            }
        });
        holder.linearLayout.setOnClickListener(new LinearLayout.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(context, Main3Activity.class);
                i1.putExtra("condition",weather.getText());
                context.startActivity(i1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView date, day, high, low, text, link;
        LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.date);
            day = (TextView) itemView.findViewById(R.id.day);
            high = (TextView) itemView.findViewById(R.id.highT);
            low = (TextView) itemView.findViewById(R.id.lowT);
            text = (TextView) itemView.findViewById(R.id.textT);
            link = (TextView) itemView.findViewById(R.id.linkToWear);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.cardLayout);
        }
    }

}
