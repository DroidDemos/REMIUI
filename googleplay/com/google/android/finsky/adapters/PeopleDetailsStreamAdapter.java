package com.google.android.finsky.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.layout.IdentifiableTextView;
import com.google.android.finsky.layout.play.PeopleDetailsProfileInfoView;
import com.google.android.finsky.layout.play.PlayCardDismissListener;
import com.google.android.finsky.layout.play.PlayRecyclerView;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.layout.play.WarmWelcomeCard;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocDetails.PersonDetails;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.play.image.BitmapLoader;

public class PeopleDetailsStreamAdapter extends CardRecyclerViewAdapter {
    private final boolean mIsShowingOwnProfile;
    private boolean mIsShowingWarmWelcome;
    private final Document mPlusDoc;
    private final int mWarmWelcomeCardColumns;
    private final boolean mWarmWelcomeHideGraphic;

    public PeopleDetailsStreamAdapter(Document plusDoc, Context context, DfeApi dfeApi, NavigationManager navigationManager, BitmapLoader bitmapLoader, DfeToc toc, ClientMutationCache clientMutationCache, DfeList dfeList, boolean isRestoring, PlayStoreUiElementNode parentNode) {
        super(context, dfeApi, navigationManager, bitmapLoader, toc, clientMutationCache, dfeList, null, plusDoc.getCoreContentHeader(), isRestoring, true, 2, parentNode);
        this.mPlusDoc = plusDoc;
        PersonDetails personDetails = this.mPlusDoc.getPersonDetails();
        boolean z = personDetails != null && personDetails.personIsRequester;
        this.mIsShowingOwnProfile = z;
        Resources res = context.getResources();
        this.mWarmWelcomeCardColumns = res.getInteger(R.integer.warm_welcome_card_columns);
        z = this.mWarmWelcomeCardColumns == 1 && !res.getBoolean(R.bool.play_warm_welcome_single_column_shows_graphic);
        this.mWarmWelcomeHideGraphic = z;
        z = this.mIsShowingOwnProfile && !((Boolean) FinskyPreferences.warmWelcomeOwnProfileAcknowledged.get()).booleanValue();
        this.mIsShowingWarmWelcome = z;
    }

    protected boolean hasExtraLeadingSpacer() {
        return false;
    }

    protected boolean shouldHidePlainHeaderDuringInitialLoading() {
        return true;
    }

    protected boolean shouldHidePlainHeaderOnEmpty() {
        return true;
    }

    public int getDataRowsCount() {
        return super.getDataRowsCount() + getThisAdapterPrependedRowsCount();
    }

    public int getPrependedRowsCount() {
        return super.getPrependedRowsCount() + getThisAdapterPrependedRowsCount();
    }

    private int getThisAdapterPrependedRowsCount() {
        int numPrependedRows = 0 + 1;
        if (this.mIsShowingWarmWelcome) {
            numPrependedRows++;
        }
        if (this.mItems.size() == 0) {
            return numPrependedRows + 1;
        }
        return numPrependedRows;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                view = inflate(R.layout.people_details_profile_info, parent, false);
                break;
            case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                view = inflate(R.layout.profile_no_activity, parent, false);
                break;
            case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                view = inflate(this.mWarmWelcomeCardColumns == 1 ? R.layout.warm_welcome_card_single_column : R.layout.warm_welcome_card_double_column, parent, false);
                break;
            default:
                return super.onCreateViewHolder(parent, viewType);
        }
        return new PlayRecyclerView.ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        int itemViewType = viewHolder.getItemViewType();
        View view = viewHolder.itemView;
        switch (itemViewType) {
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                bindProfileInfoView(view);
                return;
            case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                bindEmptyStateView(view);
                return;
            case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                bindWarmWelcomeCardView(view, position);
                return;
            default:
                super.onBindViewHolder(viewHolder, position);
                return;
        }
    }

    public int getItemViewType(int position) {
        if (position == 0) {
            return 22;
        }
        if (position == 1) {
            return 25;
        }
        position--;
        if (this.mIsShowingWarmWelcome) {
            if (position == 1) {
                return 27;
            }
            position--;
        }
        if (this.mItems.size() == 0) {
            return 26;
        }
        return super.getItemViewType(position);
    }

    private void bindProfileInfoView(View view) {
        PeopleDetailsProfileInfoView profileInfoView = (PeopleDetailsProfileInfoView) view;
        profileInfoView.setIdentifier("profile_info");
        profileInfoView.bind(this.mPlusDoc, this.mParentNode, this.mCardContentPadding);
    }

    private void bindWarmWelcomeCardView(View view, final int position) {
        Image image;
        WarmWelcomeCard warmWelcomeCard = (WarmWelcomeCard) view;
        String title = this.mContext.getString(R.string.warm_welcome_own_profile_title);
        String body = this.mContext.getString(R.string.warm_welcome_own_profile_body);
        String buttonText = this.mContext.getString(R.string.warm_welcome_own_profile_got_it);
        Image warmWelcomeGraphic = new Image();
        warmWelcomeGraphic.imageType = 4;
        warmWelcomeGraphic.hasImageType = true;
        warmWelcomeGraphic.imageUrl = (String) G.peoplePageWarmWelcomeGraphicUrl.get();
        warmWelcomeGraphic.hasImageUrl = true;
        Image warmWelcomeButtonIcon = new Image();
        warmWelcomeButtonIcon.imageType = 4;
        warmWelcomeButtonIcon.hasImageType = true;
        warmWelcomeButtonIcon.imageUrl = (String) G.peoplePageWarmWelcomeButtonIconUrl.get();
        warmWelcomeButtonIcon.hasImageUrl = true;
        if (this.mWarmWelcomeHideGraphic) {
            image = null;
        } else {
            image = warmWelcomeGraphic;
        }
        warmWelcomeCard.configureContent(title, body, image, 9, this.mParentNode, null);
        final WarmWelcomeCard finalWarmWelcomeCard = warmWelcomeCard;
        warmWelcomeCard.configureDismissAction(buttonText, warmWelcomeButtonIcon, new OnClickListener() {
            public void onClick(View v) {
                FinskyApp.get().getEventLogger().logClickEvent(1231, null, finalWarmWelcomeCard);
                FinskyPreferences.warmWelcomeOwnProfileAcknowledged.put(Boolean.TRUE);
                PeopleDetailsStreamAdapter.this.mIsShowingWarmWelcome = false;
                PeopleDetailsStreamAdapter.this.notifyItemRemoved(position);
            }
        });
        warmWelcomeCard.hideSecondaryAction();
        warmWelcomeCard.setPadding(this.mCardContentPadding, 0, this.mCardContentPadding, 0);
        warmWelcomeCard.setIdentifier("self_warm_welcome");
    }

    private void bindEmptyStateView(View view) {
        IdentifiableTextView result = (IdentifiableTextView) view;
        result.setText(this.mIsShowingOwnProfile ? R.string.own_profile_empty_message : R.string.other_profile_empty_message);
        int padding = view.getResources().getDimensionPixelSize(R.dimen.play_profile_empty_state_hmargin) + this.mCardContentPadding;
        result.setPadding(padding, view.getPaddingTop(), padding, view.getPaddingBottom());
        result.setIdentifier("empty_state");
    }

    protected boolean isDismissed(Document document) {
        return false;
    }

    protected PlayCardDismissListener getPlayCardDismissListener() {
        return null;
    }
}
