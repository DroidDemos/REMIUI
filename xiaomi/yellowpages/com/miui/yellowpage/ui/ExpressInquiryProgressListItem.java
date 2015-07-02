package com.miui.yellowpage.ui;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.a;
import com.miui.yellowpage.utils.d;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import miui.app.AlertDialog.Builder;
import miui.telephony.PhoneNumberUtils.PhoneNumber;

public class ExpressInquiryProgressListItem extends LinearLayout {
    private static final Pattern MG;
    private TextView MH;
    private int MI;
    private int MJ;
    private int MK;
    private TextView lA;
    private Context mContext;
    private ImageView mIcon;
    private int mPaddingLeft;

    static {
        MG = Pattern.compile("[\\+]?[0-9\\-\\s]{10,}");
    }

    public ExpressInquiryProgressListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mIcon = (ImageView) findViewById(R.id.progress_icon);
        this.MH = (TextView) findViewById(R.id.context);
        this.lA = (TextView) findViewById(R.id.time);
        this.MI = this.mContext.getResources().getColor(R.color.list_text_color_normal_light);
        this.MJ = this.mContext.getResources().getColor(R.color.list_secondary_text_color_normal_light);
        this.MK = (int) this.mContext.getResources().getDimension(R.dimen.express_progress_item_padding_vertical);
        this.mPaddingLeft = (int) this.mContext.getResources().getDimension(R.dimen.express_progress_item_padding_left);
    }

    public void a(a aVar) {
        cF(aVar.getContext());
        this.lA.setText(aVar.getTime());
        if (aVar.isFirst() && aVar.isLast()) {
            setPadding(this.mPaddingLeft, this.MK, 0, 0);
            this.mIcon.setBackgroundResource(R.drawable.express_icon_single);
            this.MH.setTextColor(this.MI);
            this.lA.setTextColor(this.MI);
        } else if (aVar.isFirst()) {
            setPadding(this.mPaddingLeft, this.MK, 0, 0);
            this.mIcon.setBackgroundResource(R.drawable.express_icon_first);
            this.MH.setTextColor(this.MI);
            this.lA.setTextColor(this.MI);
        } else if (aVar.isLast()) {
            setPadding(this.mPaddingLeft, 0, 0, this.MK);
            this.mIcon.setBackgroundResource(R.drawable.express_icon_last);
            this.MH.setTextColor(this.MJ);
            this.lA.setTextColor(this.MJ);
        } else {
            setPadding(this.mPaddingLeft, 0, 0, 0);
            this.mIcon.setBackgroundResource(R.drawable.express_icon_middle);
            this.MH.setTextColor(this.MJ);
            this.lA.setTextColor(this.MJ);
        }
    }

    private void cF(String str) {
        Matcher matcher = MG.matcher(str);
        CharSequence spannableStringBuilder = new SpannableStringBuilder(str);
        while (matcher.find()) {
            PhoneNumber parse = PhoneNumber.parse(matcher.group());
            String normalizedNumber = parse.getNormalizedNumber(true, true);
            if (parse.isNormalMobileNumber()) {
                spannableStringBuilder.setSpan(new d(new bm(this, normalizedNumber)), matcher.start(), matcher.end(), 33);
            }
            parse.recycle();
        }
        this.MH.setText(spannableStringBuilder);
        this.MH.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void cG(String str) {
        Builder builder = new Builder(this.mContext);
        builder.setTitle(str);
        builder.setPositiveButton(R.string.call, new bo(this, str));
        builder.setNegativeButton(17039360, null);
        builder.create().show();
    }
}
