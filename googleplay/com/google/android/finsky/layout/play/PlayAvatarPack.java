package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.adapters.Recyclable;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.utils.GPlusDialogsHelper;
import com.google.android.finsky.utils.IntMath;
import com.google.android.play.image.AvatarCropTransformation;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.utils.DocV2Utils;

public class PlayAvatarPack extends ViewGroup implements Recyclable {
    private FifeImageView mAvatarPrimary;
    private FifeImageView[] mAvatarsSecondary;
    private final int mPrimarySize;

    public PlayAvatarPack(Context context) {
        this(context, null);
    }

    public PlayAvatarPack(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.PlayAvatarPack);
        this.mPrimarySize = viewAttrs.getDimensionPixelSize(0, context.getResources().getDimensionPixelSize(R.dimen.play_profile_avatar_size));
        viewAttrs.recycle();
    }

    public void setData(DocV2 primaryPerson, DocV2[] secondaryPersons, NavigationManager navigationManager, PlayStoreUiElementNode parentNode) {
        if (primaryPerson == null) {
            setVisibility(8);
            return;
        }
        int secondaryCount;
        Image image;
        final PlayStoreUiElementNode node;
        setVisibility(0);
        removeAllViews();
        BitmapLoader bitmapLoader = FinskyApp.get().getBitmapLoader();
        Resources res = getResources();
        if (secondaryPersons == null) {
            secondaryCount = 0;
        } else {
            secondaryCount = Math.min(4, IntMath.floor(secondaryPersons.length, 2) * 2);
        }
        this.mAvatarsSecondary = new FifeImageView[secondaryCount];
        if (secondaryCount > 0) {
            int i;
            for (i = 0; i < secondaryCount; i++) {
                final DocV2 finalPerson = secondaryPersons[i];
                image = DocV2Utils.getFirstImageOfType(finalPerson, 4);
                this.mAvatarsSecondary[i] = makeFifeImageView();
                this.mAvatarsSecondary[i].setImage(image.imageUrl, image.supportsFifeUrlOptions, bitmapLoader);
                this.mAvatarsSecondary[i].setContentDescription(res.getString(R.string.content_description_view_gplus_profile, new Object[]{finalPerson.title}));
                node = new GenericUiElementNode(279, finalPerson.serverLogsCookie, null, parentNode);
                parentNode.childImpression(node);
                final NavigationManager navigationManager2 = navigationManager;
                this.mAvatarsSecondary[i].setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        navigationManager2.getClickListener(new Document(finalPerson), node).onClick(view);
                    }
                });
            }
            for (i = 0; i < secondaryCount; i++) {
                addView(this.mAvatarsSecondary[(secondaryCount - i) - 1]);
            }
        }
        this.mAvatarPrimary = makeFifeImageView();
        image = DocV2Utils.getFirstImageOfType(primaryPerson, 4);
        this.mAvatarPrimary.setImage(image.imageUrl, image.supportsFifeUrlOptions, bitmapLoader);
        this.mAvatarPrimary.setContentDescription(res.getString(R.string.content_description_view_gplus_profile, new Object[]{primaryPerson.title}));
        node = new GenericUiElementNode(279, null, null, parentNode);
        parentNode.childImpression(node);
        Document primaryDocument = new Document(primaryPerson);
        if (NavigationManager.hasClickListener(primaryDocument)) {
            this.mAvatarPrimary.setOnClickListener(navigationManager.getClickListener(primaryDocument, node));
        } else {
            navigationManager2 = navigationManager;
            this.mAvatarPrimary.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    GPlusDialogsHelper.showGPlusSignUpDialog(navigationManager2.getActivePage().getFragmentManager());
                }
            });
        }
        addView(this.mAvatarPrimary);
    }

    private FifeImageView makeFifeImageView() {
        FifeImageView result = new FifeImageView(getContext());
        result.setScaleType(ScaleType.CENTER_CROP);
        result.setBitmapTransformation(AvatarCropTransformation.getFullAvatarCrop(getResources()));
        result.setHasFixedBounds(true);
        result.setFocusable(true);
        return result;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int secondaryCount;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if (this.mAvatarPrimary != null) {
            int primarySpec = MeasureSpec.makeMeasureSpec(this.mPrimarySize, 1073741824);
            this.mAvatarPrimary.measure(primarySpec, primarySpec);
        }
        if (this.mAvatarsSecondary == null) {
            secondaryCount = 0;
        } else {
            secondaryCount = this.mAvatarsSecondary.length;
        }
        if (secondaryCount > 0) {
            int spaceLeft = ((width - this.mPrimarySize) - getPaddingLeft()) - getPaddingRight();
            int secondarySize = (int) (((float) this.mPrimarySize) * 0.7f);
            if (secondaryCount * ((int) (((float) secondarySize) * 0.66999996f)) > spaceLeft) {
                secondarySize = (int) (((float) (spaceLeft / secondaryCount)) / 0.66999996f);
            }
            int secondarySpec = MeasureSpec.makeMeasureSpec(secondarySize, 1073741824);
            for (int i = 0; i < secondaryCount; i++) {
                this.mAvatarsSecondary[i].measure(secondarySpec, secondarySpec);
            }
        }
        setMeasuredDimension(width, this.mPrimarySize);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int secondaryCount;
        int width = getWidth();
        int height = getHeight();
        int primaryLeft = (width / 2) - (this.mPrimarySize / 2);
        int primaryRight = primaryLeft + this.mPrimarySize;
        if (this.mAvatarPrimary != null) {
            this.mAvatarPrimary.layout(primaryLeft, 0, primaryRight, this.mPrimarySize);
        }
        if (this.mAvatarsSecondary == null) {
            secondaryCount = 0;
        } else {
            secondaryCount = this.mAvatarsSecondary.length;
        }
        if (secondaryCount > 0) {
            int evenRightMarker = primaryLeft;
            int oddLeftMarker = primaryRight;
            for (int i = 0; i < secondaryCount; i += 2) {
                FifeImageView even = this.mAvatarsSecondary[i];
                int evenWidth = even.getMeasuredWidth();
                int evenHeight = even.getMeasuredHeight();
                int evenRight = evenRightMarker + ((int) (((float) evenWidth) * 0.33f));
                int evenTop = (height - evenHeight) / 2;
                even.layout(evenRight - evenWidth, evenTop, evenRight, evenTop + evenHeight);
                evenRightMarker = evenRight - evenWidth;
                FifeImageView odd = this.mAvatarsSecondary[i + 1];
                int oddWidth = odd.getMeasuredWidth();
                int oddHeight = odd.getMeasuredHeight();
                int oddLeft = oddLeftMarker - ((int) (((float) oddWidth) * 0.33f));
                int oddTop = (height - oddHeight) / 2;
                odd.layout(oddLeft, oddTop, oddLeft + oddWidth, oddTop + oddHeight);
                oddLeftMarker = oddLeft + oddWidth;
            }
        }
    }

    public void onRecycle() {
        if (this.mAvatarPrimary != null) {
            this.mAvatarPrimary.clearImage();
        }
        if (this.mAvatarsSecondary != null) {
            for (int i = 0; i < this.mAvatarsSecondary.length; i++) {
                if (this.mAvatarsSecondary[i] != null) {
                    this.mAvatarsSecondary[i].clearImage();
                }
            }
        }
    }
}
