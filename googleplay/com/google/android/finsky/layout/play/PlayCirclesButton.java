package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.model.CirclesModel;
import com.google.android.finsky.model.CirclesModel.CirclesModelListener;
import com.google.android.finsky.utils.GPlusUtils;
import com.google.android.gms.common.people.data.AudienceMember;
import java.util.List;

public class PlayCirclesButton extends ProfileButton implements OnClickListener, CirclesModelListener {
    private CirclesModel mCircleModel;
    private final boolean mIsFlat;
    private PlayStoreUiElementNode mParentNode;

    public PlayCirclesButton(Context context) {
        this(context, null);
    }

    public PlayCirclesButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.PlayCirclesButton);
        this.mIsFlat = attributes.getBoolean(0, false);
        attributes.recycle();
    }

    public void bind(Document targetPerson, String currentAccountName, PlayStoreUiElementNode parentNode) {
        this.mParentNode = parentNode;
        if (this.mCircleModel == null) {
            this.mCircleModel = FinskyApp.get().getClientMutationCache(currentAccountName).getCachedCirclesModel(targetPerson, currentAccountName);
            this.mCircleModel.setCirclesModelListener(this);
            this.mCircleModel.loadCircles(FinskyApp.get().getApplicationContext(), ((PageFragmentHost) getContext()).getPeopleClient());
            setOnClickListener(this);
        }
        configure(this.mCircleModel.getCircles());
    }

    public void onCirclesUpdate(List<AudienceMember> circles) {
        configure(circles);
    }

    public void onClick(View view) {
        if (view == this && (view.getContext() instanceof FragmentActivity)) {
            FinskyApp.get().getEventLogger().logClickEvent(280, null, this.mParentNode);
            this.mCircleModel.launchCirclePicker((FragmentActivity) view.getContext());
        }
    }

    private void configure(List<AudienceMember> circles) {
        boolean showsCirclesIcon;
        int backgroundDrawableId;
        int iconResourceId;
        if (circles == null || circles.isEmpty()) {
            showsCirclesIcon = false;
        } else {
            showsCirclesIcon = true;
        }
        if (this.mIsFlat) {
            backgroundDrawableId = R.drawable.play_highlight_overlay_light;
            iconResourceId = showsCirclesIcon ? R.drawable.ic_circles__big_green : R.drawable.ic_add_person;
        } else {
            backgroundDrawableId = showsCirclesIcon ? R.drawable.btn_follow_green_with_highlight : R.drawable.btn_follow_blue_with_highlight;
            if (showsCirclesIcon) {
                iconResourceId = R.drawable.ic_circles_white;
            } else {
                iconResourceId = 0;
            }
        }
        Resources resources = getResources();
        String buttonText = GPlusUtils.getCirclesString(circles, resources);
        configure(buttonText, iconResourceId, backgroundDrawableId);
        setContentDescription(showsCirclesIcon ? buttonText : resources.getString(R.string.content_description_add_on_gplus, new Object[]{this.mCircleModel.getTargetPersonDoc().getTitle()}));
    }

    public void onDetachedFromWindow() {
        if (this.mCircleModel != null) {
            this.mCircleModel.setCirclesModelListener(null);
            this.mCircleModel = null;
        }
        super.onDetachedFromWindow();
    }
}
