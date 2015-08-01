package com.example.andi.shutterdroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.andi.shutterdroid.api.Image;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by andi on 29.07.15.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView;
        }
    }

    private List<Image> mImages;
    private Context mContext;

    public ImagesAdapter(Context context, List<Image> images) {
        mContext = context;
        mImages = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_image,viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Image image = mImages.get(i);
        Log.d("IA", image.getLargeThumbnail());
        Picasso.with(mContext).load(image.getLargeThumbnail()).into(viewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

}