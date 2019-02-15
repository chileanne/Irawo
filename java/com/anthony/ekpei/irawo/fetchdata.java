package com.anthony.ekpei.irawo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by EKPEI on 5/24/2018.
 */

public interface fetchdata {
String BASE_URL="http://api.themoviedb.org/3/";

    @GET("movie/popular?api_key=dec0d824cf240747b20fc0cbd86e84b1")
    Call<Movieresponse> getpopularmovies();

    @GET("movie/top_rated?api_key=dec0d824cf240747b20fc0cbd86e84b1")
    Call<Movieresponse> gettoprated();

    @GET("movie/upcoming?api_key=dec0d824cf240747b20fc0cbd86e84b1")
    Call<Movieresponse> getupcomignmovies();

    @GET("tv/airing_today?api_key=dec0d824cf240747b20fc0cbd86e84b1")
    Call<Movieresponse2> getpopulartvseries();

    @GET("tv/top_rated?api_key=dec0d824cf240747b20fc0cbd86e84b1")
    Call<Movieresponse2> gettopratedtvseries();


    @GET("tv/popular?api_key=dec0d824cf240747b20fc0cbd86e84b1")
    Call<Movieresponse2> getpopulartvsiess();

    @GET("movie/{movie_id}/videos?api_key=dec0d824cf240747b20fc0cbd86e84b1")
    Call<TrailerResponse> getMovieTrailer(@Path("movie_id") int id);
}
