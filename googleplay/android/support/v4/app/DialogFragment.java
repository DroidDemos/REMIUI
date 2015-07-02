package android.support.v4.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.wallet.instrumentmanager.R;

public class DialogFragment extends Fragment implements OnCancelListener, OnDismissListener {
    int mBackStackId;
    boolean mCancelable;
    Dialog mDialog;
    boolean mDismissed;
    boolean mShownByMe;
    boolean mShowsDialog;
    int mStyle;
    int mTheme;
    boolean mViewDestroyed;

    public DialogFragment() {
        this.mStyle = 0;
        this.mTheme = 0;
        this.mCancelable = true;
        this.mShowsDialog = true;
        this.mBackStackId = -1;
    }

    public void setStyle(int style, int theme) {
        this.mStyle = style;
        if (this.mStyle == 2 || this.mStyle == 3) {
            this.mTheme = 16973913;
        }
        if (theme != 0) {
            this.mTheme = theme;
        }
    }

    public void show(FragmentManager manager, String tag) {
        this.mDismissed = false;
        this.mShownByMe = true;
        FragmentTransaction ft = manager.beginTransaction();
        ft.add((Fragment) this, tag);
        ft.commit();
    }

    public int show(FragmentTransaction transaction, String tag) {
        this.mDismissed = false;
        this.mShownByMe = true;
        transaction.add((Fragment) this, tag);
        this.mViewDestroyed = false;
        this.mBackStackId = transaction.commit();
        return this.mBackStackId;
    }

    public void dismiss() {
        dismissInternal(false);
    }

    void dismissInternal(boolean allowStateLoss) {
        if (!this.mDismissed) {
            this.mDismissed = true;
            this.mShownByMe = false;
            if (this.mDialog != null) {
                this.mDialog.dismiss();
                this.mDialog = null;
            }
            this.mViewDestroyed = true;
            if (this.mBackStackId >= 0) {
                getFragmentManager().popBackStack(this.mBackStackId, 1);
                this.mBackStackId = -1;
                return;
            }
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.remove(this);
            if (allowStateLoss) {
                ft.commitAllowingStateLoss();
            } else {
                ft.commit();
            }
        }
    }

    public Dialog getDialog() {
        return this.mDialog;
    }

    public int getTheme() {
        return this.mTheme;
    }

    public void setCancelable(boolean cancelable) {
        this.mCancelable = cancelable;
        if (this.mDialog != null) {
            this.mDialog.setCancelable(cancelable);
        }
    }

    public void setShowsDialog(boolean showsDialog) {
        this.mShowsDialog = showsDialog;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!this.mShownByMe) {
            this.mDismissed = false;
        }
    }

    public void onDetach() {
        super.onDetach();
        if (!this.mShownByMe && !this.mDismissed) {
            this.mDismissed = true;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mShowsDialog = this.mContainerId == 0;
        if (savedInstanceState != null) {
            this.mStyle = savedInstanceState.getInt("android:style", 0);
            this.mTheme = savedInstanceState.getInt("android:theme", 0);
            this.mCancelable = savedInstanceState.getBoolean("android:cancelable", true);
            this.mShowsDialog = savedInstanceState.getBoolean("android:showsDialog", this.mShowsDialog);
            this.mBackStackId = savedInstanceState.getInt("android:backStackId", -1);
        }
    }

    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        if (!this.mShowsDialog) {
            return super.getLayoutInflater(savedInstanceState);
        }
        this.mDialog = onCreateDialog(savedInstanceState);
        switch (this.mStyle) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                this.mDialog.getWindow().addFlags(24);
                break;
        }
        this.mDialog.requestWindowFeature(1);
        if (this.mDialog != null) {
            return (LayoutInflater) this.mDialog.getContext().getSystemService("layout_inflater");
        }
        return (LayoutInflater) this.mActivity.getSystemService("layout_inflater");
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme());
    }

    public void onCancel(DialogInterface dialog) {
    }

    public void onDismiss(DialogInterface dialog) {
        if (!this.mViewDestroyed) {
            dismissInternal(true);
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.mShowsDialog) {
            View view = getView();
            if (view != null) {
                if (view.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                this.mDialog.setContentView(view);
            }
            this.mDialog.setOwnerActivity(getActivity());
            this.mDialog.setCancelable(this.mCancelable);
            this.mDialog.setOnCancelListener(this);
            this.mDialog.setOnDismissListener(this);
            if (savedInstanceState != null) {
                Bundle dialogState = savedInstanceState.getBundle("android:savedDialogState");
                if (dialogState != null) {
                    this.mDialog.onRestoreInstanceState(dialogState);
                }
            }
        }
    }

    public void onStart() {
        super.onStart();
        if (this.mDialog != null) {
            this.mViewDestroyed = false;
            this.mDialog.show();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.mDialog != null) {
            Bundle dialogState = this.mDialog.onSaveInstanceState();
            if (dialogState != null) {
                outState.putBundle("android:savedDialogState", dialogState);
            }
        }
        if (this.mStyle != 0) {
            outState.putInt("android:style", this.mStyle);
        }
        if (this.mTheme != 0) {
            outState.putInt("android:theme", this.mTheme);
        }
        if (!this.mCancelable) {
            outState.putBoolean("android:cancelable", this.mCancelable);
        }
        if (!this.mShowsDialog) {
            outState.putBoolean("android:showsDialog", this.mShowsDialog);
        }
        if (this.mBackStackId != -1) {
            outState.putInt("android:backStackId", this.mBackStackId);
        }
    }

    public void onStop() {
        super.onStop();
        if (this.mDialog != null) {
            this.mDialog.hide();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.mDialog != null) {
            this.mViewDestroyed = true;
            this.mDialog.dismiss();
            this.mDialog = null;
        }
    }
}
