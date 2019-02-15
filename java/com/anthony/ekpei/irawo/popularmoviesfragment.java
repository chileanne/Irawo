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
public class popularmoviesfragment extends Fragment {
    private RecyclerView mrecyclerview;
    public List<Result> movielist;
                    public ProgressDialog mprogress;
 private popularmoviesadapter adpaterclass;
    private Toolbar mtoolbar;

    public popularmoviesfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_popularmoviesfragment, container, false);
        mtoolbar=(Toolbar) view.findViewById(R.id.main_pageappbar);
        mtoolbar.setTitle("Popular Movies");

        mrecyclerview=(RecyclerView)view.findViewById(R.id.popularmoviesrecyclerview);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mprogress=new ProgressDialog(getActivity());
        mprogress.setTitle("Connecting");
        mprogress.setMessage("Uploading content");
        mprogress.setCanceledOnTouchOutside(true);
        mprogress.show();
     movielist=new ArrayList<>();


        adpaterclass = new popularmoviesadapter(movielist, getActivity());

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

        Call<Movieresponse> call = fetchdata.getpopularmovies();

        call.enqueue(new Callback<Movieresponse>() {
            @Override
            public void onResponse(Call<Movieresponse> call, Response<Movieresponse> response) {

                mprogress.dismiss();
                List<Result> movielist=response.body().getResults();

                adpaterclass = new popularmoviesadapter(movielist, getActivity());

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
