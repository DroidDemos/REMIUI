package com.google.android.finsky.billing;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.giftcard.RedeemCodeFragment;
import com.google.android.finsky.billing.lightpurchase.multistep.MultiStepFragment;
import com.google.android.finsky.billing.lightpurchase.multistep.StepFragment;
import com.google.android.finsky.protos.Acquisition.SuccessInfo;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.image.FifeImageView;

public abstract class SuccessStep<T extends MultiStepFragment> extends StepFragment<T> {
    private View mMainView;
    protected SuccessInfo mSuccessInfo;

    protected static Bundle createArgs(SuccessInfo successInfo) {
        Bundle args = new Bundle();
        args.putParcelable("SuccessStep.success_info", ParcelableProto.forProto(successInfo));
        return args;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId;
        super.onCreateView(inflater, container, savedInstanceState);
        Bundle args = getArguments();
        this.mSuccessInfo = (SuccessInfo) ParcelableProto.getProtoFromBundle(args, "SuccessStep.success_info");
        int billingUiMode = args.getInt("ui_mode");
        boolean hasTitle = !TextUtils.isEmpty(this.mSuccessInfo.title);
        if (billingUiMode == 1) {
            getActivity().setTitle(this.mSuccessInfo.title);
            ((RedeemCodeFragment) getMultiStepFragment()).hideCancelButton();
            layoutId = R.layout.setup_wizard_redeem_success_step;
        } else {
            layoutId = hasTitle ? R.layout.purchase_success_step_with_title : R.layout.purchase_success_step;
        }
        this.mMainView = inflater.inflate(layoutId, container, false);
        if (hasTitle) {
            ((TextView) this.mMainView.findViewById(R.id.title)).setText(this.mSuccessInfo.title);
            TextView titleBylineView = (TextView) this.mMainView.findViewById(R.id.title_byline);
            if (TextUtils.isEmpty(this.mSuccessInfo.titleBylineHtml)) {
                titleBylineView.setVisibility(8);
            } else {
                titleBylineView.setText(Html.fromHtml(this.mSuccessInfo.titleBylineHtml));
                titleBylineView.setMovementMethod(LinkMovementMethod.getInstance());
                titleBylineView.setLinkTextColor(titleBylineView.getTextColors());
            }
            FifeImageView imageView = (FifeImageView) this.mMainView.findViewById(R.id.image);
            Image image = this.mSuccessInfo.thumbnailImage;
            if (image != null) {
                imageView.setImage(image.imageUrl, image.supportsFifeUrlOptions, FinskyApp.get().getBitmapLoader());
            } else {
                imageView.setVisibility(8);
            }
        }
        TextView messageView = (TextView) this.mMainView.findViewById(R.id.message);
        if (TextUtils.isEmpty(this.mSuccessInfo.messageHtml)) {
            messageView.setVisibility(8);
        } else {
            messageView.setText(Html.fromHtml(this.mSuccessInfo.messageHtml));
            messageView.setMovementMethod(LinkMovementMethod.getInstance());
            messageView.setLinkTextColor(messageView.getTextColors());
        }
        return this.mMainView;
    }

    public void onResume() {
        super.onResume();
        String textToAnnounce = null;
        if (!TextUtils.isEmpty(this.mSuccessInfo.messageHtml)) {
            textToAnnounce = Html.fromHtml(this.mSuccessInfo.messageHtml).toString();
        } else if (!TextUtils.isEmpty(this.mSuccessInfo.title)) {
            textToAnnounce = this.mSuccessInfo.title;
        }
        if (textToAnnounce != null) {
            UiUtils.sendAccessibilityEventWithText(this.mMainView.getContext(), textToAnnounce, this.mMainView);
        }
    }

    public String getContinueButtonLabel(Resources resources) {
        return !TextUtils.isEmpty(this.mSuccessInfo.buttonLabel) ? this.mSuccessInfo.buttonLabel : null;
    }
}
