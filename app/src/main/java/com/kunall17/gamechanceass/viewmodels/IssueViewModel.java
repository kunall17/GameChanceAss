package com.kunall17.gamechanceass.viewmodels;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kunall17.gamechanceass.pojo.Issue;
import com.kunall17.gamechanceass.repository.APIRepository;
import com.kunall17.gamechanceass.ui.CommentsActivity;

import java.util.List;

import static com.kunall17.gamechanceass.ui.CommentsActivity.ARG_COMMENT_ID;

public class IssueViewModel extends ViewModel {

    private APIRepository apiRepository;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<List<Issue>> issueList = new MutableLiveData<>();

    public IssueViewModel() {
        isLoading.setValue(false);
        apiRepository = new APIRepository(isLoading);
        apiRepository.getIssues(issueList);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<List<Issue>> getIssueList() {
        return issueList;
    }

    public void viewMenuClick(int adapterPosition, AppCompatActivity context) {
        List<Issue> value = issueList.getValue();
        if (value != null) {
            Intent intent = new Intent(context, CommentsActivity.class);
            intent.putExtra(ARG_COMMENT_ID, value.get(adapterPosition).getNumber());
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
}
