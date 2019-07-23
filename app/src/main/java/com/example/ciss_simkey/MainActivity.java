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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.selectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//                List<PackageInfo> appsList = MainActivity.this.getPackageManager().getInstalledPackages(0);
                List<ResolveInfo> appsList = MainActivity.this.getPackageManager().queryIntentActivities(mainIntent, 0);
                showAppList(appsList);
            }
        });
    }

    private void showAppList (final List<ResolveInfo> appList) {
//        System.out.println("List is here " + appList.get(0).resolvePackageName);


        ArrayList<String> app = new ArrayList<>();
        int i = 0;
        System.out.println("HERE");
        for (ResolveInfo info: appList) {
//            ApplicationInfo appInfo = appList.get(i).activityInfo.applicationInfo;
            //exclude system pre-installed apps
            if ((info.activityInfo.applicationInfo.flags & info.activityInfo.applicationInfo.FLAG_SYSTEM) <= 0 ) {
                app.add(info.loadLabel(MainActivity.this.getPackageManager()).toString());
            }
        }
        final String apps[] =  new String[app.size()];
        for (String name: app) {
            apps[i] = name;
            i++;
        }

        System.out.println("I am HERE!!!");
//        System.out.println("here is the list " + apps);
//        final String [] items = apps;
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("AppList");
        alertBuilder.setItems(apps, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(MainActivity.newIntent(MainActivity.this, apps[i]));
//                Toast.makeText(MainActivity.this, apps[i], Toast.LENGTH_SHORT).show();
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
