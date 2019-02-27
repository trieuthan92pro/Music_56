package com.example.soundcloud.my_music;

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
import com.example.soundcloud.data.model.Genre;
import com.example.soundcloud.data.model.Song;

public class LocalSongAdapter extends RecyclerView.Adapter<LocalSongAdapter.ViewHolder> {
    private Genre mGenre;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnLocalSongItemListener mListener;

    public LocalSongAdapter(Context context, OnLocalSongItemListener listener) {
        mContext = context;
        mListener = listener;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setGenre(Genre genre) {
        mGenre = genre;
        if (genre != null && genre.getSongs() != null) {
            notifyItemRangeChanged(0, genre.getSongs().size());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_song, viewGroup, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Song song = mGenre.getSongs().get(i);
        viewHolder.bindData(song);
    }

    @Override
    public int getItemCount() {
        return mGenre == null || mGenre.getSongs() == null ? 0 : mGenre.getSongs().size();
    }

    public interface OnLocalSongItemListener {
        void onSongItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private OnLocalSongItemListener mItemListener;
        private TextView mTextSongTitle;
        private TextView mTextSongArtist;
        private ImageView mImageArtwork;
        private Context mContext;
        private ImageView mImageAddToPlaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mTextSongArtist = itemView.findViewById(R.id.text_song_item_artist);
            mTextSongTitle = itemView.findViewById(R.id.text_song_item_title);
            mImageArtwork = itemView.findViewById(R.id.image_song_item_artwork);
            mImageAddToPlaylist = (ImageView) itemView.findViewById(R.id.image_item_song_download);
        }

        public ViewHolder(@NonNull View view, OnLocalSongItemListener listener) {
            this(view);
            mItemListener = listener;
        }

        public void bindData(Song song) {
            if (song == null) return;
            mTextSongTitle.setText(song.getTitle());
            mTextSongArtist.setText(song.getArtist());
            Glide.with(mContext).load(song.getArtworkUrl())
                    .error(R.drawable.ic_artwork_item_default)
                    .into(mImageArtwork);
            Glide.with(mContext)
                    .load(R.drawable.ic_add_to_playlist)
                    .into(mImageAddToPlaylist);
        }
    }
}
