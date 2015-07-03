package com.xiaomi.passport.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import com.xiaomi.passport.widget.AlertDialog.Builder;
import com.xiaomi.passport.widget.ProgressDialog;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SimpleDialogFragment extends DialogFragment {
    public static final String ARG_CANCELABLE = "cancelable";
    public static final String ARG_MESSAGE = "msg_res_id";
    public static final String ARG_TITLE = "title";
    public static final String ARG_TYPE = "type";
    private static final String TAG = "SimpleDialogFragment";
    public static final int TYPE_ALERT = 1;
    public static final int TYPE_PROGRESS = 2;
    private boolean mCancelable;
    private String mMessage;
    private OnClickListener mNegativeButtonClickListener;
    private int mNegativeButtonTextRes;
    private OnDismissListener mOnDismissListener;
    private OnClickListener mPositiveButtonClickListener;
    private int mPositiveButtonTextRes;
    private String mTitle;
    private int mType;

    public static final class AlertDialogFragmentBuilder {
        private boolean mCancelable;
        private boolean mCreated;
        private String mMessage;
        private String mTitle;
        private int mType;

        public AlertDialogFragmentBuilder(int type) {
            this.mCancelable = true;
            this.mType = type;
        }

        public AlertDialogFragmentBuilder setMessage(String message) {
            this.mMessage = message;
            return this;
        }

        public AlertDialogFragmentBuilder setCancelable(boolean cancelable) {
            this.mCancelable = cancelable;
            return this;
        }

        public AlertDialogFragmentBuilder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public SimpleDialogFragment create() {
            if (this.mCreated) {
                throw new IllegalStateException("dialog has been created");
            }
            this.mCreated = true;
            SimpleDialogFragment f = new SimpleDialogFragment();
            Bundle args = new Bundle();
            args.putString(SimpleDialogFragment.ARG_TITLE, this.mTitle);
            args.putString(SimpleDialogFragment.ARG_MESSAGE, this.mMessage);
            args.putBoolean(SimpleDialogFragment.ARG_CANCELABLE, this.mCancelable);
            args.putInt(SimpleDialogFragment.ARG_TYPE, this.mType);
            f.setArguments(args);
            return f;
        }
    }

    public SimpleDialogFragment() {
        this.mCancelable = true;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args == null) {
            throw new IllegalStateException("no argument");
        }
        this.mType = args.getInt(ARG_TYPE);
        this.mMessage = args.getString(ARG_MESSAGE);
        this.mTitle = args.getString(ARG_TITLE);
        this.mCancelable = args.getBoolean(ARG_CANCELABLE, true);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Class[] clsArr;
        Constructor constructor;
        Object[] objArr;
        Class[] clsArr2;
        Method method;
        switch (this.mType) {
            case TYPE_ALERT /*1*/:
                try {
                    Class<?> builderClazz = Class.forName("miui.app.AlertDialog$Builder");
                    clsArr = new Class[TYPE_ALERT];
                    clsArr[0] = Context.class;
                    constructor = builderClazz.getConstructor(clsArr);
                    objArr = new Object[TYPE_ALERT];
                    objArr[0] = getActivity();
                    Object builder = constructor.newInstance(objArr);
                    clsArr2 = new Class[TYPE_ALERT];
                    clsArr2[0] = CharSequence.class;
                    method = builderClazz.getMethod("setMessage", clsArr2);
                    objArr = new Object[TYPE_ALERT];
                    objArr[0] = this.mMessage;
                    method.invoke(builder, objArr);
                    clsArr2 = new Class[TYPE_ALERT];
                    clsArr2[0] = CharSequence.class;
                    method = builderClazz.getMethod("setTitle", clsArr2);
                    objArr = new Object[TYPE_ALERT];
                    objArr[0] = this.mTitle;
                    method.invoke(builder, objArr);
                    clsArr2 = new Class[TYPE_ALERT];
                    clsArr2[0] = Boolean.TYPE;
                    method = builderClazz.getMethod("setCancelable", clsArr2);
                    objArr = new Object[TYPE_ALERT];
                    objArr[0] = Boolean.valueOf(this.mCancelable);
                    method.invoke(builder, objArr);
                    if (this.mPositiveButtonTextRes > 0) {
                        clsArr2 = new Class[TYPE_PROGRESS];
                        clsArr2[0] = Integer.TYPE;
                        clsArr2[TYPE_ALERT] = OnClickListener.class;
                        method = builderClazz.getMethod("setPositiveButton", clsArr2);
                        objArr = new Object[TYPE_PROGRESS];
                        objArr[0] = Integer.valueOf(this.mPositiveButtonTextRes);
                        objArr[TYPE_ALERT] = this.mPositiveButtonClickListener;
                        method.invoke(builder, objArr);
                    }
                    if (this.mNegativeButtonTextRes > 0) {
                        clsArr2 = new Class[TYPE_PROGRESS];
                        clsArr2[0] = Integer.TYPE;
                        clsArr2[TYPE_ALERT] = OnClickListener.class;
                        method = builderClazz.getMethod("setNegativeButton", clsArr2);
                        objArr = new Object[TYPE_PROGRESS];
                        objArr[0] = Integer.valueOf(this.mNegativeButtonTextRes);
                        objArr[TYPE_ALERT] = this.mNegativeButtonClickListener;
                        method.invoke(builder, objArr);
                    }
                    return (Dialog) builderClazz.getMethod("create", new Class[0]).invoke(builder, new Object[0]);
                } catch (Exception e) {
                    Log.e(TAG, "AlertDialog reflect exception: ", e);
                    Builder b = new Builder(getActivity()).setMessage(this.mMessage).setCancelable(this.mCancelable).setTitle(this.mTitle);
                    if (this.mPositiveButtonTextRes > 0) {
                        b.setPositiveButton(this.mPositiveButtonTextRes, this.mPositiveButtonClickListener);
                    }
                    if (this.mNegativeButtonTextRes > 0) {
                        b.setNegativeButton(this.mNegativeButtonTextRes, this.mNegativeButtonClickListener);
                    }
                    return b.create();
                }
            case TYPE_PROGRESS /*2*/:
                try {
                    Class<?> progressDialogClazz = Class.forName("miui.app.ProgressDialog");
                    clsArr = new Class[TYPE_ALERT];
                    clsArr[0] = Context.class;
                    constructor = progressDialogClazz.getConstructor(clsArr);
                    objArr = new Object[TYPE_ALERT];
                    objArr[0] = getActivity();
                    Object dialog = constructor.newInstance(objArr);
                    clsArr2 = new Class[TYPE_ALERT];
                    clsArr2[0] = CharSequence.class;
                    method = progressDialogClazz.getMethod("setMessage", clsArr2);
                    objArr = new Object[TYPE_ALERT];
                    objArr[0] = this.mMessage;
                    method.invoke(dialog, objArr);
                    clsArr2 = new Class[TYPE_ALERT];
                    clsArr2[0] = Boolean.TYPE;
                    method = progressDialogClazz.getMethod("setCancelable", clsArr2);
                    objArr = new Object[TYPE_ALERT];
                    objArr[0] = Boolean.valueOf(this.mCancelable);
                    method.invoke(dialog, objArr);
                    ((Window) progressDialogClazz.getMethod("getWindow", new Class[0]).invoke(dialog, new Object[0])).setGravity(80);
                    return (Dialog) dialog;
                } catch (Exception e2) {
                    Log.e(TAG, "ProgressDiaglog reflect exception: ", e2);
                    Dialog dialog2 = new ProgressDialog(getActivity());
                    dialog2.setMessage(this.mMessage);
                    dialog2.setCancelable(this.mCancelable);
                    dialog2.getWindow().setGravity(80);
                    return dialog2;
                }
            default:
                throw new IllegalStateException("unknown dialog type:" + this.mType);
        }
    }

    public void setOnDismissListener(OnDismissListener dialogDismissListener) {
        this.mOnDismissListener = dialogDismissListener;
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss(dialog);
        }
    }

    public void setNegativeButton(int text, OnClickListener negativeButtonClickListener) {
        this.mNegativeButtonTextRes = text;
        this.mNegativeButtonClickListener = negativeButtonClickListener;
    }

    public void setPositiveButton(int text, OnClickListener positiveButtonClickListener) {
        this.mPositiveButtonTextRes = text;
        this.mPositiveButtonClickListener = positiveButtonClickListener;
    }
}
