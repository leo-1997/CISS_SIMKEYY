package com.example.ciss_simkey;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LogInProgress extends CustomTitleBarActivity {

    private MyTask mTask;

    private TextView mTextView;
    private Button mCancel;
    private ProgressBar mProgressBar;

    @Override
    protected int setTitleName() {
        return R.string.login_button;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_bar);

        centerTitle();

        mTextView = findViewById(R.id.progress_text);
        mProgressBar = findViewById(R.id.progress_bar);

        mTask = new MyTask();

        System.out.println("HERERE!");

        mTask.execute();

        mCancel = findViewById(R.id.progress_cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTask.cancel(true);
                finish();
            }
        });

    }

    private class MyTask extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPreExecute() {
            mTextView.setText("加载中");

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                int count = 0;
                int length = 1;
                while (count < 99) {

                    count += length;
                    publishProgress(count);
                    Thread.sleep(30);
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgressBar.setProgress(values[0]);
            mTextView.setText("loading..." + values[0] + "%");
        }

        @Override
        protected void onPostExecute(String s) {
            mTextView.setText("加载完毕");
            startActivity(new Intent(LogInProgress.this, MainPageActivity.class));
        }

        @Override
        protected void onCancelled() {
            mTextView.setText("已取消");
            mProgressBar.setProgress(0);
            setResult(Activity.RESULT_CANCELED, null);
        }
    }
}
