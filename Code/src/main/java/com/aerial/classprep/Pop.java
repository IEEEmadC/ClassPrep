package com.aerial.classprep;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

/**
 * Created by User on 7/12/2017.
 */

public class Pop extends Dialog {
    public Pop(@NonNull Context context) {
        super(context);
    }



    /*@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        //getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
    }*/


}
