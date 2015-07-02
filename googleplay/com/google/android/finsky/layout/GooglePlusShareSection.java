package com.google.android.finsky.layout;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.android.volley.toolbox.Authenticator;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.SkyjamJsonObjectRequest;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.config.G;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePlusShareSection extends RelativeLayout {
    private Document mAlbumDocument;
    private Fragment mFragment;
    private TextView mHeader;
    private boolean mIsFetching;
    private DfeList mItemListRequest;
    private PlayStoreUiElementNode mParentNode;
    private ProgressBar mProgress;
    private JsonObjectRequest mRequest;
    private RequestQueue mRequestQueue;
    private final ErrorListener mTrackListErrorListener;
    private final OnDataChangedListener mTrackListListener;
    private final ErrorListener mUnrollErrorListener;

    private static class AuthorizedSkyjamJsonObjectRequest extends SkyjamJsonObjectRequest {
        private final Authenticator mAuthenticator;
        private String mLastAuthToken;

        public AuthorizedSkyjamJsonObjectRequest(Authenticator authenticator, String url, JSONObject jsonRequest, Listener<JSONObject> listener, ErrorListener errorListener) {
            super(0, url, jsonRequest, listener, errorListener);
            this.mLastAuthToken = null;
            this.mAuthenticator = authenticator;
        }

        public void deliverError(VolleyError error) {
            super.deliverError(error);
            if (this.mLastAuthToken != null && (error instanceof AuthFailureError)) {
                this.mAuthenticator.invalidateAuthToken(this.mLastAuthToken);
                this.mLastAuthToken = null;
            }
        }

        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> headers = super.getHeaders();
            this.mLastAuthToken = this.mAuthenticator.getAuthToken();
            if (this.mLastAuthToken == null) {
                throw new AuthFailureError("Auth token is null");
            }
            headers.put("Authorization", "GoogleLogin auth=" + this.mLastAuthToken);
            return headers;
        }
    }

    public GooglePlusShareSection(Context context) {
        this(context, null);
    }

    public GooglePlusShareSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mRequest = null;
        this.mIsFetching = false;
        this.mTrackListErrorListener = new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FinskyLog.e("Unable to load child documents: %s", error.getCause());
                GooglePlusShareSection.this.updateWithDocument(null, GooglePlusShareSection.this.mAlbumDocument);
            }
        };
        this.mTrackListListener = new OnDataChangedListener() {
            public void onDataChanged() {
                Libraries libraries = FinskyApp.get().getLibraries();
                Account currentAccount = FinskyApp.get().getCurrentAccount();
                for (int i = 0; i < GooglePlusShareSection.this.mItemListRequest.getCount(); i++) {
                    Document song = (Document) GooglePlusShareSection.this.mItemListRequest.getItem(i);
                    Account songOwner = LibraryUtils.getOwnerWithCurrentAccount(song, libraries, currentAccount);
                    if (songOwner != null) {
                        GooglePlusShareSection.this.updateWithDocument(songOwner, song);
                        return;
                    }
                }
            }
        };
        this.mUnrollErrorListener = new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                GooglePlusShareSection.this.mIsFetching = false;
                GooglePlusShareSection.this.updateUi();
                FinskyLog.e("Unable to load JSON: %s", error.getCause());
            }
        };
    }

    public void onDetachedFromWindow() {
        if (this.mItemListRequest != null) {
            this.mItemListRequest.removeDataChangedListener(this.mTrackListListener);
            this.mItemListRequest.removeErrorListener(this.mTrackListErrorListener);
        }
        super.onDetachedFromWindow();
    }

    public void bind(Document document, Fragment fragment, boolean hasDetailsloaded, PlayStoreUiElementNode parentNode) {
        setVisibility(8);
        if (hasDetailsloaded && ((Boolean) G.postPurchaseSharingEnabled.get()).booleanValue() && !document.hasPreorderOffer()) {
            this.mFragment = fragment;
            this.mAlbumDocument = document;
            this.mParentNode = parentNode;
            if (isSupportedCorpus()) {
                Account owner = LibraryUtils.getOwnerWithCurrentAccount(this.mAlbumDocument, FinskyApp.get().getLibraries(), FinskyApp.get().getCurrentAccount());
                if (owner == null) {
                    String childrenListUrl = this.mAlbumDocument.getCoreContentListUrl();
                    if (!TextUtils.isEmpty(childrenListUrl)) {
                        if (this.mItemListRequest != null) {
                            this.mItemListRequest.removeDataChangedListener(this.mTrackListListener);
                            this.mItemListRequest.removeErrorListener(this.mTrackListErrorListener);
                        }
                        this.mItemListRequest = new DfeList(FinskyApp.get().getDfeApi(), childrenListUrl, false);
                        this.mItemListRequest.addDataChangedListener(this.mTrackListListener);
                        this.mItemListRequest.addErrorListener(this.mTrackListErrorListener);
                        this.mItemListRequest.startLoadItems();
                        return;
                    }
                    return;
                }
                updateWithDocument(owner, this.mAlbumDocument);
            }
        }
    }

    private void updateWithDocument(final Account owner, final Document doc) {
        if (isSupportedCorpus() && isSharingSupported(doc)) {
            setVisibility(0);
            TextView subheader = (TextView) findViewById(R.id.subheader);
            if (doc.getAlbumDetails() != null) {
                subheader.setText(R.string.share_google_plus_album);
            } else if (doc.getSongDetails() != null) {
                subheader.setText(getContext().getString(R.string.share_google_plus_track, new Object[]{doc.getTitle()}));
            }
            this.mHeader = (TextView) findViewById(R.id.header);
            this.mHeader.setText(getContext().getString(CorpusResourceUtils.getShareHeaderId(doc.getBackend())));
            this.mProgress = (ProgressBar) findViewById(R.id.loading_progress);
            setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (!GooglePlusShareSection.this.mIsFetching) {
                        GooglePlusShareSection.this.mIsFetching = true;
                        String url = String.format("https://music.google.com/music/sharepreview?storeId=%s&u=0&source=androidmarket-mobile", new Object[]{doc.getBackendDocId()});
                        GooglePlusShareSection.this.updateUi();
                        GooglePlusShareSection.this.mRequest = new AuthorizedSkyjamJsonObjectRequest(new AndroidAuthenticator(GooglePlusShareSection.this.getContext(), owner, "sj"), url, null, GooglePlusShareSection.this.createJsonListener(doc), GooglePlusShareSection.this.mUnrollErrorListener);
                        GooglePlusShareSection.this.mRequestQueue.add(GooglePlusShareSection.this.mRequest);
                        FinskyApp.get().getEventLogger().logClickEvent(273, null, GooglePlusShareSection.this.mParentNode);
                    }
                }
            });
            this.mRequestQueue = FinskyApp.get().getRequestQueue();
        }
    }

    private boolean isSupportedCorpus() {
        return this.mAlbumDocument != null && this.mAlbumDocument.getBackend() == 2;
    }

    private boolean isSharingSupported(Document doc) {
        if (getContext().getPackageManager().queryIntentActivities(buildBaseShareIntent(doc), 0).isEmpty()) {
            return false;
        }
        return true;
    }

    private Intent buildBaseShareIntent(Document doc) {
        Intent share = new Intent("com.google.android.apps.plus.SHARE_GOOGLE", Uri.parse(doc.getShareUrl()));
        share.putExtra("authAccount", FinskyApp.get().getCurrentAccountName());
        share.putExtra("com.google.android.apps.plus.VERSION", "1.00");
        return share;
    }

    private void updateUi() {
        this.mProgress.setVisibility(this.mIsFetching ? 0 : 8);
    }

    private Listener<JSONObject> createJsonListener(final Document doc) {
        return new Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                try {
                    GooglePlusShareSection.this.mIsFetching = false;
                    GooglePlusShareSection.this.updateUi();
                    if (GooglePlusShareSection.this.mFragment.isResumed()) {
                        String externalId = response.getString("externalId");
                        String url = response.getString("url");
                        Intent shareIntent = GooglePlusShareSection.this.buildBaseShareIntent(doc);
                        shareIntent.setData(Uri.parse(url));
                        shareIntent.putExtra("com.google.android.apps.plus.FOOTER", GooglePlusShareSection.this.getContext().getString(R.string.share_google_plus_footer));
                        shareIntent.putExtra("com.google.android.apps.plus.EXTERNAL_ID", externalId);
                        GooglePlusShareSection.this.mFragment.startActivityForResult(shareIntent, 0);
                    }
                } catch (JSONException e) {
                    FinskyLog.e(e, "Exception during JSON unmarshalling: %s", e.getMessage());
                }
            }
        };
    }
}
