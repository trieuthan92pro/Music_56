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
    private List<Genre> mList;

    public DiscoverVerticalAdapter(Context context, List<Genre> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.discover_vertical_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Genre genre = mList.get(position);
        viewHolder.textGenreTitle.setText(genre.getTitle());
        DiscoverHorizontalAdapter discoverHorizontalAdapter =
                new DiscoverHorizontalAdapter(mContext, genre.getSongList());
        RecyclerView.LayoutManager horizontalLayoutManager =
                new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        viewHolder.horizontalRecyclerView.setAdapter(discoverHorizontalAdapter);
        viewHolder.horizontalRecyclerView.setLayoutManager(horizontalLayoutManager);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textGenreTitle;
        public TextView textViewMore;
        public ImageView imageShowMore;
        public RecyclerView horizontalRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textGenreTitle = itemView.findViewById(R.id.text_genre_title);
            textViewMore = itemView.findViewById(R.id.text_view_more);
            imageShowMore = itemView.findViewById(R.id.img_view_more);
            horizontalRecyclerView = itemView.findViewById(R.id.horizontal_recycler_view);
            horizontalRecyclerView.setHasFixedSize(true);
        }
    }
}
