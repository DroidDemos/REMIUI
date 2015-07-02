package com.google.android.finsky.layout;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.PlayCardUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayCardViewBase;

public class FreeSongOfTheDayAlbumView extends LinearLayout implements OnDataChangedListener {
    private PlayCardViewBase mAlbumCard;
    private BitmapLoader mBitmapLoader;
    private DfeDetails mDetailsData;
    protected DfeApi mDfeApi;
    public TextView mHeader;
    protected NavigationManager mNavigationManager;
    private Document mParentDoc;
    private PlayStoreUiElementNode mParentNode;
    private String mUrl;

    public FreeSongOfTheDayAlbumView(Context context) {
        this(context, null);
    }

    public FreeSongOfTheDayAlbumView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mAlbumCard = (PlayCardViewBase) findViewById(R.id.play_card);
        this.mHeader = (TextView) findViewById(R.id.header);
    }

    public void init(DfeApi api, NavigationManager navManager, BitmapLoader bitmapLoader) {
        this.mDfeApi = api;
        this.mNavigationManager = navManager;
        this.mBitmapLoader = bitmapLoader;
    }

    public void bind(Document parentDoc, String url, PlayStoreUiElementNode parentNode) {
        this.mHeader.setText(R.string.music_from_album);
        this.mParentDoc = parentDoc;
        this.mUrl = url;
        this.mParentNode = parentNode;
        setupView();
    }

    private void detachListeners() {
        if (this.mDetailsData != null) {
            this.mDetailsData.removeDataChangedListener(this);
        }
    }

    public void setupView() {
        if (TextUtils.isEmpty(this.mUrl)) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        detachListeners();
        this.mDetailsData = new DfeDetails(this.mDfeApi, this.mUrl);
        attachToInternalRequest();
    }

    private void attachToInternalRequest() {
        this.mDetailsData.addDataChangedListener(this);
        if (this.mDetailsData.getDocument() != null) {
            setVisibility(0);
            prepareAndPopulateContent();
            return;
        }
        this.mAlbumCard.bindLoading();
    }

    public void onDataChanged() {
        if (this.mDetailsData == null || !this.mDetailsData.isReady() || this.mDetailsData.getDocument() == null) {
            setVisibility(8);
        } else {
            prepareAndPopulateContent();
        }
    }

    private void prepareAndPopulateContent() {
        PlayCardUtils.bindCard(this.mAlbumCard, this.mDetailsData.getDocument(), this.mParentDoc.getDocId(), this.mBitmapLoader, this.mNavigationManager, this.mParentNode);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        detachListeners();
    }
}
