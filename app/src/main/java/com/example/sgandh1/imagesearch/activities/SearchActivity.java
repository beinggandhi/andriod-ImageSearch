package com.example.sgandh1.imagesearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.example.sgandh1.imagesearch.R;
import com.example.sgandh1.imagesearch.adapters.EndlessScrollListener;
import com.example.sgandh1.imagesearch.adapters.ImageResultsAdapter;
import com.example.sgandh1.imagesearch.models.ImageResult;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity {

    private EditText etQuery;
    private GridView gvResults;
    private AsyncHttpClient client = new AsyncHttpClient();
    private ArrayList<ImageResult> imageResults;
    private ImageResultsAdapter imageResultsAdapter;

    private int startIndex = 0;
    private int numResults = 8;
    private String query;
    private String searchURI = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0";


    private void setupViews() {
        etQuery = (EditText) findViewById(R.id.etQuery);
        gvResults = (GridView) findViewById(R.id.gvResults);
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, ImageDisplayActivity.class);
                ImageResult imageResult = imageResults.get(position);

                intent.putExtra("imageResult", imageResult);

                startActivity(intent);
            }
        });

        gvResults.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                startIndex += numResults;
                customLoadMoreDataFromApi();
                return true;
            }
        });
    }

    private void customLoadMoreDataFromApi() {
            String searchUrl = searchURI + "&rsz=" + numResults + "&q=" + query + "&start=" + startIndex;

//        client.get(searchUrl, onSuccess)
        client.get(searchUrl, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray imageResultsJSON;
                try {
                    imageResultsJSON = response.getJSONObject("responseData").getJSONArray("results");
                    imageResultsAdapter.addAll(ImageResult.fromJSONArray(imageResultsJSON));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setupViews();
        imageResults = new ArrayList<ImageResult>();

        imageResultsAdapter = new ImageResultsAdapter(this, imageResults);
        gvResults.setAdapter(imageResultsAdapter);
    }



    public void onImageSearch(View view) {
        query = etQuery.getText().toString();
        //Toast.makeText(this, query, Toast.LENGTH_LONG).show();

        String searchUrl = searchURI + "&rsz=" + numResults + "&q=" + query;

        imageResultsAdapter.clear();
        startIndex = 0;
        customLoadMoreDataFromApi();

    }

    public void showFilters(MenuItem item) {
    }
}
