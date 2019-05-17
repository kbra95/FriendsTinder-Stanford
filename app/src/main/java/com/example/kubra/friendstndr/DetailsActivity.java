package com.example.kubra.friendstndr;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {

    private String nname;
    private ImageView img ;
    private TextView textView;
    private TextView baslik;
    private String[] friendDetails;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_details);
        if (fragment == null) {
            fragment = new Fragment();
            fm.beginTransaction()
                    .add(R.id.fragment_details, fragment)
                    .commit();
        }
        friendDetails = getResources().getStringArray(R.array.friend_details);
        textView =findViewById(R.id.details);
        baslik =findViewById(R.id.baslik);
        img =findViewById(R.id.img_id);
        Intent intent =getIntent();
        nname = intent.getStringExtra(MainFragment.NAME);
        index =intent.getIntExtra("index",0);
        setDetails(nname);
    }

    private void setDetails(String name) {
        int imgID = getResourseId(this,name,"drawable",getPackageName());
        textView.setText(friendDetails[index]);
        baslik.setText(name.toUpperCase());
        img.setImageResource(imgID);

    }

    public static int getResourseId(Context context, String pVariableName, String pResourcename, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        }
    }
}
