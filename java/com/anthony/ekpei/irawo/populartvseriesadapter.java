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

import static android.media.CamcorderProfile.get;

/**
 * Created by EKPEI on 6/18/2018.
 */

public class populartvseriesadapter extends RecyclerView.Adapter<populartvseriesadapter.MyHolder> {
    List<Result2> movielist;
    Context mcontext;

    public populartvseriesadapter(List<Result2> movielist, FragmentActivity activity) {
        this.movielist=movielist;
        this.mcontext=activity;

    }

    @Override
    public populartvseriesadapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.populartvseriessinglelayout,parent,false);
        return new  MyHolder(view);
    }

    @Override
    public void onBindViewHolder(populartvseriesadapter.MyHolder holder, final int position) {
        Result2 m=movielist.get(position);
        holder.ptitless.setText(m.getName());
        Double a=m.getPopularity();
        String c=Double.toString(a);
        String x= "Rating:" + c;
        holder.date.setText(x);
        Glide.with(mcontext).load(m.getPosterPath()).placeholder(R.drawable.bv).into(holder.pims);

        holder.pmcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Result2 fuckingclass = movielist.get(position);
                Intent skipintent = new Intent(v.getContext(), NextActivity2.class);
                skipintent.putExtra("IMAGES", fuckingclass.getPosterPath());
                skipintent.putExtra("TITLE", fuckingclass.getName());
                skipintent.putExtra("OVERVIEW", fuckingclass.getOverview());
                skipintent.putExtra("DATE", fuckingclass.getFirstAirDate());

                v.getContext().startActivity(skipintent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return movielist.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView ptitless,date;
        ImageView pims;
        public CardView pmcards;
        public MyHolder(View v) {
            super(v);
            pims=(ImageView) v.findViewById(R.id.populartvseriesimage);
            ptitless = (TextView) v.findViewById(R.id.populartvseriestitles);
            date = (TextView) v.findViewById(R.id.populartvseriesdates);
            pmcards= (CardView) v.findViewById(R.id.populartvseriesclick);
        }
    }
}
