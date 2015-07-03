package com.xiaomi.passport.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class AlertControllerWrapper extends AlertController {
    AlertControllerImpl mAlertControllerImpl;

    public static class AlertParams extends com.xiaomi.passport.widget.AlertController.AlertParams {
        public ArrayList<ActionItem> mActionItems;
        public boolean mEditMode;
        public OnClickListener mOnActionItemClickListener;
        public OnDismissListener mOnDismissListener;
        public OnShowListener mOnShowListener;

        public static class ActionItem {
            public int icon;
            public int id;
            public CharSequence label;

            public ActionItem(CharSequence label, int icon, int id) {
                this.label = label;
                this.icon = icon;
                this.id = id;
            }
        }

        public AlertParams(Context context) {
            super(context);
        }

        public void apply(AlertController dialog) {
            if (this.mCustomTitleView != null) {
                dialog.setCustomTitle(this.mCustomTitleView);
            } else {
                if (this.mTitle != null) {
                    dialog.setTitle(this.mTitle);
                }
                if (this.mIconId >= 0) {
                    dialog.setIcon(this.mIconId);
                }
            }
            if (this.mMessage != null) {
                dialog.setMessage(this.mMessage);
            }
            if (this.mPositiveButtonText != null) {
                dialog.setButton(-1, this.mPositiveButtonText, this.mPositiveButtonListener, null);
            }
            if (this.mNegativeButtonText != null) {
                dialog.setButton(-2, this.mNegativeButtonText, this.mNegativeButtonListener, null);
            }
            if (this.mNeutralButtonText != null) {
                dialog.setButton(-3, this.mNeutralButtonText, this.mNeutralButtonListener, null);
            }
            if (this.mItems == null && this.mCursor == null && this.mAdapter == null) {
            }
            if (this.mView != null) {
                dialog.setView(this.mView);
            }
            if (this.mActionItems != null) {
                ((AlertControllerWrapper) dialog).getImpl().setActionItems(this.mActionItems, this.mOnActionItemClickListener);
            }
        }
    }

    public AlertControllerWrapper(Context context, DialogInterface di, Window window) {
        super(context, di, window);
        this.mAlertControllerImpl = new AlertControllerImpl(context, di, window);
    }

    public AlertControllerImpl getImpl() {
        return this.mAlertControllerImpl;
    }

    public void installContent() {
        this.mAlertControllerImpl.installContent();
    }

    public void setTitle(CharSequence title) {
        this.mAlertControllerImpl.setTitle(title);
    }

    public void setCustomTitle(View customTitleView) {
        this.mAlertControllerImpl.setCustomTitle(customTitleView);
    }

    public void setMessage(CharSequence message) {
        this.mAlertControllerImpl.setMessage(message);
    }

    public void setView(View view) {
        this.mAlertControllerImpl.setView(view);
    }

    public void setButton(int whichButton, CharSequence text, OnClickListener listener, Message msg) {
        this.mAlertControllerImpl.setButton(whichButton, text, listener, msg);
    }

    public void setIcon(int resId) {
    }

    public void setInverseBackgroundForced(boolean forceInverseBackground) {
    }

    public ListView getListView() {
        return null;
    }

    public Button getButton(int which) {
        return this.mAlertControllerImpl.getButton(which);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return this.mAlertControllerImpl.onKeyDown(keyCode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return this.mAlertControllerImpl.onKeyUp(keyCode, event);
    }

    public boolean[] getCheckedItems() {
        return this.mAlertControllerImpl.getCheckedItems();
    }
}
