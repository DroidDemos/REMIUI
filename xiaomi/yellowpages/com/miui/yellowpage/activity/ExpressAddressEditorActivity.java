package com.miui.yellowpage.activity;

import android.location.Address;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.model.ExpressAddress;
import com.miui.yellowpage.model.ExpressAddress.Type;
import com.miui.yellowpage.ui.ExpressAddressEditorFragment;
import com.miui.yellowpage.ui.cs;
import java.io.Serializable;

public class ExpressAddressEditorActivity extends ExpressBaseActivity {
    private ExpressAddressEditorFragment Jf;
    private Type bH;
    private EditType hi;
    private ExpressAddress hj;
    private TextView mTitle;

    public enum EditType {
        NEW,
        EDIT
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bn();
        ii();
        if (this.bH == null || this.hi == null) {
            Log.e("ExpressAddressEditorActivity", "address type or edit type unknown");
            finish();
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("extra_address", this.hj);
        bundle2.putSerializable("extra_address_type", this.bH);
        bundle2.putSerializable("extra_edit_type", this.hi);
        showFragment("ExpressAddressEditorFragment", null, bundle2, false);
    }

    private void ii() {
        String action = getIntent().getAction();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Serializable serializable = extras.getSerializable("extra_address_type");
            if (serializable != null) {
                if ("com.miui.yellowpage.action.NEW_ADDRESS".equals(action)) {
                    this.hi = EditType.NEW;
                } else if ("com.miui.yellowpage.action.EDIT_ADDRESS".equals(action)) {
                    this.hi = EditType.EDIT;
                }
                this.bH = (Type) serializable;
                if (this.hi == EditType.EDIT) {
                    Parcelable parcelable = extras.getParcelable("extra_address");
                    if (parcelable == null) {
                        this.hi = EditType.NEW;
                        this.hj = new ExpressAddress();
                        return;
                    }
                    this.hj = ExpressAddress.a((Address) parcelable);
                    return;
                }
                this.hj = new ExpressAddress();
            }
        }
    }

    private void bn() {
        this.mActionBar.setCustomView(R.layout.edit_mode_title_bar_with_default);
        this.mActionBar.setDisplayOptions(16);
        View customView = this.mActionBar.getCustomView();
        Button button = (Button) customView.findViewById(16908314);
        Button button2 = (Button) customView.findViewById(16908313);
        this.mTitle = (TextView) customView.findViewById(16908310);
        button.setOnClickListener(new an());
        button2.setOnClickListener(new N());
    }

    protected cs newFragmentByTag(String str) {
        if (!"ExpressAddressEditorFragment".equals(str)) {
            return null;
        }
        this.Jf = new ExpressAddressEditorFragment();
        return this.Jf;
    }

    public void setTitle(int i) {
        this.mTitle.setText(i);
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle.setText(charSequence);
    }

    protected boolean requireLogin() {
        return true;
    }
}
