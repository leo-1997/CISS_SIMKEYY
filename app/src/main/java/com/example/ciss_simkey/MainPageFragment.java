package com.example.ciss_simkey;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;

public class MainPageFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);

//        setScale(view, R.id.imageView);

        return view;
    }

    /***
     * To adjust the scale of banner according to the screen size of the device
     * The scale is 3/5 of the height of the device's screen
     * @param imgId To set the resource id of the image
     */
    private void setScale(View view, int imgId) {
        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int sWidth = metrics.widthPixels;
        int sHeight = metrics.heightPixels;

//        ImageView mImage = view.findViewById(imgId);
//        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(sWidth, (int) ((float) 3/5 * sHeight));
//        mImage.setLayoutParams(params);
    }
}
