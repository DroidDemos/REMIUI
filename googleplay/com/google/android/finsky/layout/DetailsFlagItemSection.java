package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.DetailsBylinesSection.DetailsBylineEntry;
import com.google.android.finsky.layout.DetailsSectionStack.NoBottomSeparator;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;

public class DetailsFlagItemSection extends LinearLayout implements NoBottomSeparator {
    private boolean mBinded;
    private DetailsBylinesCell mFlagItemView;

    public DetailsFlagItemSection(Context context) {
        this(context, null);
    }

    public DetailsFlagItemSection(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mFlagItemView = (DetailsBylinesCell) findViewById(R.id.flag_row);
    }

    public void bind(final Document doc, final NavigationManager navigationManager, boolean hasDetailsLoaded, final PlayStoreUiElementNode parentNode) {
        if ((doc.getBackend() == 3 || doc.getBackend() == 2) && hasDetailsLoaded) {
            this.mFlagItemView.populate(new DetailsBylineEntry(R.string.flagging_title, R.drawable.ic_flagcontent, new OnClickListener() {
                public void onClick(View v) {
                    navigationManager.goToFlagContent(doc.getDetailsUrl());
                    FinskyApp.get().getEventLogger().logClickEvent(207, null, parentNode);
                }
            }));
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        this.mBinded = true;
    }

    public boolean hasBinded() {
        return this.mBinded;
    }
}
