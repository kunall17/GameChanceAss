package com.kunall17.gamechanceass.network;

import com.kunall17.gamechanceass.pojo.Comments;
import com.kunall17.gamechanceass.pojo.Issue;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitAPIs {

    @GET("repos/firebase/firebase-ios-sdk/issues")
    Call<List<Issue>> getIssues();


    @GET("repos/firebase/firebase-ios-sdk/issues/{id}/comments")
    Call<List<Comments>> getIssues(@Path("id") Integer id);

}
