package com.kunall17.gamechanceass.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.kunall17.gamechanceass.R;
import com.kunall17.gamechanceass.pojo.Issue;
import com.kunall17.gamechanceass.viewmodels.IssueViewModel;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class IssueViewHoler extends RecyclerView.ViewHolder {

    private final IssueViewModel issueViewModel;
    private AppCompatTextView name;
    private AppCompatTextView title;
    private AppCompatTextView timeTv;
    private AppCompatTextView comments;

    public IssueViewHoler(@NonNull View rootView, IssueViewModel issueViewModel) {
        super(rootView);
        name = (AppCompatTextView) rootView.findViewById(R.id.name);
        title = (AppCompatTextView) rootView.findViewById(R.id.title);
        timeTv = (AppCompatTextView) rootView.findViewById(R.id.time);
        comments = (AppCompatTextView) rootView.findViewById(R.id.comments);
        this.issueViewModel = issueViewModel;
        itemView.setOnClickListener( v -> issueViewModel.viewMenuClick(getAdapterPosition(), (AppCompatActivity) rootView.getContext()));
    }

    public void set(Issue issue) {
        title.setText(issue.getTitle());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date time = sdf.parse(issue.getCreatedAt());

            PrettyTime prettyTime = new PrettyTime(Locale.getDefault());
            String ago = prettyTime.format(time);
            timeTv.setText(ago);
        } catch (Exception e) {
            e.printStackTrace();
        }
        comments.setText(issue.getComments() + "");

    }
}
