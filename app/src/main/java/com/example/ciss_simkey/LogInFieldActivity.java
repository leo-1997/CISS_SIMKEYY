package com.example.ciss_simkey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.ciss_simkey.databinding.LoginFieldBinding;

public class LogInFieldActivity extends CustomTitleBarActivity {
    private static final int REQUEST_LOG_IN = 1;

    private EditText mPhoneNumber;
    private EditText mPinNumber;

    private LoginFieldBinding mBinding;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.login_field);
        centerTitle(R.string.login_button);
        mBinding.selectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(LogInFieldActivity.this, LogInProgress.class), REQUEST_LOG_IN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_LOG_IN && resultCode == Activity.RESULT_CANCELED) {
            Toast mToast = Toast.makeText(LogInFieldActivity.this, R.string.cancel_toast, Toast.LENGTH_SHORT);
            mToast.show();
        }
    }
}
