package com.example.sgandh1.imagesearch.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sgandh1.imagesearch.R;
import com.example.sgandh1.imagesearch.models.ImageResult;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sgandh1 on 10/17/15.
 */
public class ImageResultsAdapter extends ArrayAdapter<ImageResult> {
    public ImageResultsAdapter(Context context, List<ImageResult> images) {
        super(context, R.layout.item_image_result, images);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ImageResult imageResult = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent, false);
        }
        // Lookup view for data population
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        // clear out image from last time
        //ivImage.setImageResource(0);

        // Populate the data into the template view using the data object
        tvTitle.setText(Html.fromHtml(imageResult.title));
        Picasso.with(getContext()).load(imageResult.thumbUrl).into(ivImage);
        // Return the completed view to render on screen
        return convertView;
    }
}
