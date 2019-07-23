package com.example.ciss_simkey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.ciss_simkey.databinding.ActivityLogInBinding;

public class LogIn extends AppCompatActivity {

    private static final String EXTRA_APP_NAME="com.example.ciss_simkey.app";

    private ActivityLogInBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_log_in);
        mBinding.appName.setText(getIntent().getStringExtra(EXTRA_APP_NAME));
//        setContentView(R.layout.activity_log_in);
    }

}
