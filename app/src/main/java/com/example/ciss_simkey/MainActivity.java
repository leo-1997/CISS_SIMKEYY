package com.example.ciss_simkey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.ciss_simkey.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String EXTRA_APP_NAME="com.example.ciss_simkey.app";

    private ActivityMainBinding mBinding;
    private AlertDialog mAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.main_title);
        setTitle("选择程序");
        mBinding.selectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> appsList = MainActivity.this.getPackageManager().queryIntentActivities(mainIntent, 0);
                showAppList(appsList);
            }
        });
    }

    private void showAppList (final List<ResolveInfo> appList) {
        ArrayList<String> app = new ArrayList<>();
        int i = 0;
        for (ResolveInfo info: appList) {
            if ((info.activityInfo.applicationInfo.flags & info.activityInfo.applicationInfo.FLAG_SYSTEM) <= 0 ) {
                app.add(info.loadLabel(MainActivity.this.getPackageManager()).toString());
            }
        }
        final String apps[] =  new String[app.size()];
        for (String name: app) {
            apps[i] = name;
            i++;
        }

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("AppList");
        alertBuilder.setItems(apps, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(MainActivity.newIntent(MainActivity.this, apps[i]));
                mAlert.dismiss();
            }
        });
        mAlert = alertBuilder.create();
        mAlert.show();
    }

    public static Intent newIntent (Context packageContext, String appName) {
        Intent intent = new Intent(packageContext, LogIn.class);
        intent.putExtra(EXTRA_APP_NAME, appName);
        return intent;
    }
}
