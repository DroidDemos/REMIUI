package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.android.vending.R;
import com.google.android.finsky.adapters.Recyclable;
import com.google.android.finsky.layout.HeroGraphicView;
import com.google.android.finsky.layout.IdentifiableFrameLayout;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout.StreamSpacer;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.DocumentV2.DocV2;

public class PlaySocialHeader extends IdentifiableFrameLayout implements Recyclable, StreamSpacer {
    private PlayAvatarPack mAvatarPack;
    private final int mLeadingSpacerHeight;

    public PlaySocialHeader(Context context) {
        this(context, null);
    }

    public PlaySocialHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mLeadingSpacerHeight = FinskyHeaderListLayout.getMinimumHeaderHeight(context, 2, 0);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mAvatarPack = (PlayAvatarPack) findViewById(R.id.avatar_pack);
    }

    public void bind(DocV2 primaryPerson, DocV2[] secondaryPersons, NavigationManager navigationManager, PlayStoreUiElementNode parentNode) {
        this.mAvatarPack.setData(primaryPerson, secondaryPersons, navigationManager, parentNode);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int coverHeight = HeroGraphicView.getSpacerHeight(getContext(), width, true, 0.0f) - this.mLeadingSpacerHeight;
        this.mAvatarPack.measure(widthMeasureSpec, 0);
        setMeasuredDimension(width, coverHeight + ((int) (((float) this.mAvatarPack.getMeasuredHeight()) * 0.3f)));
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int width = getWidth();
        int height = getHeight();
        this.mAvatarPack.layout(0, height - this.mAvatarPack.getMeasuredHeight(), width, height);
    }

    public void onRecycle() {
        this.mAvatarPack.onRecycle();
    }
}
