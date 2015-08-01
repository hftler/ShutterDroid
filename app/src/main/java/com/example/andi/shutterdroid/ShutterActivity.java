package com.example.andi.shutterdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;


import com.example.andi.shutterdroid.api.Image;
import com.example.andi.shutterdroid.api.ShutterStock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ShutterActivity extends ActionBarActivity {

    private List<Image> mImages;
    private ImagesAdapter mAdapter;

    private String mClientID ="82222f54356045d9048b";
    private String mClientSecret = "3594d4f8dab767c52576ee2d09ec787df42a611d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shutter);





        mImages = new ArrayList<>();



        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new ImagesAdapter(this, mImages);
        recyclerView.setAdapter(mAdapter);

        ShutterStock.getRecent(new Date(), new Callback<List<Image>>() {
            @Override
            public void success(List<Image> images, Response response) {
                mImages.clear();
                mImages.addAll(images);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shutter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
