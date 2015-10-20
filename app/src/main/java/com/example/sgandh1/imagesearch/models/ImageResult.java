package com.example.sgandh1.imagesearch.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sgandh1 on 10/17/15.
 */
public class ImageResult implements Serializable{



    public String fullUrl;
    public String thumbUrl;
    public String title;

    // create new ImageResult from raw json
    public ImageResult(JSONObject jsonObject) {
        try {
            title = jsonObject.getString("title");
            fullUrl = jsonObject.getString("url");
            thumbUrl = jsonObject.getString("tbUrl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ImageResult> fromJSONArray(JSONArray array){
        ArrayList<ImageResult> results = new ArrayList<ImageResult>();
        for(int i = 0 ; i < array.length(); i++) {
            try {
                results.add(new ImageResult(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return results;
    }



}
