package com.example.soundcloud.discover;

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

public class DiscoverHorizontalAdapter extends RecyclerView.Adapter<DiscoverHorizontalAdapter.ViewHolder> {
    private List<Song> mSongs;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public DiscoverHorizontalAdapter(Context context, List<Song> songs) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mSongs = songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.song_horizontal_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Song song = mSongs.get(position);
        viewHolder.bindData(song);
    }

    @Override
    public int getItemCount() {
        return mSongs == null ? 0 : mSongs.size();
    }

    public void setSongs(List<Song> songs) {
        mSongs = songs;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewArtist;
        private TextView mTextViewSongTitle;
        private ImageView mImageViewArtwork;
        private Context mContext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mTextViewSongTitle = itemView.findViewById(R.id.text_song_title_horizontal);
            mTextViewArtist = itemView.findViewById(R.id.text_artist_horizontal);
            mImageViewArtwork = itemView.findViewById(R.id.image_artwork_horizontal);
        }

        public void bindData(Song song) {
            if (song == null) return;
            String artworkUrl = song.getArtworkUrl();
            Glide.with(mContext)
                    .load(artworkUrl.trim())
                    .error(R.drawable.ic_artwork_item_default)
                    .fallback(R.drawable.ic_artwork_item_default)
                    .into(mImageViewArtwork);
            mTextViewArtist.setText(song.getArtist());
            mTextViewSongTitle.setText(song.getTitle());
        }
    }
}
