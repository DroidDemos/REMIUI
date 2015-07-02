package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.PlayCardImageTypeSequence;
import com.google.android.play.layout.PlayCardViewBase;

public class PlayEditorialNonAppCardView extends PlayCardViewBase implements PlayCardImageTypeSequence {
    private static final int[] IMAGE_TYPES;

    static {
        IMAGE_TYPES = new int[]{4, 0, 2};
    }

    public PlayEditorialNonAppCardView(Context context) {
        this(context, null);
    }

    public PlayEditorialNonAppCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getCardType() {
        return 6;
    }

    public int[] getImageTypePreference() {
        return IMAGE_TYPES;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Document doc = (Document) getData();
        this.mThumbnailAspectRatio = PlayCardClusterMetadata.getAspectRatio(doc == null ? 6 : doc.getDocumentType());
        MarginLayoutParams thumbnailLp = (MarginLayoutParams) this.mThumbnail.getLayoutParams();
        thumbnailLp.width = (int) (((float) thumbnailLp.height) / this.mThumbnailAspectRatio);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
