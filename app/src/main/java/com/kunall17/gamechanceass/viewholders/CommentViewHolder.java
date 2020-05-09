package com.kunall17.gamechanceass.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.kunall17.gamechanceass.R;
import com.kunall17.gamechanceass.pojo.Comments;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CommentViewHolder extends RecyclerView.ViewHolder {

    private AppCompatTextView name;
    private AppCompatTextView title;
    private AppCompatTextView timeTv;
    private AppCompatTextView comments;

    public CommentViewHolder(@NonNull View rootView) {
        super(rootView);
        name = (AppCompatTextView) rootView.findViewById(R.id.name);
        title = (AppCompatTextView) rootView.findViewById(R.id.title);
        timeTv = (AppCompatTextView) rootView.findViewById(R.id.time);
        comments = (AppCompatTextView) rootView.findViewById(R.id.comments);
        comments.setVisibility(View.GONE);
    }

    public void set(Comments issue) {
        name.setText(issue.getUser().getLogin());
        title.setText(issue.getBody());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date time = sdf.parse(issue.getCreatedAt());

            PrettyTime prettyTime = new PrettyTime(Locale.getDefault());
            String ago = prettyTime.format(time);
            timeTv.setText(ago);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
