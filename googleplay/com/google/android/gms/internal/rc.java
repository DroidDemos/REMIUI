package com.google.android.gms.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.c;
import com.google.android.gms.people.model.Circle;
import com.google.android.wallet.instrumentmanager.R;

public final class rc extends c implements Circle {
    private final Bundle Ue;

    public rc(DataHolder dataHolder, int i, Bundle bundle) {
        super(dataHolder, i);
        this.Ue = bundle;
    }

    public String getCircleId() {
        return getString("circle_id");
    }

    public String getCircleName() {
        int circleType = getCircleType();
        if (circleType != -1) {
            Bundle bundle = this.Ue.getBundle("localized_group_names");
            if (bundle != null) {
                Object string = bundle.getString(String.valueOf(circleType));
                if (!TextUtils.isEmpty(string)) {
                    return string;
                }
            }
        }
        return getString("name");
    }

    public int getCircleType() {
        int integer = getInteger("type");
        switch (integer) {
            case -1:
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case R.styleable.WalletImFormEditText_required /*4*/:
                return integer;
            default:
                return -2;
        }
    }
}
