package com.example.usersad.projectrealm.adapter;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.example.usersad.projectrealm.R;
import com.example.usersad.projectrealm.application.AppProjectRealm;
import com.example.usersad.projectrealm.helpers.SvgDecoder;
import com.example.usersad.projectrealm.helpers.SvgDrawableTrancoder;
import com.example.usersad.projectrealm.helpers.SvgSoftwareLayerSetter;
import com.example.usersad.projectrealm.helpers.onItemClickListener;
import com.example.usersad.projectrealm.model.mDataCountries;

import java.io.InputStream;
import java.util.List;

public class RvCountriesAdapter extends RecyclerView.Adapter<RvCountriesAdapter.ViewHoldew> {

    private List<mDataCountries> countriesList;
    private onItemClickListener listener;
    private Context context;

    public RvCountriesAdapter (List<mDataCountries> countriesList, Context context){this.countriesList = countriesList;this.context = context;}

    @NonNull
    @Override
    public RvCountriesAdapter.ViewHoldew onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvcountries_item,parent,false);
        return new ViewHoldew(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RvCountriesAdapter.ViewHoldew holder, int position) {

        mDataCountries mCountries = countriesList.get(position);

        holder.textName.setText(mCountries.getName());

        Log.i("mylog",mCountries.getName());
        Log.i("mylog",mCountries.getFlag());

        Uri uri = Uri.parse(mCountries.getFlag());
        holder.requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .load(uri)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    class ViewHoldew extends RecyclerView.ViewHolder {

        private TextView textName;
        private ImageView image;
        private LinearLayout itemCountries;
        private GenericRequestBuilder requestBuilder;

        ViewHoldew(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            image = itemView.findViewById(R.id.image);
            itemCountries = itemView.findViewById(R.id.itemCountries);
            itemCountries.setOnClickListener(v -> listener.onItemClick(getAdapterPosition()));

            requestBuilder = AppProjectRealm.getRequestBuilder();
        }


    }

    public void addDataCountries(List<mDataCountries> list){
        this.countriesList = list;
    }

    public void setItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }

}
