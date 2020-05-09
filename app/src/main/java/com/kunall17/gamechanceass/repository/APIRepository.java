package com.kunall17.gamechanceass.repository;

import androidx.lifecycle.MutableLiveData;

import com.kunall17.gamechanceass.network.RetrofitAPIs;
import com.kunall17.gamechanceass.pojo.Comments;
import com.kunall17.gamechanceass.pojo.Issue;

import org.w3c.dom.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kunall17.gamechanceass.network.RetrofitService.getRetrofitApis;

public class APIRepository {
    private final MutableLiveData<Boolean> isLoading;
    private RetrofitAPIs zomatoApi;


    public APIRepository(MutableLiveData<Boolean> isLoading) {
        // Add all interceptors you want (headers, URL, logging)
        zomatoApi = getRetrofitApis();
        this.isLoading = isLoading;
    }

    public void getIssues(MutableLiveData<List<Issue>> issueList) {
        isLoading.setValue(true);
        zomatoApi.getIssues().enqueue(new Callback<List<Issue>>() {
            @Override
            public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                if (response.isSuccessful()) {
                    List<Issue> body = response.body();
                    issueList.setValue(body);
                }
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<List<Issue>> call, Throwable t) {
                t.printStackTrace();
                issueList.setValue(null);
                isLoading.setValue(false);
            }
        });
    }


    public void getCommentUrl(Integer id, MutableLiveData<List<Comments>> commentsList) {
        MutableLiveData<List<Comment>> issueList = new MutableLiveData<>();
        isLoading.setValue(true);
        zomatoApi.getIssues(id).enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if (response.isSuccessful()) {
                    List<Comments> body = response.body();
                    commentsList.setValue(body);
                }
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                t.printStackTrace();
                issueList.setValue(null);
                isLoading.setValue(false);
            }
        });
    }
}
