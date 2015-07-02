package com.google.android.finsky.detailspage;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import com.android.vending.R;

public class ArtistClusterModuleLayout extends CardClusterModuleLayout {
    public ArtistClusterModuleLayout(Context context) {
        super(context);
    }

    public ArtistClusterModuleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected int getCardLayoutId(Resources res) {
        return R.layout.play_card_artist;
    }

    protected int getMaxItemsPerRow(Resources res) {
        return res.getInteger(R.integer.music_item_rows);
    }
}
