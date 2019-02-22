package com.example.soundcloud.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.soundcloud.R;
import com.example.soundcloud.data.model.History;

import java.util.List;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<History> mSearchHistories;
    private OnSearchHistoryItemClickListener mOnSearchHistoryItemClickListener;

    public SearchHistoryAdapter(Context context, OnSearchHistoryItemClickListener listener) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mOnSearchHistoryItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_history_search, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        History history = mSearchHistories.get(i);
        viewHolder.bindData(history);
    }

    @Override
    public int getItemCount() {
        return mSearchHistories == null ? 0 : mSearchHistories.size();
    }

    public void setSearchHistories(List<History> searchHistories) {
        mSearchHistories = searchHistories;
    }

    public interface OnSearchHistoryItemClickListener {
        void onSearchHistoryItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextHistorySearchItem;
        private OnSearchHistoryItemClickListener mItemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextHistorySearchItem = itemView.findViewById(R.id.item_text_history_search);
        }

        public ViewHolder(@NonNull View view, OnSearchHistoryItemClickListener listener) {
            this(view);
            mItemClickListener = listener;
        }

        public void bindData(History history) {
            mTextHistorySearchItem.setText(history.getSearchKey());
        }
    }
}
