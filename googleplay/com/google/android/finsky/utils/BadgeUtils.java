package com.google.android.finsky.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.DecoratedTextView;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocAnnotations.Badge;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.BitmapLoader.BitmapContainer;
import com.google.android.play.image.BitmapLoader.BitmapLoadedHandler;
import com.google.android.play.layout.PlayTextView;
import com.google.android.play.layout.StarRatingBar;

public class BadgeUtils {
    public static Image getImage(Badge badge, int imageType) {
        for (Image image : badge.image) {
            if (image.imageType == imageType) {
                return image;
            }
        }
        return null;
    }

    public static void configureCreatorBadge(Document doc, BitmapLoader bitmapLoader, DecoratedTextView textView) {
        configureBadge(doc.hasCreatorBadges() ? doc.getFirstCreatorBadge() : null, bitmapLoader, textView);
    }

    public static void configureBadge(Badge badge, BitmapLoader bitmapLoader, DecoratedTextView textView) {
        CharSequence text = textView.getText();
        if (badge != null) {
            int badgeSize = (int) textView.getTextSize();
            Image badgeImage = getImage(badge, 6);
            if (badgeImage != null) {
                textView.loadDecoration(bitmapLoader, badgeImage, badgeSize);
                textView.setContentDescription(textView.getResources().getString(R.string.content_description_item_subtitle, new Object[]{text, badge.title}));
                return;
            }
            textView.setContentDescription(text);
            return;
        }
        textView.setCompoundDrawables(null, null, null, null);
        textView.setContentDescription(text);
    }

    public static void configureContentRatingBadge(Document doc, BitmapLoader bitmapLoader, final ImageView ratingView) {
        if (doc.getDocumentType() != 1) {
            ratingView.setVisibility(8);
            return;
        }
        Badge ratingBadge = doc.getBadgeForContentRating();
        if (ratingBadge == null) {
            ratingView.setVisibility(8);
            return;
        }
        ratingView.setContentDescription(ratingBadge.title);
        bitmapLoaded(ratingView, bitmapLoader.get(ratingBadge.image[0].imageUrl, 0, 0, new BitmapLoadedHandler() {
            public void onResponse(BitmapContainer result) {
                BadgeUtils.bitmapLoaded(ratingView, result);
            }
        }));
    }

    private static void bitmapLoaded(ImageView imageView, BitmapContainer container) {
        Bitmap bitmap = container.getBitmap();
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public static void configureRatingItemSection(Document doc, StarRatingBar ratingBar, DecoratedTextView textView) {
        if (ratingBar != null) {
            ratingBar.setVisibility(4);
        }
        if (textView != null) {
            textView.setVisibility(8);
        }
        if (textView != null && doc.hasItemBadges()) {
            textView.setVisibility(0);
            Badge firstItemBadge = doc.getFirstItemBadge();
            int badgeSize = (int) textView.getTextSize();
            Image badgeImage = getImage(firstItemBadge, 6);
            if (badgeImage != null) {
                textView.loadDecoration(FinskyApp.get().getBitmapLoader(), badgeImage, badgeSize);
            }
            textView.setText(firstItemBadge.title.toUpperCase());
            textView.setContentColorStateListId(R.color.play_secondary_text, false);
            textView.setPadding(0, textView.getPaddingTop(), 0, textView.getPaddingBottom());
        } else if (textView != null && (doc.hasCensoring() || doc.hasReleaseType())) {
            configureTipperSticker(doc, textView);
            int stickerPadding = textView.getResources().getDimensionPixelSize(R.dimen.play_tipper_sticker_padding);
            textView.setPadding(stickerPadding, textView.getPaddingTop(), stickerPadding, textView.getPaddingBottom());
        } else if (textView != null && doc.getDocumentType() == 20) {
            configureReleaseDate(doc, textView);
            textView.setPadding(0, textView.getPaddingTop(), 0, textView.getPaddingBottom());
        } else if (ratingBar != null && doc.hasRating() && doc.getRatingCount() > 0) {
            ratingBar.setRating(doc.getStarRating());
            ratingBar.setVisibility(0);
        }
    }

    private static void configureReleaseDate(Document doc, PlayTextView textView) {
        if (doc.getTvEpisodeDetails() != null && !TextUtils.isEmpty(doc.getTvEpisodeDetails().releaseDate)) {
            textView.setVisibility(0);
            textView.setContentColorId(R.color.grey, false);
            textView.setText(doc.getTvEpisodeDetails().releaseDate);
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    public static void configureTipperSticker(Document doc, PlayTextView textView) {
        int stringId = -1;
        int colorId = R.color.tipper_sticker_edited;
        if (doc.hasCensoring()) {
            switch (doc.getCensoring()) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    stringId = R.string.tipper_sticker_explicit_content;
                    colorId = R.color.tipper_sticker_explicit;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    stringId = R.string.tipper_sticker_edited_content;
                    break;
            }
        }
        if (stringId == -1 && doc.hasReleaseType()) {
            switch (doc.getReleaseType()) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    stringId = R.string.tipper_sticker_remix;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    stringId = R.string.tipper_sticker_bonus_tracks;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    stringId = R.string.tipper_sticker_remastered;
                    break;
            }
        }
        if (stringId > -1) {
            textView.setVisibility(0);
            textView.setText(textView.getResources().getString(stringId).toUpperCase());
            textView.setContentColorId(colorId, true);
            textView.setCompoundDrawables(null, null, null, null);
            return;
        }
        textView.setVisibility(8);
    }
}
