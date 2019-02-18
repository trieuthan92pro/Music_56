package com.example.soundcloud.discover;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.soundcloud.R;
import com.example.soundcloud.data.model.Genre;

import java.util.List;

public class DiscoverVerticalAdapter
        extends RecyclerView.Adapter<DiscoverVerticalAdapter.ViewHolder> {
    private Context mContext;
    private List<Genre> mGenres;
    private LayoutInflater mLayoutInflater;

    public DiscoverVerticalAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.discover_vertical_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Genre genre = mGenres.get(position);
        viewHolder.bindData(mContext, genre);
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    public void setGenres(List<Genre> genres){
        mGenres = genres;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewGenreTitle;
        private TextView mTextViewViewMore;
        private ImageView mImageViewViewMore;
        private RecyclerView mRecyclerViewHorizontal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewGenreTitle = itemView.findViewById(R.id.text_genre_title);
            mTextViewViewMore = itemView.findViewById(R.id.text_view_more);
            mImageViewViewMore = itemView.findViewById(R.id.img_view_more);
            mRecyclerViewHorizontal = itemView.findViewById(R.id.horizontal_recycler_view);
            mRecyclerViewHorizontal.setHasFixedSize(true);
        }

        public void bindData(Context context, Genre genre) {
            if (genre == null) return;
            mTextViewGenreTitle.setText(genre.getTitle());
            DiscoverHorizontalAdapter discoverHorizontalAdapter =
                    new DiscoverHorizontalAdapter(context, genre.getSongs());
            RecyclerView.LayoutManager horizontalLayoutManager =
                    new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            mRecyclerViewHorizontal.setAdapter(discoverHorizontalAdapter);
            mRecyclerViewHorizontal.setLayoutManager(horizontalLayoutManager);
        }
    }
}
