package com.google.android.finsky.layout.play;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.utils.DocV2Utils;

public class PlayCardEmptyClusterView extends PlayClusterView {
    private FifeImageView mEmptyImage;
    private TextView mEmptyText;

    public PlayCardEmptyClusterView(Context context) {
        this(context, null);
    }

    public PlayCardEmptyClusterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mEmptyImage = (FifeImageView) findViewById(R.id.empty_image);
        this.mEmptyText = (TextView) findViewById(R.id.empty_text);
    }

    public void createContent(Document clusterDocument, PlayStoreUiElementNode parentNode) {
        configureLogging(clusterDocument.getServerLogsCookie(), parentNode);
        String emptyMessage = clusterDocument.hasEmptyContainer() ? clusterDocument.getEmptyContainer().emptyMessage : null;
        Image emptyImage = DocV2Utils.getFirstImageOfType(clusterDocument.getBackingDocV2(), 4);
        if (TextUtils.isEmpty(emptyMessage)) {
            this.mEmptyText.setVisibility(8);
        } else {
            this.mEmptyText.setText(Html.fromHtml(emptyMessage));
            this.mEmptyText.setVisibility(0);
        }
        if (emptyImage != null) {
            this.mEmptyImage.setImage(emptyImage.imageUrl, emptyImage.supportsFifeUrlOptions, FinskyApp.get().getBitmapLoader());
            this.mEmptyImage.setVisibility(0);
        } else {
            this.mEmptyImage.setVisibility(8);
        }
        logEmptyClusterImpression();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = getPaddingTop() + getPaddingBottom();
        if (this.mHeader.getVisibility() != 8) {
            this.mHeader.measure(widthMeasureSpec, 0);
            height += this.mHeader.getMeasuredHeight();
        }
        if (this.mEmptyImage.getVisibility() != 8) {
            MarginLayoutParams imageLp = (MarginLayoutParams) this.mEmptyImage.getLayoutParams();
            this.mEmptyImage.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(imageLp.height, 1073741824));
            height += (imageLp.topMargin + this.mEmptyImage.getMeasuredHeight()) + imageLp.bottomMargin;
        }
        this.mEmptyText.measure(MeasureSpec.makeMeasureSpec(width / 2, 1073741824), 0);
        setMeasuredDimension(width, height + this.mEmptyText.getMeasuredHeight());
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int y = getPaddingTop();
        if (this.mHeader.getVisibility() != 8) {
            this.mHeader.layout(0, y, width, this.mHeader.getMeasuredHeight() + y);
            y += this.mHeader.getMeasuredHeight();
        }
        if (this.mEmptyImage.getVisibility() != 8) {
            MarginLayoutParams imageLp = (MarginLayoutParams) this.mEmptyImage.getLayoutParams();
            y += imageLp.topMargin;
            this.mEmptyImage.layout(0, y, width, this.mEmptyImage.getMeasuredHeight() + y);
            y += this.mEmptyImage.getMeasuredHeight() + imageLp.bottomMargin;
        }
        int textWidth = this.mEmptyText.getMeasuredWidth();
        int textX = (width - textWidth) / 2;
        this.mEmptyText.layout(textX, y, textX + textWidth, this.mEmptyText.getMeasuredHeight() + y);
    }

    public void onRecycle() {
        super.onRecycle();
        this.mEmptyImage.clearImage();
    }

    protected int getPlayStoreUiElementType() {
        return 416;
    }
}
