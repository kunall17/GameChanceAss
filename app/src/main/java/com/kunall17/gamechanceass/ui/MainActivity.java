package com.kunall17.gamechanceass.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kunall17.gamechanceass.R;
import com.kunall17.gamechanceass.adapter.IssueAdapter;
import com.kunall17.gamechanceass.databinding.ActivityMainBinding;
import com.kunall17.gamechanceass.viewmodels.IssueViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView feedRv;
    private ProgressDialog progressDialog;
    private IssueViewModel issueViewModel;

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
        issueViewModel = new IssueViewModel();
        setTitle("Issues");

        IssueAdapter adapter = new IssueAdapter(issueViewModel);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.setAdapter(adapter);
        feedRv.setHasFixedSize(true);
        feedRv.setLayoutManager(layoutManager);
        issueViewModel.getIssueList().observe(this, adapter::setData);
        issueViewModel.getIsLoading().observe(this, this::showLoader);
    }
}
