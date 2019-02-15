package com.anthony.ekpei.irawo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by EKPEI on 7/20/2018.
 */

public class Traileradpter extends RecyclerView.Adapter <Traileradpter.MyHolder> {
    private Context mcontext;
     List<Trailer> trailer;

    public Traileradpter(List<Trailer> tg, Context applicationContext) {
        this.mcontext = applicationContext;
        this.trailer = tg;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailrcard, parent, false);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.title.setText(trailer.get(position).getName());

        holder.mcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(mcontext, "hello", Toast.LENGTH_LONG).show();

                Trailer fuckingclass = trailer.get(position);
                String videoId = fuckingclass.getKey();
                Intent skipintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + videoId));

                skipintent.putExtra("DATE", videoId);

                v.getContext().startActivity(skipintent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return trailer.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;
        public CardView mcard;

        public MyHolder(View v) {
            super(v);

            title = (TextView) v.findViewById(R.id.titletrailer);
            image = (ImageView) v.findViewById(R.id.img);
            mcard = (CardView) v.findViewById(R.id.trailercard);
        }
    }
}