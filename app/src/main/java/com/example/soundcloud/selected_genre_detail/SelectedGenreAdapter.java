package com.example.soundcloud.selected_genre_detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.soundcloud.R;
import com.example.soundcloud.data.model.Genre;
import com.example.soundcloud.data.model.Song;

public class SelectedGenreAdapter extends RecyclerView.Adapter<SelectedGenreAdapter.ViewHolder> {
    private Genre mGenre;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;

    public SelectedGenreAdapter(Context context, OnItemClickListener listener) {
        mLayoutInflater = LayoutInflater.from(context);
        mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_song, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.setListener(mOnItemClickListener);
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextSongTitle;
        private TextView mTextSongArtist;
        private ImageView mImageArtwork;
        private Context mContext;
        private OnItemClickListener mListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mTextSongArtist = itemView.findViewById(R.id.text_song_item_artist);
            mTextSongTitle = itemView.findViewById(R.id.text_song_item_title);
            mImageArtwork = itemView.findViewById(R.id.image_song_item_artwork);

            mTextSongArtist.setOnClickListener(this);
            mTextSongTitle.setOnClickListener(this);
            mImageArtwork.setOnClickListener(this);
            itemView.findViewById(R.id.image_item_song_download).setOnClickListener(this);
            itemView.findViewById(R.id.image_item_song_option).setOnClickListener(this);
        }

        public void setListener(OnItemClickListener listener) {
            mListener = listener;
        }

        public void bindData(Song song) {
            if (song == null) return;
            mTextSongTitle.setText(song.getArtist());
            mTextSongArtist.setText(song.getArtist());
            Glide.with(mContext).load(song.getArtworkUrl())
                    .error(R.drawable.ic_artwork_item_default)
                    .into(mImageArtwork);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.text_song_item_artist:
                case R.id.text_song_item_title:
                case R.id.image_song_item_artwork:
                    mListener.onItemClick(getAdapterPosition());
                    break;

                case R.id.image_item_song_download:
                    //TODO(1): handle download feature latter
                    break;

                case R.id.image_item_song_option:
                    //TODO(2): handle menu option feature latter
                    break;

                default:
                    break;
            }
        }
    }
}
