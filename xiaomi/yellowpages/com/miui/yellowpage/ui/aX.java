package com.miui.yellowpage.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract.Data;
import android.text.TextUtils;
import android.widget.Toast;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.request.BaseResult;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

/* compiled from: ExpressAddressEditorFragment */
class aX implements c {
    final /* synthetic */ ExpressAddressEditorFragment tu;

    private aX(ExpressAddressEditorFragment expressAddressEditorFragment) {
        this.tu = expressAddressEditorFragment;
    }

    public void onPreRequest(int i) {
    }

    public void onRequestFinished(int i, BaseResult baseResult) {
        if (((EditResult) baseResult).Ei == State.SUCCEEDED) {
            if (this.tu.hj.getStatus() == 3) {
                Toast.makeText(this.tu.mActivity, R.string.express_address_deleted, 0).show();
            }
            this.tu.ax();
            Intent intent = new Intent();
            intent.putExtra("extra_address", this.tu.hj);
            this.tu.mActivity.setResult(-1, intent);
            this.tu.mActivity.finish();
            return;
        }
        Log.d("ExpressAddressEditorFragment", "failed");
    }

    public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
        int i2 = 0;
        int i3 = 1;
        EditResult editResult = (EditResult) baseResult;
        switch (i) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                if (!TextUtils.isEmpty((String) obj)) {
                    this.tu.hj.setId((String) obj);
                    this.tu.hj.setStatus(1);
                    editResult.Ei = State.SUCCEEDED;
                    ej();
                    break;
                }
                editResult.Ei = State.FAILED;
                break;
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                if (obj != null && Boolean.valueOf((String) obj).booleanValue()) {
                    i2 = 1;
                }
                if (i2 == 0) {
                    editResult.Ei = State.FAILED;
                    break;
                }
                editResult.Ei = State.SUCCEEDED;
                this.tu.hj.setStatus(2);
                ej();
                break;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                if (obj == null || !Boolean.valueOf((String) obj).booleanValue()) {
                    i3 = 0;
                }
                if (i3 == 0) {
                    editResult.Ei = State.FAILED;
                    break;
                }
                editResult.Ei = State.SUCCEEDED;
                this.tu.hj.setStatus(3);
                break;
        }
        return editResult;
    }

    private void ej() {
        if (!TextUtils.isEmpty(this.tu.hn) && this.tu.hg.isChecked()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("raw_contact_id", this.tu.hn);
            contentValues.put("data1", this.tu.hj.eQ());
            contentValues.put("mimetype", "vnd.android.cursor.item/postal-address_v2");
            this.tu.mActivity.getContentResolver().insert(Data.CONTENT_URI, contentValues);
        }
    }
}
