package com.google.android.finsky.detailspage;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
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
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.SkyjamJsonObjectRequest;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.config.G;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePlusShareModule extends FinskyModule<Data> implements OnClickListener, PlayStoreUiElementNode {
    private boolean mIsDestroyed;
    private DfeList mItemListRequest;
    private boolean mNeedsRefresh;
    private PlayStoreUiElement mPlayStoreUiElement;
    private JsonObjectRequest mRequest;
    private RequestQueue mRequestQueue;
    private boolean mRequestingSkyJamAuth;

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

    protected static class Data extends ModuleData {
        public Document albumDoc;
        public boolean isReady;
        public Account owner;
        public Document songDoc;

        protected Data() {
        }
    }

    public GooglePlusShareModule() {
        this.mRequest = null;
        this.mPlayStoreUiElement = FinskyEventLog.obtainPlayStoreUiElement(273);
    }

    public int getLayoutResId() {
        return R.layout.google_plus_share_module;
    }

    public boolean readyForDisplay() {
        return this.mModuleData != null && ((Data) this.mModuleData).isReady;
    }

    public void onRestoreModuleData(ModuleData data) {
        super.onRestoreModuleData(data);
        this.mIsDestroyed = false;
        this.mRequestingSkyJamAuth = false;
        if (this.mModuleData != null && !((Data) this.mModuleData).isReady) {
            checkIfAnySongsFromAlbumAreOwned();
        }
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (((Boolean) G.postPurchaseSharingEnabled.get()).booleanValue() && !Document.isPreorderOffer(detailsDoc.getOffer(1)) && isSupportedCorpus(detailsDoc) && isSharingSupported(detailsDoc)) {
            this.mRequestQueue = FinskyApp.get().getRequestQueue();
            if (this.mModuleData == null) {
                this.mModuleData = new Data();
                ((Data) this.mModuleData).albumDoc = detailsDoc;
                ((Data) this.mModuleData).owner = LibraryUtils.getOwnerWithCurrentAccount(((Data) this.mModuleData).albumDoc, FinskyApp.get().getLibraries(), FinskyApp.get().getCurrentAccount());
                if (((Data) this.mModuleData).owner == null) {
                    checkIfAnySongsFromAlbumAreOwned();
                } else {
                    ((Data) this.mModuleData).isReady = true;
                }
            }
        }
    }

    public void bindView(View view) {
        GooglePlusShareModuleLayout moduleView = (GooglePlusShareModuleLayout) view;
        if (!moduleView.hasBinded() || this.mNeedsRefresh) {
            Document doc = ((Data) this.mModuleData).songDoc == null ? ((Data) this.mModuleData).albumDoc : ((Data) this.mModuleData).songDoc;
            String headerText = this.mContext.getString(CorpusResourceUtils.getShareHeaderId(doc.getBackend()));
            String subHeaderText = null;
            if (doc.getAlbumDetails() != null) {
                subHeaderText = this.mContext.getString(R.string.share_google_plus_album);
            } else if (doc.getSongDetails() != null) {
                subHeaderText = this.mContext.getString(R.string.share_google_plus_track, new Object[]{doc.getTitle()});
            }
            moduleView.bind(headerText, subHeaderText, this, this.mRequestingSkyJamAuth);
            this.mNeedsRefresh = false;
        }
    }

    public void onDestroyModule() {
        this.mIsDestroyed = true;
    }

    public void onClick(View v) {
        if (!this.mRequestingSkyJamAuth) {
            final Document doc = ((Data) this.mModuleData).songDoc == null ? ((Data) this.mModuleData).albumDoc : ((Data) this.mModuleData).songDoc;
            this.mRequestingSkyJamAuth = true;
            String url = String.format("https://music.google.com/music/sharepreview?storeId=%s&u=0&source=androidmarket-mobile", new Object[]{doc.getBackendDocId()});
            refreshModule();
            this.mRequest = new AuthorizedSkyjamJsonObjectRequest(new AndroidAuthenticator(this.mContext, ((Data) this.mModuleData).owner, "sj"), url, null, new Listener<JSONObject>() {
                public void onResponse(JSONObject response) {
                    try {
                        GooglePlusShareModule.this.mRequestingSkyJamAuth = false;
                        GooglePlusShareModule.this.refreshModule();
                        if (!GooglePlusShareModule.this.mIsDestroyed) {
                            String externalId = response.getString("externalId");
                            String url = response.getString("url");
                            Intent shareIntent = GooglePlusShareModule.buildBaseShareIntent(doc);
                            shareIntent.setData(Uri.parse(url));
                            shareIntent.putExtra("com.google.android.apps.plus.FOOTER", GooglePlusShareModule.this.mContext.getString(R.string.share_google_plus_footer));
                            shareIntent.putExtra("com.google.android.apps.plus.EXTERNAL_ID", externalId);
                            GooglePlusShareModule.this.mDetailsFragment2.startActivityForResult(shareIntent, 0);
                        }
                    } catch (JSONException e) {
                        FinskyLog.e(e, "Exception during JSON unmarshalling: %s", e.getMessage());
                    }
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    GooglePlusShareModule.this.mRequestingSkyJamAuth = false;
                    GooglePlusShareModule.this.refreshModule();
                    FinskyLog.e("Unable to load JSON: %s", error.getCause());
                }
            });
            this.mRequestQueue.add(this.mRequest);
            FinskyApp.get().getEventLogger().logClickEvent(273, null, getParentNode());
        }
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mPlayStoreUiElement;
    }

    public PlayStoreUiElementNode getParentNode() {
        return this.mParentNode;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyEventLog.childImpression(this, childNode);
    }

    private void checkIfAnySongsFromAlbumAreOwned() {
        String childrenListUrl = ((Data) this.mModuleData).albumDoc.getCoreContentListUrl();
        if (!TextUtils.isEmpty(childrenListUrl)) {
            if (this.mItemListRequest != null) {
                this.mItemListRequest = null;
            }
            this.mItemListRequest = new DfeList(FinskyApp.get().getDfeApi(), childrenListUrl, false);
            this.mItemListRequest.addDataChangedListener(new OnDataChangedListener() {
                public void onDataChanged() {
                    Libraries libraries = FinskyApp.get().getLibraries();
                    Account currentAccount = FinskyApp.get().getCurrentAccount();
                    for (int i = 0; i < GooglePlusShareModule.this.mItemListRequest.getCount(); i++) {
                        Document song = (Document) GooglePlusShareModule.this.mItemListRequest.getItem(i);
                        Account songOwner = LibraryUtils.getOwnerWithCurrentAccount(song, libraries, currentAccount);
                        if (songOwner != null) {
                            ((Data) GooglePlusShareModule.this.mModuleData).owner = songOwner;
                            ((Data) GooglePlusShareModule.this.mModuleData).songDoc = song;
                            ((Data) GooglePlusShareModule.this.mModuleData).isReady = true;
                            GooglePlusShareModule.this.refreshModule();
                            return;
                        }
                    }
                }
            });
            this.mItemListRequest.addErrorListener(new ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    FinskyLog.e("Unable to load child documents: %s", volleyError.getCause());
                }
            });
            this.mItemListRequest.startLoadItems();
        }
    }

    private void refreshModule() {
        if (!this.mIsDestroyed) {
            this.mFinskyModuleController.refreshModule(this, true);
            this.mNeedsRefresh = true;
        }
    }

    private static boolean isSupportedCorpus(Document document) {
        return document != null && document.getBackend() == 2;
    }

    private static boolean isSharingSupported(Document doc) {
        if (FinskyApp.get().getPackageManager().queryIntentActivities(buildBaseShareIntent(doc), 0).isEmpty()) {
            return false;
        }
        return true;
    }

    private static Intent buildBaseShareIntent(Document doc) {
        Intent share = new Intent("com.google.android.apps.plus.SHARE_GOOGLE", Uri.parse(doc.getShareUrl()));
        share.putExtra("authAccount", FinskyApp.get().getCurrentAccountName());
        share.putExtra("com.google.android.apps.plus.VERSION", "1.00");
        return share;
    }
}
