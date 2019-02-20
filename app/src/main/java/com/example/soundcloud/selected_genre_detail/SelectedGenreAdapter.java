package com.example.soundcloud.selected_genre_detail;

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

public class SelectedGenreAdapter extends RecyclerView.Adapter<SelectedGenreAdapter.ViewHolder> {
    private Genre mGenre;
    private LayoutInflater mLayoutInflater;

    public SelectedGenreAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_song, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Song song = mGenre.getSongs().get(i);
        viewHolder.bindData(song);
    }

    @Override
    public int getItemCount() {
        return mGenre == null ? 0 : mGenre.getSongs().size();
    }

    public void setGenre(Genre genre) {
        mGenre = genre;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextSongTitle;
        private TextView mTextSongArtist;
        private ImageView mImageArtwork;
        private Context mContext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mTextSongArtist = itemView.findViewById(R.id.text_song_item_artist);
            mTextSongTitle = itemView.findViewById(R.id.text_song_item_title);
            mImageArtwork = itemView.findViewById(R.id.image_song_item_artwork);
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
