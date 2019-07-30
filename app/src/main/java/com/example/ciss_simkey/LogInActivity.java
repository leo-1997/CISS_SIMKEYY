package com.example.ciss_simkey;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.ciss_simkey.databinding.ActivityLogInBinding;

public class LogInActivity extends CustomTitleBarActivity {

    private static final String EXTRA_APP_NAME="com.example.ciss_simkey.app";

    private ActivityLogInBinding mBinding;

    @Override
    protected int setTitleName() {
        return R.string.login_button;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_log_in);
        centerTitle();
        mBinding.appName.setText(getIntent().getStringExtra(EXTRA_APP_NAME));
        mBinding.appName.setGravity(Gravity.CENTER);
        mBinding.selectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(LogInActivity.this, LogInFieldActivity.class));
            }
        });
    }
}
