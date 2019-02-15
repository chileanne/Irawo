package com.anthony.ekpei.irawo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class NextActivity extends AppCompatActivity {
    private TextView tile,overview,realsedate;
    private ImageView image;
    private Button mbtn;
    private String titles,overviews,images,realease,a;
    private Toolbar mtoolbar;

    // private List<Trailer> tg;
    //private Traileradpter adpaterclass;
    //private RecyclerView mrecyclerview;
    //private ProgressDialog mprogress;
    public int movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        mtoolbar=(Toolbar) findViewById(R.id.main_pageappbar);
        setSupportActionBar(mtoolbar);

        //trailer
       /* mrecyclerview= (RecyclerView) findViewById(R.id.trailerecycler);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mprogress=new ProgressDialog(this);
        mprogress.setTitle("Connecting");
        mprogress.setMessage("Uploading content");
        mprogress.setCanceledOnTouchOutside(false);
        mprogress.show();*/

        mbtn=(Button) findViewById(R.id.btn);
        tile=(TextView) findViewById(R.id.filmtitle);
        overview=(TextView) findViewById(R.id.filmoverviecontent);
        image=(ImageView) findViewById(R.id.filmimage);
        realsedate=(TextView) findViewById(R.id.filmreleasedate);

        Intent i= getIntent();
        titles=i.getExtras().getString("TITLE");
        movie=i.getExtras().getInt("id");
        images=i.getExtras().getString("IMAGES");
        overviews=i.getExtras().getString("OVERVIEW");
        realease=i.getExtras().getString("DATE");
        a="ReleaseDate: "+realease;

        getSupportActionBar().setTitle(titles);

        Glide.with(this).load(images).placeholder(R.drawable.bv).into(image);
        tile.setText(titles);
        overview.setText(overviews);
        realsedate.setText(a);


        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myin= new Intent(NextActivity.this, TrailerActivity.class);
                myin.putExtra("id", movie);
                myin.putExtra("TITLE", titles);
                NextActivity.this.startActivity(myin);
            }
        });

   /*     tg=new ArrayList<>();


        adpaterclass = new Traileradpter(tg, getApplicationContext());

        mrecyclerview.setAdapter(adpaterclass);
        fetchretrofit();*/
    }

   /* private void fetchretrofit() {

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
                Toast.makeText(NextActivity.this, "BadNetwork", Toast.LENGTH_LONG).show();

            }



        });
    }*/

}

