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

    public DiscoverHorizontalAdapter(Context context, List<Song> songs) {
        mContext = context;
        mSongs = songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.song_horizontal_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Song song = mSongs.get(position);
        viewHolder.bindData(mContext, song);
    }

    @Override
    public int getItemCount() {
        return mSongs == null ? 0 : mSongs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewArtist;
        private TextView mTextViewSongTitle;
        private ImageView mImageViewArtwork;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewSongTitle = itemView.findViewById(R.id.text_song_title_horizontal);
            mTextViewArtist = itemView.findViewById(R.id.text_artist_horizontal);
            mImageViewArtwork = itemView.findViewById(R.id.image_artwork_horizontal);
        }

        public void bindData(Context context, Song song) {
            if (song == null) return;
            String artworkUrl = song.getArtworkUrl();
            Glide.with(context)
                    .load(artworkUrl.trim())
                    .error(R.drawable.ic_artwork_item_default)
                    .fallback(R.drawable.ic_artwork_item_default)
                    .into(mImageViewArtwork);
            mTextViewArtist.setText(song.getArtist());
            mTextViewSongTitle.setText(song.getTitle());
        }
    }
}
