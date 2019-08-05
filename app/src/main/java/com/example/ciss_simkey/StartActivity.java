package com.example.ciss_simkey;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ciss_simkey.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    private static final String EXTRA_APP_NAME="com.example.ciss_simkey.app";

    private ActivityMainBinding mBinding;
    private AlertDialog mAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getSupportActionBar().hide();
        mBinding.selectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> appsList = StartActivity.this.getPackageManager().queryIntentActivities(mainIntent, 0);
                showAppList(appsList);
            }
        });
    }

    private void showAppList (final List<ResolveInfo> appList) {
        ArrayList<String> app = new ArrayList<>();
        int i = 0;
        for (ResolveInfo info: appList) {
            if ((info.activityInfo.applicationInfo.flags & info.activityInfo.applicationInfo.FLAG_SYSTEM) <= 0 ) {
                app.add(info.loadLabel(StartActivity.this.getPackageManager()).toString());
            }
        }
        final String apps[] =  new String[app.size()];
        for (String name: app) {
            apps[i] = name;
            i++;
        }

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("选择软件");
        alertBuilder.setItems(apps, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(StartActivity.newIntent(StartActivity.this, apps[i]));
                mAlert.dismiss();
            }
        });
        mAlert = alertBuilder.create();
        mAlert.show();
    }

    public static Intent newIntent (Context packageContext, String appName) {
        Intent intent = new Intent(packageContext, LogInActivity.class);
        intent.putExtra(EXTRA_APP_NAME, appName);
        return intent;
    }
}
