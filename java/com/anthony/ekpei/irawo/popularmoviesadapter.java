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
 * Created by EKPEI on 5/23/2018.
 */

public class popularmoviesadapter extends RecyclerView.Adapter<popularmoviesadapter.MyHolder> {

    List<Result> list;
    private Context mcontext;


    public popularmoviesadapter(List<Result> movielist, FragmentActivity activity) {
        this.list=movielist;
        this.mcontext=activity;

    }


    @Override
    public popularmoviesadapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.popularmoviessinglelayout,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(popularmoviesadapter.MyHolder holder, final int position) {
        Result m=list.get(position);
        holder.titles.setText(m.getOriginalTitle());
        Double h=m.getPopularity();
        String c=Double.toString(h);
        String x= "Rating:" + c;
        holder.date.setText(x);
        Glide.with(mcontext).load(m.getPosterPath()).into(holder.im);

        holder.mcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Result fuckingclass = list.get(position);
                Intent skipintent = new Intent(v.getContext(), NextActivity.class);
                skipintent.putExtra("IMAGES", fuckingclass.getPosterPath());
                skipintent.putExtra("TITLE", fuckingclass.getTitle());
                skipintent.putExtra("OVERVIEW", fuckingclass.getOverview());
                skipintent.putExtra("DATE", fuckingclass.getReleaseDate());
                skipintent.putExtra("id", fuckingclass.getId());

                v.getContext().startActivity(skipintent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView titles,date;
        ImageView im;
       public CardView mcard;
        public MyHolder(View v)

        {
            super(v);
            im=(ImageView) v.findViewById(R.id.popularmovieimage);
            titles = (TextView) v.findViewById(R.id.titles);
           date = (TextView) v.findViewById(R.id.dates);
            mcard= (CardView) v.findViewById(R.id.cardview);
        }
    }
}
