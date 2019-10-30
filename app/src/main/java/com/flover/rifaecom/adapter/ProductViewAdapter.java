package com.flover.rifaecom.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.flover.rifaecom.R;

import java.util.ArrayList;


public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.ViewHolder>{
    private static final String TAG = "ProductViewAdapter";

    private ArrayList<String> mImageNames;
    private ArrayList<String> mImages;
    private Context mContext;

    public ProductViewAdapter(ArrayList<String> mImageNames, Context mContext){
        this.mImages = mImageNames;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "OnBindViewHolder: called");
        Glide.with(mContext).asBitmap().load(mImages.get(position)).into(holder.image);
        // holder.imageDescription.setText(mImageNames.get(position));
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        // TextView imageDescription;

        CardView parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            // imageDescription = itemView.findViewById(R.id.product_name);
            //parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
