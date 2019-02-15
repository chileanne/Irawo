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
 * Created by EKPEI on 6/22/2018.
 */

public class upcomingtvseriesadapter extends RecyclerView.Adapter<upcomingtvseriesadapter.MyHoler> {

            private List<Result2> movielist;
    private Context mcontext;
    public upcomingtvseriesadapter(List<Result2> movielist, FragmentActivity activity) {
        this.movielist=movielist;
        this.mcontext=activity;
    }

    @Override
    public upcomingtvseriesadapter.MyHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.upcomingtvseriessinglelayout,parent,false);
        return new MyHoler(view);
    }

    @Override
    public void onBindViewHolder(upcomingtvseriesadapter.MyHoler holder, final int position) {
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
                // Toast.makeText(mcontext,"hello", Toast.LENGTH_LONG ).show();

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

    public class MyHoler extends RecyclerView.ViewHolder {
        TextView ptitless,date;
        ImageView pims;
        CardView pmcards;
        public MyHoler(View v) {
            super(v);
            pims=(ImageView) v.findViewById(R.id.upcomingtvseriesimage);
           date=(TextView) v.findViewById(R.id.upcomingtvseriesdates);
            ptitless = (TextView) v.findViewById(R.id.upcomingtvseriestitles);
            pmcards= (CardView) v.findViewById(R.id.upcomingtvseriesclick);

        }
    }
}
