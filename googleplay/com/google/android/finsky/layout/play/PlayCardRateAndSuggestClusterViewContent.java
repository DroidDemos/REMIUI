package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayCardViewBase;

public class PlayCardRateAndSuggestClusterViewContent extends PlayCardClusterViewContent {
    private int mState;

    public interface PendingStateHandler {
        void enterPendingStateIfNecessary(boolean z);

        void exitPendingStateIfNecessary(boolean z);
    }

    public PlayCardRateAndSuggestClusterViewContent(Context context) {
        this(context, null);
    }

    public PlayCardRateAndSuggestClusterViewContent(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mState = 0;
    }

    public void setState(int state, boolean isDuringInitialLoading) {
        this.mState = state;
        refreshCards(!isDuringInitialLoading);
    }

    public void createContent(DfeApi dfeApi, NavigationManager navigationManager, BitmapLoader bitmapLoader, PlayCardDismissListener dismissListener, PlayCardHeap cardHeap, PlayStoreUiElementNode parentNode) {
        super.createContent(dfeApi, navigationManager, bitmapLoader, dismissListener, cardHeap, parentNode);
        refreshCards(false);
    }

    protected int getNumberOfTilesToBind() {
        return this.mState == 0 ? 1 : super.getNumberOfTilesToBind();
    }

    private void refreshCards(boolean animateChanges) {
        boolean exitPendingStateForOtherCards = false;
        if (getCardChildCount() != 0) {
            int tileIndex;
            for (tileIndex = 0; tileIndex < this.mMetadata.getTileCount(); tileIndex++) {
                getCardChildAt(tileIndex).setVisibility(0);
            }
            if (this.mState == 2) {
                exitPendingStateForOtherCards = true;
            }
            for (tileIndex = 1; tileIndex < this.mMetadata.getTileCount(); tileIndex++) {
                PlayCardViewBase card = getCardChildAt(tileIndex);
                if (card.getData() == null) {
                    card.bindLoading();
                }
                PendingStateHandler pendingStateHandler = (PendingStateHandler) card;
                if (exitPendingStateForOtherCards) {
                    pendingStateHandler.exitPendingStateIfNecessary(animateChanges);
                } else {
                    pendingStateHandler.enterPendingStateIfNecessary(animateChanges);
                }
            }
        }
    }
}
