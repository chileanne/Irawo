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
public class AiringtodayFragment extends Fragment {
    private RecyclerView mrecyclerview;
    public List<Result2> movielist;
        public ProgressDialog mprogress;
    private airingtodayadapter adpaterclass;
    private Toolbar mtoolbar;

    public AiringtodayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_airingtoday, container, false);

        //Toolbar
        mtoolbar=(Toolbar) view.findViewById(R.id.main_pageappbar);
        mtoolbar.setTitle("Airing Today");


        mrecyclerview=(RecyclerView)view.findViewById(R.id.nowairingrecyclerview);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mprogress=new ProgressDialog(getActivity());
        mprogress.setTitle("Connecting");
        mprogress.setMessage("Uploading content");
        mprogress.setCanceledOnTouchOutside(true);
        mprogress.show();
        movielist=new ArrayList<>();


        adpaterclass = new airingtodayadapter(movielist, getActivity());

        mrecyclerview.setAdapter(adpaterclass);
        fetchretrofit();



        return view;
    }

    private void fetchretrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(fetchdata.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        fetchdata fetchdata = retrofit.create(fetchdata.class);

        Call<Movieresponse2> call = fetchdata.getpopulartvseries();

        call.enqueue(new Callback<Movieresponse2>() {
            @Override
            public void onResponse(Call<Movieresponse2> call, Response<Movieresponse2> response) {

                    mprogress.dismiss();
                List<Result2> movielist=response.body().getResults();
                adpaterclass = new airingtodayadapter(movielist, getActivity());

                mrecyclerview.setAdapter(adpaterclass);

                adpaterclass.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<Movieresponse2> call, Throwable t) {
                mprogress.hide();
                 Toast.makeText(getActivity(), "BadNetwork", Toast.LENGTH_LONG).show();

            }



        });
    }


}



