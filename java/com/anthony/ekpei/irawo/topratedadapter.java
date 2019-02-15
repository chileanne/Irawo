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
 * Created by EKPEI on 6/11/2018.
 */

public class topratedadapter  extends RecyclerView.Adapter<topratedadapter.MyHolder> {

    List<Result> lists;
    private Context mcontexts;



    public topratedadapter(List<Result> movielistd, FragmentActivity activity) {
        this.lists=movielistd;
        this.mcontexts=activity;
    }

    @Override
    public topratedadapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.topratedsinglelayout,parent,false);
        return new  MyHolder(view);
    }

    @Override
    public void onBindViewHolder(topratedadapter.MyHolder holder, final int position) {

        Result m=lists.get(position);
        holder.titless.setText(m.getOriginalTitle());
        Double h=m.getPopularity();
        String c=Double.toString(h);
        String x= "Rating:" + c;
        holder.date.setText(x);
        Glide.with(mcontexts).load(m.getPosterPath()).into(holder.ims);

        holder.mcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Result fuckingclass = lists.get(position);
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
        return lists.size();


}

    public class MyHolder extends RecyclerView.ViewHolder {


        TextView titless,date;
        ImageView ims;
        public CardView mcards;

        public MyHolder(View v) {
            super(v);
            ims=(ImageView) v.findViewById(R.id.topratedimage);
            titless = (TextView) v.findViewById(R.id.topratedtitles);
           date = (TextView) v.findViewById(R.id.toprateddates);
            mcards= (CardView) v.findViewById(R.id.topratedclick);
        }
    }
    }



