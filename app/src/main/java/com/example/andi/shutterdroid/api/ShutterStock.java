package com.example.andi.shutterdroid.api;

import android.util.Base64;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by andi on 23.07.15.
 */
public class ShutterStock {
    protected String mClientID ="82222f54356045d9048b";
    protected String mClientSecret = "3594d4f8dab767c52576ee2d09ec787df42a611d";

    private static final RestAdapter ADAPTER = new RestAdapter.Builder()
            .setEndpoint("https://api.shutterstock.com/v2")
            .setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestInterceptor.RequestFacade request) {
                    String authInfo = "82222f54356045d9048b:3594d4f8dab767c52576ee2d09ec787df42a611d";
                    String auth = "Basic "+ Base64.encodeToString(authInfo.getBytes(), Base64.NO_WRAP);
                    request.addHeader("Authorization", auth);
                }
            })
            .build();

    private static final ShutterStockService SERVICE = ADAPTER.create(ShutterStockService.class);

    public static void search(String query, Callback<List<Image>> cb){
        SERVICE.search(query, new ImageCallback(cb));
    }


    public static void getRecent(Date date, Callback<List<Image>> cb){
        SERVICE.getRecent(new SimpleDateFormat("yyyy-MM-dd").format(date), new ImageCallback(cb));
    }



    private  static class ImageCallback implements Callback<Response>{
        Callback<List<Image>> cb;
        ImageCallback(Callback<List<Image>> cb){
            this.cb = cb;
        }
        @Override
        public void success(Response response, retrofit.client.Response response2) {
            cb.success(response.data, response2);
        }

        @Override
        public void failure(RetrofitError error) {
            cb.failure(error);
        }
    }
}
