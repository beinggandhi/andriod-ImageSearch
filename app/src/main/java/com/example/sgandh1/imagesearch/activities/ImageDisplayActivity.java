package com.example.sgandh1.imagesearch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.sgandh1.imagesearch.R;
import com.example.sgandh1.imagesearch.models.ImageResult;
import com.squareup.picasso.Picasso;

public class ImageDisplayActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        //`getSupportActionBar().hide();
        ImageResult imageResult = (ImageResult) getIntent().getSerializableExtra("imageResult");
        ImageView ivImageResult = (ImageView) findViewById(R.id.ivImageResult);

        Picasso.with(this).load(imageResult.fullUrl).into(ivImageResult);
    }


}
