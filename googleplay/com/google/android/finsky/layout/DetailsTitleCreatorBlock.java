package com.google.android.finsky.layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.DateUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.play.image.AvatarCropTransformation;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.BitmapTransformation;
import com.google.android.play.image.FifeImageView;
import java.text.ParseException;
import java.util.List;

public class DetailsTitleCreatorBlock extends RelativeLayout {
    private TextView mCreatorDate;
    private FifeImageView mCreatorImage;
    private DecoratedTextView mCreatorTitle;

    private static class ArtistAvatarTransformation implements BitmapTransformation {
        private static ArtistAvatarTransformation INSTANCE;

        static {
            INSTANCE = new ArtistAvatarTransformation();
        }

        private ArtistAvatarTransformation() {
        }

        public int getTransformationInset(int viewWidth, int viewHeight) {
            return AvatarCropTransformation.getFullAvatarCrop(FinskyApp.get().getResources()).getTransformationInset(viewWidth, viewHeight);
        }

        public Bitmap transform(Bitmap source, int viewWidth, int viewHeight) {
            int sourceWidth = source.getWidth();
            int sourceHeight = source.getHeight();
            Bitmap squareCrop = Bitmap.createBitmap(sourceHeight, sourceHeight, Config.ARGB_8888);
            new Canvas(squareCrop).drawBitmap(source, (float) ((-(sourceWidth - sourceHeight)) / 2), 0.0f, null);
            return AvatarCropTransformation.getFullAvatarCrop(FinskyApp.get().getResources()).transform(squareCrop, viewWidth, viewHeight);
        }

        public void drawFocusedOverlay(Canvas canvas, int width, int height) {
        }

        public void drawPressedOverlay(Canvas canvas, int width, int height) {
        }
    }

    public DetailsTitleCreatorBlock(Context context) {
        this(context, null);
    }

    public DetailsTitleCreatorBlock(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mCreatorImage = (FifeImageView) findViewById(R.id.creator_image);
        this.mCreatorImage.setBitmapTransformation(ArtistAvatarTransformation.INSTANCE);
        this.mCreatorTitle = (DecoratedTextView) findViewById(R.id.creator_title);
        this.mCreatorDate = (TextView) findViewById(R.id.creator_date);
    }

    public void bind(Document document, NavigationManager navigationManager, BitmapLoader bitmapLoader, FinskyEventLog eventLogger, PlayStoreUiElementNode parentNode) {
        int docType = document.getDocumentType();
        boolean hasCreatorDoc = document.hasCreatorDoc();
        if ((docType == 2 || docType == 4 || docType == 5 || hasCreatorDoc) && docType != 1) {
            if (hasCreatorDoc) {
                final Document creatorDoc = document.getCreatorDoc();
                this.mCreatorTitle.setText(creatorDoc.getTitle());
                List<Image> creatorImages = creatorDoc.getImages(0);
                if (creatorImages == null || creatorImages.size() == 0) {
                    this.mCreatorImage.setVisibility(8);
                } else {
                    Image creatorImage = (Image) creatorImages.get(0);
                    this.mCreatorImage.setImage(creatorImage.imageUrl, creatorImage.supportsFifeUrlOptions, bitmapLoader);
                    this.mCreatorImage.setVisibility(0);
                }
                if (!(TextUtils.isEmpty(creatorDoc.getDetailsUrl()) || navigationManager == null)) {
                    setFocusable(true);
                    final FinskyEventLog finskyEventLog = eventLogger;
                    final PlayStoreUiElementNode playStoreUiElementNode = parentNode;
                    final NavigationManager navigationManager2 = navigationManager;
                    setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            finskyEventLog.logClickEvent(126, null, playStoreUiElementNode);
                            navigationManager2.goToDocPage(creatorDoc);
                        }
                    });
                }
            } else {
                this.mCreatorTitle.setText(document.getCreator());
                this.mCreatorImage.setVisibility(8);
            }
            if (docType == 1) {
                BadgeUtils.configureCreatorBadge(document, bitmapLoader, this.mCreatorTitle);
            }
            if (docType == 2 || docType == 4 || docType == 5) {
                String releaseDate = null;
                if (docType == 2 || docType == 4) {
                    releaseDate = document.getAlbumDetails().details.originalReleaseDate;
                } else if (docType == 5) {
                    releaseDate = document.getBookDetails().publicationDate;
                }
                if (document.hasPreorderOffer() || TextUtils.isEmpty(releaseDate)) {
                    this.mCreatorDate.setVisibility(8);
                } else {
                    try {
                        this.mCreatorDate.setText(DateUtils.formatIso8601Date(releaseDate));
                        this.mCreatorDate.setVisibility(0);
                    } catch (ParseException e) {
                        FinskyLog.e(e, "Cannot parse ISO 8601 date", new Object[0]);
                        this.mCreatorDate.setVisibility(8);
                    }
                }
            }
            setVisibility(0);
            return;
        }
        setVisibility(8);
    }
}
