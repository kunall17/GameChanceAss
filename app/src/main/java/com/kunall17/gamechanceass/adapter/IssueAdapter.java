package com.kunall17.gamechanceass.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kunall17.gamechanceass.R;
import com.kunall17.gamechanceass.pojo.Issue;
import com.kunall17.gamechanceass.viewholders.IssueViewHoler;
import com.kunall17.gamechanceass.viewmodels.IssueViewModel;

import java.util.ArrayList;
import java.util.List;

public class IssueAdapter extends RecyclerView.Adapter<IssueViewHoler> {

    private List<Issue> issueList = new ArrayList<>();
    private IssueViewModel issueViewModel;

    public IssueAdapter(IssueViewModel issueViewModel) {
        this.issueViewModel = issueViewModel;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return issueList.size();
    }

    public void setData(List<Issue> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new Diff(this.issueList, newList));
        issueList = newList;
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public IssueViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IssueViewHoler(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false), issueViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull IssueViewHoler holder, int position) {
        holder.set(issueList.get(position));
    }
}
