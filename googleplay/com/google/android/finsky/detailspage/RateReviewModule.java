package com.google.android.finsky.detailspage;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import com.android.vending.R;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.RateReviewActivity;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.detailspage.RateReviewModuleLayout.RateReviewClickListener;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.finsky.protos.PlusProfile.PlusProfileResponse;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.RateReviewHelper;
import com.google.android.finsky.utils.RateReviewHelper.CheckAndConfirmGPlusListener;
import com.google.android.finsky.utils.RateReviewHelper.RateReviewListener;

public class RateReviewModule extends FinskyModule<Data> implements RateReviewClickListener, Listener {
    private boolean mIsDestroyed;
    private boolean mNeedsRefresh;

    protected static class Data extends ModuleData {
        boolean canRate;
        Review currentReview;
        Document detailsDoc;
        boolean hasLoadedDocV2;
        Review originalReview;
        DocV2 plusDocV2;

        protected Data() {
        }
    }

    public int getLayoutResId() {
        return R.layout.rate_review_module;
    }

    public boolean readyForDisplay() {
        return this.mModuleData != null && ((Data) this.mModuleData).canRate && ((Data) this.mModuleData).hasLoadedDocV2;
    }

    public void onRestoreModuleData(ModuleData savedModuleData) {
        super.onRestoreModuleData(savedModuleData);
        if (this.mModuleData != null) {
            this.mLibraries.addListener(this);
            if (!((Data) this.mModuleData).hasLoadedDocV2 && ((Data) this.mModuleData).canRate) {
                loadPlusProfile();
            }
        }
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (hasDetailsLoaded && FinskyApp.get().getToc().isGplusSignupEnabled() && this.mModuleData == null) {
            this.mModuleData = new Data();
            ((Data) this.mModuleData).detailsDoc = socialDetailsDoc;
            ((Data) this.mModuleData).canRate = DocUtils.canRate(this.mLibraries, ((Data) this.mModuleData).detailsDoc);
            ((Data) this.mModuleData).originalReview = socialDfeDetails.getUserReview();
            Data data = (Data) this.mModuleData;
            data.currentReview = FinskyApp.get().getClientMutationCache(FinskyApp.get().getCurrentAccountName()).getCachedReview(socialDetailsDoc.getDocId(), ((Data) this.mModuleData).originalReview);
            this.mLibraries.addListener(this);
            if (((Data) this.mModuleData).canRate) {
                loadPlusProfile();
            }
        }
    }

    private void loadPlusProfile() {
        FinskyApp.get().getPlayDfeApi().getPlusProfile(new Response.Listener<PlusProfileResponse>() {
            public void onResponse(PlusProfileResponse response) {
                ((Data) RateReviewModule.this.mModuleData).hasLoadedDocV2 = true;
                ((Data) RateReviewModule.this.mModuleData).plusDocV2 = response.partialUserProfile;
                if (!RateReviewModule.this.mIsDestroyed) {
                    RateReviewModule.this.refreshReview();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError e) {
            }
        }, true);
    }

    public void bindView(View view) {
        RateReviewModuleLayout rateReviewModuleLayout = (RateReviewModuleLayout) view;
        if (!rateReviewModuleLayout.hasBinded() || this.mNeedsRefresh) {
            rateReviewModuleLayout.bind(((Data) this.mModuleData).currentReview, ((Data) this.mModuleData).detailsDoc, ((Data) this.mModuleData).plusDocV2, this, this.mNavigationManager, this.mParentNode);
            this.mNeedsRefresh = false;
        }
    }

    public void onDestroyModule() {
        this.mLibraries.removeListener(this);
        this.mIsDestroyed = true;
    }

    public void onRatingClicked(int rating) {
        FinskyApp.get().getEventLogger().logClickEvent(1201, null, this.mParentNode);
        launchReviewsDialog(this.mDetailsFragment2, ((Data) this.mModuleData).currentReview, rating);
    }

    private void launchReviewsDialog(Fragment fragment, final Review review, final int prefilledRating) {
        RateReviewHelper.checkAndConfirmGPlus(fragment.getActivity(), new CheckAndConfirmGPlusListener() {
            public void onCheckAndConfirmGPlusPassed(Document plusDoc) {
                if (!RateReviewModule.this.mIsDestroyed) {
                    RateReviewModule.this.mDetailsFragment2.startActivityForResult(RateReviewActivity.createIntent(FinskyApp.get().getCurrentAccountName(), ((Data) RateReviewModule.this.mModuleData).detailsDoc, plusDoc, review, prefilledRating, false), 43);
                }
            }

            public void onCheckAndConfirmGPlusFailed() {
                if (!RateReviewModule.this.mIsDestroyed) {
                    RateReviewModule.this.refreshReview();
                }
            }
        });
    }

    public void onEditClicked() {
        if (((Data) this.mModuleData).currentReview != null) {
            FinskyApp.get().getEventLogger().logClickEvent(1202, null, this.mParentNode);
            launchReviewsDialog(this.mDetailsFragment2, ((Data) this.mModuleData).currentReview, ((Data) this.mModuleData).currentReview.starRating);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String currentAccountName = FinskyApp.get().getCurrentAccountName();
        switch (resultCode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                RateReviewHelper.updateReview(currentAccountName, ((Data) this.mModuleData).detailsDoc.getDocId(), ((Data) this.mModuleData).detailsDoc.getDetailsUrl(), data.getIntExtra("rating", 0), data.getStringExtra("review_title"), data.getStringExtra("review_comment"), (Document) data.getParcelableExtra("author"), this.mContext, new RateReviewListener() {
                    public void onRateReviewCommitted(int rating, String title, String content) {
                        RateReviewModule.this.refreshReview();
                    }

                    public void onRateReviewFailed() {
                        RateReviewModule.this.refreshReview();
                    }
                }, 1203);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                RateReviewHelper.deleteReview(currentAccountName, ((Data) this.mModuleData).detailsDoc.getDocId(), ((Data) this.mModuleData).detailsDoc.getDetailsUrl(), this.mContext, new RateReviewListener() {
                    public void onRateReviewCommitted(int rating, String title, String content) {
                        RateReviewModule.this.refreshReview();
                    }

                    public void onRateReviewFailed() {
                        RateReviewModule.this.refreshReview();
                    }
                });
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                refreshReview();
                return;
            default:
                return;
        }
    }

    public void onAllLibrariesLoaded() {
    }

    public void onLibraryContentsChanged(AccountLibrary library) {
        if (this.mModuleData != null && !((Data) this.mModuleData).canRate) {
            ((Data) this.mModuleData).canRate = DocUtils.canRate(this.mLibraries, ((Data) this.mModuleData).detailsDoc);
            if (((Data) this.mModuleData).canRate) {
                loadPlusProfile();
            }
        }
    }

    private void refreshReview() {
        Data data = (Data) this.mModuleData;
        data.currentReview = FinskyApp.get().getClientMutationCache(FinskyApp.get().getCurrentAccountName()).getCachedReview(((Data) this.mModuleData).detailsDoc.getDocId(), ((Data) this.mModuleData).originalReview);
        if (!this.mIsDestroyed) {
            this.mNeedsRefresh = true;
            this.mFinskyModuleController.refreshModule(this, true);
        }
    }
}
