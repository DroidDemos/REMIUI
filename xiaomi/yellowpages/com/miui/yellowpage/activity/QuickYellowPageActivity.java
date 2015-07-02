package com.miui.yellowpage.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.LocalRequest;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.ui.aR;
import com.miui.yellowpage.ui.ap;
import com.miui.yellowpage.ui.cO;
import com.miui.yellowpage.utils.o;
import com.miui.yellowpage.widget.CheckableImageView;
import com.miui.yellowpage.widget.FloatingChildLayout;
import java.util.ArrayList;
import java.util.List;
import miui.app.Activity;
import miui.yellowpage.YellowPageContract;

public class QuickYellowPageActivity extends Activity implements aR {
    private FloatingChildLayout HA;
    private List HB;
    private ViewPager HC;
    private int HD;
    private int[] HE;
    private ViewGroup HF;
    private View HG;
    private View HH;
    private HorizontalScrollView HI;
    private View HJ;
    private boolean HK;
    private b HL;
    private String[] HM;
    private boolean HN;
    private TextView bQ;
    private long ld;
    private YellowPage lh;

    public QuickYellowPageActivity() {
        this.HB = new ArrayList();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.quick_yellowpage_activity);
        u();
        if (this.ld == 0) {
            Log.d("QuickYellowPageActivity", "yellow page id should not be empty");
            finish();
            return;
        }
        this.HC = (ViewPager) findViewById(R.id.view_pager);
        this.HC.a(new ab());
        this.HC.a(new m(this, getFragmentManager(), null));
        this.HA = (FloatingChildLayout) findViewById(R.id.floating_layout);
        Rect sourceBounds = getIntent().getSourceBounds();
        if (sourceBounds != null) {
            Log.d("QuickYellowPageActivity", String.format("source position [top:%d, right:%d, bottom:%d, left:%d", new Object[]{Integer.valueOf(sourceBounds.top), Integer.valueOf(sourceBounds.right), Integer.valueOf(sourceBounds.bottom), Integer.valueOf(sourceBounds.left)}));
            this.HA.a(sourceBounds);
        }
        this.HF = (ViewGroup) findViewById(R.id.track);
        this.HG = findViewById(R.id.tab_placeholder);
        this.HH = findViewById(R.id.track_background);
        this.HI = (HorizontalScrollView) findViewById(R.id.track_scroller);
        this.HJ = findViewById(R.id.selected_tab_indicator);
        this.bQ = (TextView) findViewById(R.id.name);
        this.HA.a(new M(this));
        this.HL = new b();
        getLoaderManager().initLoader(0, null, this.HL);
    }

    private void u() {
        Intent intent = getIntent();
        if ("android.intent.action.VIEW".equals(intent.getAction())) {
            Object queryParameter = intent.getData().getQueryParameter("id");
            if (!TextUtils.isEmpty(queryParameter)) {
                this.ld = Long.parseLong(queryParameter);
            }
        }
    }

    private void d(String[] strArr) {
        cO cOVar = new cO();
        Bundle bundle = new Bundle();
        bundle.putStringArray("phone_numbers", strArr);
        cOVar.setArguments(bundle);
        cOVar.a((aR) this);
        this.HB.add(cOVar);
        this.HC.aW().notifyDataSetChanged();
    }

    private void hM() {
        ap apVar = new ap();
        Bundle bundle = new Bundle();
        bundle.putLong("id", this.ld);
        apVar.setArguments(bundle);
        this.HB.add(apVar);
        this.HC.aW().notifyDataSetChanged();
    }

    private void hN() {
        if (!this.HN) {
            this.HN = true;
            this.bQ.setText(this.lh.getName());
            hM();
            C(false);
            this.HF.addView(a(this.HF));
            this.HM = o.l(this, String.valueOf(this.ld));
            ThreadPool.execute(new K(this));
        }
    }

    private void show() {
        this.HA.b(new J(this));
    }

    public void s(boolean z) {
        if (!z) {
            this.HF.removeViewAt(1);
            this.HB.remove(1);
            this.HC.aW().notifyDataSetChanged();
            C(false);
            hO();
        }
    }

    private CheckableImageView ad(int i) {
        return (CheckableImageView) this.HF.getChildAt(i);
    }

    private void ae(int i) {
        if (i >= 0 && i < this.HF.getChildCount()) {
            for (int i2 = 0; i2 < this.HF.getChildCount(); i2++) {
                boolean z;
                CheckableImageView checkableImageView = (CheckableImageView) this.HF.getChildAt(i2);
                if (i2 == i) {
                    z = true;
                } else {
                    z = false;
                }
                checkableImageView.setChecked(z);
            }
        }
    }

    private View a(ViewGroup viewGroup) {
        CheckableImageView checkableImageView = (CheckableImageView) getLayoutInflater().inflate(R.layout.quickcontact_track_button, viewGroup, false);
        CharSequence string = getResources().getString(R.string.quick_yellow_page_tab_phones);
        checkableImageView.setLabel(string);
        checkableImageView.setChecked(false);
        checkableImageView.setContentDescription(string);
        checkableImageView.setOnClickListener(new I(this));
        return checkableImageView;
    }

    private View b(ViewGroup viewGroup) {
        CheckableImageView checkableImageView = (CheckableImageView) getLayoutInflater().inflate(R.layout.quickcontact_track_button, viewGroup, false);
        CharSequence string = getResources().getString(R.string.quick_yellow_page_tab_call_log);
        checkableImageView.setLabel(string);
        checkableImageView.setChecked(false);
        checkableImageView.setContentDescription(string);
        checkableImageView.setOnClickListener(new H(this));
        return checkableImageView;
    }

    private void hO() {
        int i = 0;
        int size = this.HB.size();
        int size2 = this.HB.size();
        int width = this.HH.getWidth();
        this.HD = width / size2;
        if (this.HD == 0) {
            Log.v("QuickYellowPageActivity", "Unable to get the tab width, just wait for the next call when window focused!");
            this.HK = false;
            return;
        }
        this.HJ.getLayoutParams().width = this.HD;
        size2 *= this.HD;
        this.HE = new int[size];
        size2 = width - size2;
        width = 0;
        while (width < size) {
            int[] iArr = this.HE;
            int i2 = this.HD;
            int i3 = size2 - 1;
            if (size2 > 0) {
                size2 = 1;
            } else {
                size2 = 0;
            }
            iArr[width] = size2 + i2;
            width++;
            size2 = i3;
        }
        while (i < size) {
            View childAt = this.HF.getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            layoutParams.width = this.HE[i];
            layoutParams.gravity = 17;
            childAt.setLayoutParams(layoutParams);
            i++;
        }
        this.HI.requestLayout();
        this.HK = true;
    }

    private void C(boolean z) {
        int i;
        int i2 = 0;
        View view = this.HG;
        if (z) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        view = this.HH;
        if (z) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        HorizontalScrollView horizontalScrollView = this.HI;
        if (!z) {
            i2 = 8;
        }
        horizontalScrollView.setVisibility(i2);
    }

    private BaseRequest cy() {
        LocalRequest localRequest = new LocalRequest(this, 0);
        localRequest.setUri(YellowPageContract.YellowPage.CONTENT_URI);
        localRequest.setProjection(new String[]{MiniDefine.at});
        localRequest.setSelection("yid=?");
        localRequest.setArgs(new String[]{String.valueOf(this.ld)});
        return localRequest;
    }

    private void hide(boolean z) {
        if (z) {
            this.HA.c(new L(this));
            return;
        }
        this.HA.c(null);
        finish();
    }
}
