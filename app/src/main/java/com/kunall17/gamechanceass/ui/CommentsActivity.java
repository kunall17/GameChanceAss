package com.kunall17.gamechanceass.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kunall17.gamechanceass.R;
import com.kunall17.gamechanceass.adapter.CommentAdapter;
import com.kunall17.gamechanceass.databinding.ActivityMainBinding;
import com.kunall17.gamechanceass.viewmodels.CommentViewModel;

public class CommentsActivity extends AppCompatActivity {

    public static final String ARG_COMMENT_ID = "ARG_COMMENT_ID";
    private RecyclerView feedRv;
    private ProgressDialog progressDialog;
    private CommentViewModel dataViewModel;

    public void showLoader(boolean b) {
        if (isFinishing()) return;
        if (b) {
            if (progressDialog != null)
                return;
            this.progressDialog = ProgressDialog.show(this, "", "", true, false);
            if (this.progressDialog.getWindow() == null) return;
            this.progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.progressDialog.setContentView(R.layout.progressdialog);
        } else if (this.progressDialog != null) {
            this.progressDialog.dismiss();
            this.progressDialog = null;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        feedRv = binding.recyclerView;

        feedRv = findViewById(R.id.recycler_view);
        dataViewModel = new CommentViewModel(getIntent().getIntExtra(ARG_COMMENT_ID, -1));
        setTitle("Comments");

        CommentAdapter adapter = new CommentAdapter();
        binding.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        feedRv.setHasFixedSize(true);
        feedRv.setLayoutManager(layoutManager);
        feedRv.setAdapter(adapter);
        dataViewModel.getCommentsList().observe(this, adapter::setData);
        dataViewModel.getIsLoading().observe(this, this::showLoader);
    }
}