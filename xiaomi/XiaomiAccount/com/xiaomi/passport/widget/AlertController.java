package com.xiaomi.passport.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.database.Cursor;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public abstract class AlertController {

    public static abstract class AlertParams {
        public ListAdapter mAdapter;
        public boolean mCancelable;
        public int mCheckedItem;
        public boolean[] mCheckedItems;
        public Context mContext;
        public Cursor mCursor;
        public View mCustomTitleView;
        public int mIconId;
        public LayoutInflater mInflater;
        public String mIsCheckedColumn;
        public boolean mIsMultiChoice;
        public boolean mIsSingleChoice;
        public CharSequence[] mItems;
        public String mLabelColumn;
        public CharSequence mMessage;
        public OnClickListener mNegativeButtonListener;
        public CharSequence mNegativeButtonText;
        public OnClickListener mNeutralButtonListener;
        public CharSequence mNeutralButtonText;
        public OnCancelListener mOnCancelListener;
        public OnMultiChoiceClickListener mOnCheckboxClickListener;
        public OnClickListener mOnClickListener;
        public OnItemSelectedListener mOnItemSelectedListener;
        public OnKeyListener mOnKeyListener;
        public OnClickListener mPositiveButtonListener;
        public CharSequence mPositiveButtonText;
        public CharSequence mTitle;
        public View mView;

        public abstract void apply(AlertController alertController);

        public AlertParams(Context context) {
            this.mIconId = 0;
            this.mCheckedItem = -1;
            this.mContext = context;
        }
    }

    public abstract Button getButton(int i);

    public abstract ListView getListView();

    public abstract void installContent();

    public abstract boolean onKeyDown(int i, KeyEvent keyEvent);

    public abstract boolean onKeyUp(int i, KeyEvent keyEvent);

    public abstract void setButton(int i, CharSequence charSequence, OnClickListener onClickListener, Message message);

    public abstract void setCustomTitle(View view);

    public abstract void setIcon(int i);

    public abstract void setInverseBackgroundForced(boolean z);

    public abstract void setMessage(CharSequence charSequence);

    public abstract void setTitle(CharSequence charSequence);

    public abstract void setView(View view);

    public AlertController(Context context, DialogInterface di, Window window) {
    }
}
