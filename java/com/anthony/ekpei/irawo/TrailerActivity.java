package com.anthony.ekpei.irawo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrailerActivity extends AppCompatActivity {
    private List<Trailer> tg;
    private Traileradpter adpaterclass;
    private RecyclerView mrecyclerview;
    private ProgressDialog mprogress;
    private int movie_id;
    private String b,tit;
    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);

        mtoolbar=(Toolbar) findViewById(R.id.main_pageappbar);
        setSupportActionBar(mtoolbar);

        Intent i= getIntent();
        tit=i.getExtras().getString("TITLE");
        movie_id=i.getExtras().getInt("id");
        b= tit +"Trailer";
        getSupportActionBar().setTitle(b);

        //trailer
        mrecyclerview= (RecyclerView) findViewById(R.id.trailerecycler);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mprogress=new ProgressDialog(this);
        mprogress.setTitle("Connecting");
        mprogress.setMessage("Uploading content");
        mprogress.setCanceledOnTouchOutside(true);
        mprogress.show();


        tg=new ArrayList<>();


        adpaterclass = new Traileradpter(tg, getApplicationContext());

        mrecyclerview.setAdapter(adpaterclass);
        fetchretrofit();
    }

    private void fetchretrofit() {

        //retrofit network setting
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(fetchdata.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        fetchdata fetchdata = retrofit.create(fetchdata.class);

        Call<TrailerResponse> call = fetchdata.getMovieTrailer(movie_id);

        call.enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                mprogress.dismiss();

                List<Trailer> tg=response.body().getResults();


                adpaterclass = new Traileradpter(tg,getApplicationContext());

                mrecyclerview.setAdapter(adpaterclass);

                adpaterclass.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {
                mprogress.hide();
                Toast.makeText(TrailerActivity.this, "BadNetwork", Toast.LENGTH_LONG).show();

            }



        });
    }
}
