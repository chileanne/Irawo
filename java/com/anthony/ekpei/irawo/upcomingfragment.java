package com.anthony.ekpei.irawo;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class upcomingfragment extends Fragment {
    private RecyclerView mrecyclerview;
    public List<Result> movielistds;
    public upcomingadapter adpaterclass;
    public ProgressDialog mprogress;
    private Toolbar mtoolbar;




    public upcomingfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_upcomingfragment, container, false);
        mtoolbar=(Toolbar) view.findViewById(R.id.main_pageappbar);
        mtoolbar.setTitle("Upcoming Movies");
        mrecyclerview= (RecyclerView) view.findViewById(R.id.upcomingrecyclerview);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mprogress=new ProgressDialog(getActivity());
        mprogress.setTitle("Connecting");
        mprogress.setMessage("Uploading content");
        mprogress.setCanceledOnTouchOutside(true);
        mprogress.show();
        movielistds=new ArrayList<>();


        adpaterclass = new upcomingadapter(movielistds, getActivity());

        mrecyclerview.setAdapter(adpaterclass);
        fetchretrofit();
        return view;
    }

    private void fetchretrofit() {

        //retrofit network setting
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(fetchdata.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        fetchdata fetchdata = retrofit.create(fetchdata.class);

        Call<Movieresponse> call = fetchdata.getupcomignmovies();

        call.enqueue(new Callback<Movieresponse>() {
            @Override
            public void onResponse(Call<Movieresponse> call, Response<Movieresponse> response) {
            mprogress.dismiss();

                List<Result> movielistds=response.body().getResults();


                adpaterclass = new upcomingadapter(movielistds,getActivity());

                mrecyclerview.setAdapter(adpaterclass);

                adpaterclass.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<Movieresponse> call, Throwable t) {
                mprogress.hide();
                  Toast.makeText(getActivity(), "BadNetwork", Toast.LENGTH_LONG).show();

            }



        });
    }


}
