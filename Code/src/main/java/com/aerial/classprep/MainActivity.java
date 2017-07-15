package com.aerial.classprep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb=(Toolbar)findViewById(R.id.toolbar);
        System.out.println(tb);
        setSupportActionBar(tb);

        getSupportActionBar().setTitle("Class Prep");
        db=new Database(this);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        System.out.println("called exo");
        getDayLessons(dayOfTheWeek);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.top_menus,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res=item.getItemId();
        if(res==R.id.menu_add){
            //Toast.makeText(getApplicationContext(),"You selected add new",Toast.LENGTH_LONG).show();
            Intent nn=new Intent(this,add_subject.class);
            this.startActivity(nn);
        }
        return true;
    }

    public void popHis(View view) {
        Pop dialog=new Pop(this);

        dialog.setContentView(R.layout.popwindow);
        dialog.show();
        dialog.getWindow().setLayout(1000, 1000);
    }

    public void popHis(String ss,String ll) {
        Pop dialog=new Pop(this);

        dialog.setContentView(R.layout.popwindow);
        TextView tws=(TextView) findViewById(R.id.subjectdes);
        TextView twl=(TextView) findViewById(R.id.lessondes);

        System.out.println(tws+" "+twl);

        tws.setText(ss);
        twl.setText(ll);
        dialog.show();
        dialog.getWindow().setLayout(1000, 1000);
    }

    public void getDayLessons(String day){
        ArrayList<DaySL> lessons = db.getLessons(day);
        LinearLayout ll=(LinearLayout) findViewById(R.id.subLayout);
        if(lessons!=null){
            for (DaySL ss:lessons){
                final String su=ss.getSub();
                final String le=ss.getLes();
                Button b=new Button(this);
                b.setText(su);
                b.setTextSize(15);

                b.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        Pop dialog=new Pop(v.getContext());

                        dialog.setContentView(R.layout.popwindow);
                        TextView tws=(TextView) dialog.findViewById(R.id.subjectdes);
                        TextView twl=(TextView) dialog.findViewById(R.id.lessondes);

                        tws.setText(su);
                        twl.setText(le);

                        dialog.show();
                        dialog.getWindow().setLayout(1000, 700);

                    }
                });

                DisplayMetrics metrics = this.getResources().getDisplayMetrics();
                float dpw = 155f;
                float dph = 149;
                float lw=114;
                float fpixelsw = metrics.density * dpw;
                float fpixelsh = metrics.density * dph;
                float lfw=metrics.density * lw;
                int pixelsw = (int) (fpixelsw + 0.5f);
                int pixelsh = (int) (fpixelsh + 0.5f);
                int llw=(int) (lfw + 0.5f);

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(pixelsw,pixelsh);
                b.setBackgroundResource(R.drawable.round_button);
                ll.addView(b,lp);

                Button bs=new Button(this);
                LinearLayout.LayoutParams lps = new LinearLayout.LayoutParams(llw, ViewGroup.LayoutParams.WRAP_CONTENT);
                bs.setBackgroundResource(R.drawable.medium_spacer);
                ll.addView(bs,lps);
            }

        }


    }

}
