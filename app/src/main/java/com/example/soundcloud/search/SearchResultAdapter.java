package com.example.soundcloud.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.soundcloud.R;
import com.example.soundcloud.data.model.Song;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private List<Song> mSongs;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mItemClickListener;

    public SearchResultAdapter(Context context, OnItemClickListener listener) {
        mContext = context;
        mItemClickListener = listener;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_song, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Song song = mSongs.get(i);
        viewHolder.bindData(song);
    }

    @Override
    public int getItemCount() {
        return mSongs == null ? 0 : mSongs.size();
    }

    public void setSongs(List<Song> songs) {
        mSongs = songs;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextSongTitle;
        private TextView mTextSongArtist;
        private ImageView mImageArtwork;
        private Context mContext;
        private OnItemClickListener mOnItemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mTextSongArtist = itemView.findViewById(R.id.text_song_item_artist);
            mTextSongTitle = itemView.findViewById(R.id.text_song_item_title);
            mImageArtwork = itemView.findViewById(R.id.image_song_item_artwork);
        }

        public ViewHolder(@NonNull View view, OnItemClickListener listener) {
            super(view);
            mOnItemClickListener = listener;
        }

        public void bindData(Song song) {
            if (song == null) return;
            mTextSongTitle.setText(song.getArtist());
            mTextSongArtist.setText(song.getArtist());
            Glide.with(mContext).load(song.getArtworkUrl())
                    .error(R.drawable.ic_artwork_item_default)
                    .into(mImageArtwork);
        }
    }
}
