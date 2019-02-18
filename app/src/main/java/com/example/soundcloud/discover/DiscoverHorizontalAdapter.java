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
        if (mSongs == null) {
            return 0;
        }
        return mSongs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textArtist;
        private TextView textSongTitle;
        private ImageView imageArtwork;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textSongTitle = itemView.findViewById(R.id.text_song_title_horizontal);
            textArtist = itemView.findViewById(R.id.text_artist_horizontal);
            imageArtwork = itemView.findViewById(R.id.image_artwork_horizontal);
        }

        public void bindData(Context context, Song song) {
            String artworkUrl = song.getArtworkUrl();
            if (artworkUrl == null || artworkUrl.isEmpty()) {
                Glide.with(context).load(R.drawable.soundcloud).into(imageArtwork);
            } else {
                Glide.with(context)
                        .load(song.getArtworkUrl().trim())
                        .error(R.drawable.ic_artwork_item_default)
                        .fallback(R.drawable.ic_artwork_item_default)
                        .into(imageArtwork);
            }
            textArtist.setText(song.getArtist());
            textSongTitle.setText(song.getTitle());
        }
    }
}
