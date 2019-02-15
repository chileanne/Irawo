package com.anthony.ekpei.irawo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by EKPEI on 6/15/2018.
 */

public class upcomingadapter extends RecyclerView.Adapter<upcomingadapter.MyHolder> {
     List<Result> m;
    Context mcontext;

    public upcomingadapter(List<Result> movielistds, FragmentActivity activity) {

       this.m=movielistds;
        this.mcontext=activity;

    }

    @Override
    public upcomingadapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.upcomingsinglelayout,parent,false);
        return new  MyHolder(view);
    }

    @Override
    public void onBindViewHolder(upcomingadapter.MyHolder holder, final int position) {
        holder.titlesss.setText(m.get(position).getTitle());
        Double h=m.get(position).getPopularity();
        String c=Double.toString(h);
        String x= "Rating:" + c;
        holder.date.setText(x);
        Glide.with(mcontext).load(m.get(position).getPosterPath()).into(holder.imss);
         holder.mcardss.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                  Result a=m.get(position);
                 Intent b= new Intent(v.getContext(), NextActivity.class);
                b.putExtra("IMAGES", a.getPosterPath());
                b.putExtra("TITLE", a.getTitle());
                 b.putExtra("OVERVIEW", a.getOverview());
                 b.putExtra("DATE", a.getReleaseDate());
                 b.putExtra("id", a.getId());

                 v.getContext().startActivity(b);
             }
         });
    }

    @Override
    public int getItemCount() {
        return m.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {


        TextView titlesss,date;
        ImageView imss;
        public CardView mcardss;

        public MyHolder(View v) {
            super(v);

            imss=(ImageView) v.findViewById(R.id.upconingimage);
            titlesss = (TextView) v.findViewById(R.id.upcomingtitles);
           date = (TextView) v.findViewById(R.id.upcomingdates);
            mcardss= (CardView) v.findViewById(R.id.upcomingclick);

        }
    }
}
