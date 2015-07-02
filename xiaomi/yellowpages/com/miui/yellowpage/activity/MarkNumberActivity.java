package com.miui.yellowpage.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import java.util.Collections;
import java.util.List;
import miui.yellowpage.AntispamCategory;
import miui.yellowpage.AntispamCustomCategory;
import miui.yellowpage.YellowPageUtils;

public class MarkNumberActivity extends Activity {
    private static final String[] Gu;
    private AntispamCustomCategory Gr;
    private MatrixCursor Gs;
    private int Gt;
    private AlertDialog mDialog;
    private String mNumber;

    static {
        Gu = new String[]{"_id", MiniDefine.au};
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        u();
        hA();
        initViews();
    }

    private void u() {
        this.mNumber = getIntent().getStringExtra("com.miui.yellowpage.extra.number");
        if (TextUtils.isEmpty(this.mNumber)) {
            finish();
        }
    }

    @SuppressLint({"InflateParams"})
    private void initViews() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.mark_number_custom_title, null);
        TextView textView = (TextView) inflate.findViewById(R.id.mark_number_dialog_maintitle);
        if (this.Gr == null || this.Gr.isNumberCategoryCustom()) {
            textView.setText(R.string.mark_number_dialog_maintitle);
        } else {
            textView.setText(R.string.mark_number_custom_dialog_maintitle);
        }
        Builder builder = new Builder(this);
        builder.setCustomTitle(inflate);
        builder.setSingleChoiceItems(this.Gs, this.Gt, MiniDefine.au, new B(this));
        builder.setNegativeButton(17039360, new C(this));
        if (this.Gt >= 0) {
            builder.setPositiveButton(R.string.delete, new D(this));
        }
        this.mDialog = builder.show();
        this.mDialog.setOnDismissListener(new E(this));
    }

    private void hA() {
        this.Gr = YellowPageUtils.getAntispamNumberCategory(this, this.mNumber);
        this.Gs = new MatrixCursor(Gu);
        this.Gt = -1;
        List categories = YellowPageUtils.getCategories(this);
        Collections.sort(categories, new F(this));
        if (categories != null && categories.size() > 0) {
            String string;
            int i = 0;
            int i2 = 0;
            while (i < categories.size()) {
                int i3;
                AntispamCategory antispamCategory = (AntispamCategory) categories.get(i);
                String categoryName = antispamCategory.getCategoryName();
                if (antispamCategory.isUserCustom() || (antispamCategory.getCategoryType() != 0 && (this.Gr == null || this.Gr.getCategoryId() != antispamCategory.getCategoryId()))) {
                    i3 = i2;
                } else {
                    if (this.Gr != null && antispamCategory.getCategoryId() == this.Gr.getCategoryId()) {
                        if (this.Gr.isNumberCategoryCustom()) {
                            this.Gt = i2;
                            categoryName = antispamCategory.getCategoryName();
                        } else {
                            categoryName = getString(R.string.selected_category_format, new Object[]{antispamCategory.getCategoryName(), Integer.valueOf(this.Gr.getMarkedCount())});
                        }
                    }
                    this.Gs.addRow(new Object[]{Integer.valueOf(antispamCategory.getCategoryId()), categoryName});
                    i3 = i2 + 1;
                }
                i++;
                i2 = i3;
            }
            if (this.Gr == null || !this.Gr.isUserCustom()) {
                string = getString(R.string.custom_mark_category);
            } else {
                string = this.Gr.getCategoryName() + "(" + getString(R.string.custom_mark_category) + ")";
                this.Gt = this.Gs.getCount();
            }
            this.Gs.addRow(new Object[]{Integer.valueOf(-1), string});
        }
    }
}
