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
public class topratedfragment extends Fragment {
    private RecyclerView mrecyclerview;
    public List<Result> movielistd;
    private topratedadapter adpaterclass;
    public ProgressDialog mprogress;
    private Toolbar mtoolbar;

    public topratedfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_topratedfragment, container, false);
        mtoolbar=(Toolbar) view.findViewById(R.id.main_pageappbar);
        mtoolbar.setTitle("TopRated Movies");
        mrecyclerview= (RecyclerView) view.findViewById(R.id.topratedrecyclerview);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mprogress=new ProgressDialog(getActivity());
        mprogress.setTitle("Connecting");
        mprogress.setMessage("Uploading content");
        mprogress.setCanceledOnTouchOutside(true);
        mprogress.show();
        movielistd=new ArrayList<>();


        adpaterclass = new topratedadapter(movielistd, getActivity());

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

        Call<Movieresponse> call = fetchdata.gettoprated();

        call.enqueue(new Callback<Movieresponse>() {
            @Override
            public void onResponse(Call<Movieresponse> call, Response<Movieresponse> response) {

                mprogress.dismiss();
                List<Result> movielistd=response.body().getResults();


                adpaterclass = new topratedadapter(movielistd,getActivity());

                mrecyclerview.setAdapter(adpaterclass);

                adpaterclass.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<Movieresponse> call, Throwable t) {
                mprogress.hide();
             //   Toast.makeText(getActivity(), "BadNetwork", t.LENGTH_LONG).show();

            }



        });
    }

}
