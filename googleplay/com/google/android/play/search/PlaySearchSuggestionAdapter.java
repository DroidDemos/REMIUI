package com.google.android.play.search;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.google.android.play.R;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.utils.collections.Lists;
import java.util.List;

public class PlaySearchSuggestionAdapter extends Adapter<ViewHolder> {
    private BitmapLoader mBitmapLoader;
    private PlaySearchController mController;
    private final List<PlaySearchSuggestionModel> mItems;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public PlaySearchOneSuggestion oneSuggestionView;

        public ViewHolder(PlaySearchOneSuggestion v) {
            super(v);
            this.oneSuggestionView = v;
        }
    }

    public PlaySearchSuggestionAdapter() {
        this.mItems = Lists.newArrayList();
    }

    public void setPlaySearchController(PlaySearchController playSearchController) {
        this.mController = playSearchController;
    }

    public void setBitmapLoader(BitmapLoader bitmapLoader) {
        this.mBitmapLoader = bitmapLoader;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((PlaySearchOneSuggestion) LayoutInflater.from(parent.getContext()).inflate(R.layout.play_search_one_suggestion, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        boolean showDivider = position < getItemCount() + -1;
        PlaySearchOneSuggestion view = holder.oneSuggestionView;
        final PlaySearchSuggestionModel model = (PlaySearchSuggestionModel) this.mItems.get(position);
        view.bindSuggestion(model, showDivider, this.mBitmapLoader);
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (PlaySearchSuggestionAdapter.this.mController != null) {
                    PlaySearchSuggestionAdapter.this.mController.onSuggestionClicked(model);
                }
            }
        });
    }

    public int getItemCount() {
        return this.mItems.size();
    }

    public void updateData(List<PlaySearchSuggestionModel> suggestionModels) {
        this.mItems.clear();
        this.mItems.addAll(suggestionModels);
        notifyDataSetChanged();
    }
}
