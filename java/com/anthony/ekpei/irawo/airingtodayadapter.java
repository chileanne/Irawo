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

public class airingtodayadapter extends RecyclerView.Adapter<airingtodayadapter.MyHolder> {
    List<Result2> a;
    Context mcontext;

    public airingtodayadapter(List<Result2> movielist, FragmentActivity activity) {
        this.a=movielist;
        this.mcontext=activity;
    }


    @Override
    public airingtodayadapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.nowairingsinglelayout,parent,false);
        return new  MyHolder(view);

    }

    @Override
    public void onBindViewHolder(airingtodayadapter.MyHolder holder, final int position) {
        Result2 v =a.get(position);
        holder.ptitless.setText(v.getName());
        Double h=v.getPopularity();
        String c=Double.toString(h);
        String x= "Rating:" + c;
        holder.date.setText(x);
        Glide.with(mcontext).load(v.getPosterPath()).placeholder(R.drawable.bv).into(holder.pims);

                holder.pmcards.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // Toast.makeText(mcontext,"hello", Toast.LENGTH_LONG ).show();

                        Result2 fuckingclass = a.get(position);
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
        return a.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView ptitless,date;
        ImageView pims;
        CardView pmcards;
        public MyHolder(View v) {

            super(v);

            pims=(ImageView) v.findViewById(R.id.nowairingimage);
            ptitless = (TextView) v.findViewById(R.id.nowairingtitles);
          date = (TextView) v.findViewById(R.id.nowairingdates);
            pmcards= (CardView) v.findViewById(R.id.nowairingclick);
        }
    }
}
