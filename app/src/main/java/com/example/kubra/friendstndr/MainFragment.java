package com.example.kubra.friendstndr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainFragment extends Fragment {
    public static final int REQUEST_CODE = 0;
    public static final String NAME = "NAME";

    MediaPlayer mp;
    static String[] friendNames;
    private ImageView[] mImageViews;
    private TextView[] mTextViews;
    private RatingBar[] mRatingBars;
    static   int finalI=0;
    float ratingValue;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        friendNames = getResources().getStringArray(R.array.friend_names);
        mImageViews = new ImageView[6];
        mTextViews = new TextView[6];
        mRatingBars = new RatingBar[6];

        Activity activity = getActivity();
        mImageViews[0] = (ImageView) activity.findViewById(R.id.chandlerImage);
        mImageViews[1] = (ImageView) activity.findViewById(R.id.joeyImage);
        mImageViews[2] = (ImageView) activity.findViewById(R.id.monica_image);
        mImageViews[3] = (ImageView) activity.findViewById(R.id.phoebe_image);
        mImageViews[4] = (ImageView) activity.findViewById(R.id.rachel_image);
        mImageViews[5] = (ImageView) activity.findViewById(R.id.ross_image);

        mTextViews[0] = (TextView) activity.findViewById(R.id.chandler);
        mTextViews[1] = (TextView) activity.findViewById(R.id.joey);
        mTextViews[2] = (TextView) activity.findViewById(R.id.monica);
        mTextViews[3] = (TextView) activity.findViewById(R.id.phoebe);
        mTextViews[4] = (TextView) activity.findViewById(R.id.rachel);
        mTextViews[5] = (TextView) activity.findViewById(R.id.ross);

        mRatingBars[0] = (RatingBar) activity.findViewById(R.id.chandlerRatingBar);
        mRatingBars[1] = (RatingBar) activity.findViewById(R.id.joeyRatingBar);
        mRatingBars[2] = (RatingBar) activity.findViewById(R.id.monica_rating_bar);
        mRatingBars[3] = (RatingBar) activity.findViewById(R.id.phoebe_rating_bar);
        mRatingBars[4] = (RatingBar) activity.findViewById(R.id.rachel_rating_bar);
        mRatingBars[5] = (RatingBar) activity.findViewById(R.id.ross_rating_bar);

        //onClick

        listenerForImages();

        if(getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE){

           //mediaplayer dont restart
        }
    }

    public void listenerForImages(){
        mImageViews[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalI =0;
                ImageClick(view,finalI);
            }
        });
        mImageViews[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalI =1;
                ImageClick(view,finalI);
            }
        });
        mImageViews[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalI =2;
                ImageClick(view,finalI);
            }
        });
        mImageViews[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalI =3;
                ImageClick(view,finalI);
            }
        });
        mImageViews[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalI =4;
                ImageClick(view,finalI);
            }
        });
        mImageViews[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalI =5;
                ImageClick(view,finalI);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode ==1) {
            ratingValue = data.getFloatExtra("MyData", 0);
            Toast.makeText(getActivity(), "Your Rating Value is: " + String.valueOf(ratingValue),
                    Toast.LENGTH_SHORT).show();
        }}

    private void ImageClick(View view, int i) {
        Activity activity = getActivity();
        Intent intent = new Intent(activity,DetailsActivity.class);
        intent.putExtra(NAME,friendNames[i]);
        intent.putExtra("index",i);

        if((getContext().getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT))
        {
            startActivityForResult(intent,REQUEST_CODE);
        }
        else {
            //one activity and two fragment in it

            DetailsFragment fragment = (DetailsFragment) getFragmentManager().findFragmentById(R.id.fragment_details);
            fragment.setDetails(friendNames[i]);
        }

    }


}
