package com.example.kubra.friendstndr;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class DetailsFragment extends Fragment {

    private RatingBar ratingBar;
    private Context mContext;
    private String nname;
    private ImageView img ;
    private TextView textView;
    private TextView baslik;
    private String[] friendDetails;
    private int index;
    private float ratingValue;
    private static Context context = null;
    MediaPlayer  mp;
    Intent intent;
    Activity activity;




    @Override
    public void onDestroy() {

        if (mp != null) {
            mp.stop();
            mp.release();
        }

        super.onDestroy();
    }
    @Override
    public void onPause() {
        super.onPause();
        if (mp != null &&
                (getActivity().getSupportFragmentManager().
                        findFragmentById(R.id.main_scroll_view) == null)){
            mp.pause();
            int pos = mp.getCurrentPosition();

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MediaFile",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("POS",pos);

            editor.apply();
        }

    }
    @Override
    public void onResume() {
        super.onResume();
        if (mp != null && getActivity().getSupportFragmentManager().
                findFragmentById(R.id.fragment_main) == null) {
            mp.setLooping(true);
            SharedPreferences s = getActivity().getSharedPreferences("MediaFile", Context.MODE_PRIVATE);
            mp.seekTo(s.getInt("POS", 0));
            mp.start();
        }}


        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_details, container, false);
        }

        @Override
        public void onActivityCreated (@Nullable Bundle savedInstanceState){
            super.onActivityCreated(savedInstanceState);


            mp = MediaPlayer.create(getActivity(), R.raw.friends);
            mp.start();

            friendDetails = getResources().getStringArray(R.array.friend_details);
            Activity activity = getActivity();
            img = activity.findViewById(R.id.img_id);
            textView = activity.findViewById(R.id.details);
            baslik = activity.findViewById(R.id.baslik);
            ratingBar = activity.findViewById(R.id.rating_bar);
            intent = activity.getIntent();

            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                    ratingValue = ratingBar.getRating();
                    Log.i("kbra", " " + ratingValue);
                    Intent intentRating = new Intent();
                    intentRating.putExtra("MyData", ratingValue);
                    getActivity().setResult(1, intentRating);
                }
            });

        }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        nname = intent.getStringExtra("NAME");
        index =intent.getIntExtra("index",0);
        setDetails(nname);
    }

    public void setDetails(String name) {
        int imgID = getResourceId(getContext(),name,"drawable",getActivity().getPackageName());
        textView.setText(friendDetails[index]);
        img.setImageResource(imgID);

    }

    public static int getResourceId(Context context, String pVariableName, String pResourcename, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        }
    }


}
