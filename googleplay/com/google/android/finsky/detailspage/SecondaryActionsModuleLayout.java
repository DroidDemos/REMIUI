package com.google.android.finsky.detailspage;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.utils.CorpusResourceUtils;
import java.text.NumberFormat;

public class SecondaryActionsModuleLayout extends LinearLayout implements OnClickListener {
    private static final NumberFormat sCountFormatter;
    private View mPlusOneButton;
    private TextView mPlusOneIcon;
    private SecondaryActionsClickListener mSecondaryActionsClickListener;
    private View mShareButton;
    private View mWishlistButton;
    private ImageView mWishlistButtonIcon;

    public interface SecondaryActionsClickListener {
        void onPlusOneClick(View view);

        void onShareClick(View view);

        void onWishlistClick(View view);
    }

    static {
        sCountFormatter = NumberFormat.getIntegerInstance();
    }

    public SecondaryActionsModuleLayout(Context context) {
        this(context, null);
    }

    public SecondaryActionsModuleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mWishlistButton = findViewById(R.id.wishlist_button);
        this.mWishlistButtonIcon = (ImageView) findViewById(R.id.wishlist_button_icon);
        this.mShareButton = findViewById(R.id.share_button);
        this.mPlusOneButton = findViewById(R.id.plus_one_button);
        this.mPlusOneIcon = (TextView) findViewById(R.id.plus_one_button_icon);
        this.mWishlistButton.setOnClickListener(this);
        this.mShareButton.setOnClickListener(this);
        this.mPlusOneButton.setOnClickListener(this);
    }

    public void bind(boolean showWishlistButton, boolean isInWishlist, int backend, boolean showShareButton, boolean showPlusOneButton, long plusOneCount, boolean isPlusOned, SecondaryActionsClickListener clickListener) {
        if (showWishlistButton || showShareButton || showPlusOneButton) {
            setVisibility(0);
            this.mSecondaryActionsClickListener = clickListener;
            Resources res = getResources();
            if (showWishlistButton) {
                this.mWishlistButton.setVisibility(0);
                if (isInWishlist) {
                    this.mWishlistButtonIcon.setImageResource(CorpusResourceUtils.getWishlistOnDrawable(backend));
                    this.mWishlistButton.setContentDescription(res.getString(R.string.content_description_wishlist_remove));
                } else {
                    this.mWishlistButtonIcon.setImageResource(R.drawable.ic_menu_wish_off);
                    this.mWishlistButton.setContentDescription(res.getString(R.string.content_description_wishlist_add));
                }
            } else {
                this.mWishlistButton.setVisibility(8);
            }
            if (showShareButton) {
                this.mShareButton.setVisibility(0);
            } else {
                this.mShareButton.setVisibility(8);
            }
            if (showPlusOneButton) {
                long plusOneCountDisplay;
                this.mPlusOneButton.setVisibility(0);
                if (plusOneCount == 0) {
                    plusOneCountDisplay = 1;
                } else {
                    plusOneCountDisplay = plusOneCount;
                }
                this.mPlusOneIcon.setText(res.getString(R.string.details_secondary_action_plus_one_icon_text, new Object[]{sCountFormatter.format(plusOneCountDisplay)}));
                if (isPlusOned) {
                    this.mPlusOneIcon.setBackgroundResource(R.drawable.plus_one_icon_on);
                    this.mPlusOneIcon.setTextColor(res.getColor(R.color.white));
                    this.mPlusOneButton.setContentDescription(res.getString(R.string.content_description_details_secondary_action_plus_one_set, new Object[]{Long.valueOf(plusOneCount)}));
                    return;
                }
                this.mPlusOneIcon.setBackgroundResource(R.drawable.plus_one_icon_off);
                this.mPlusOneIcon.setTextColor(res.getColor(R.color.play_fg_primary));
                this.mPlusOneButton.setContentDescription(res.getString(R.string.content_description_details_secondary_action_plus_one, new Object[]{Long.valueOf(plusOneCount)}));
                return;
            }
            this.mPlusOneButton.setVisibility(8);
            return;
        }
        setVisibility(8);
    }

    public void onClick(View view) {
        if (this.mSecondaryActionsClickListener != null) {
            if (view == this.mWishlistButton) {
                this.mSecondaryActionsClickListener.onWishlistClick(view);
            }
            if (view == this.mShareButton) {
                this.mSecondaryActionsClickListener.onShareClick(view);
            }
            if (view == this.mPlusOneButton) {
                this.mSecondaryActionsClickListener.onPlusOneClick(view);
            }
        }
    }
}
