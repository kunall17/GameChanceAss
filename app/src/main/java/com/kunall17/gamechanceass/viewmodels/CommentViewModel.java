package com.kunall17.gamechanceass.viewmodels;

import androidx.lifecycle.MutableLiveData;

import com.kunall17.gamechanceass.pojo.Comments;
import com.kunall17.gamechanceass.repository.APIRepository;

import java.util.List;

public class CommentViewModel {
    private final Integer id;
    private APIRepository apiRepository;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<List<Comments>> commentsList = new MutableLiveData<>();

    public CommentViewModel(Integer id) {
        isLoading.setValue(false);
        apiRepository = new APIRepository(isLoading);
        this.id = id;
        fetchIds(id);
    }

    public void fetchIds(Integer id) {
        apiRepository.getCommentUrl(id, commentsList);
    }

    public MutableLiveData<List<Comments>> getCommentsList() {
        return commentsList;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }


    public MutableLiveData<List<Comments>> getMutableLiveData(Integer commentId) {
        return commentsList;
    }
}
