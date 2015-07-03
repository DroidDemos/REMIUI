package com.xiaomi.passport.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TextView;
import com.xiaomi.accounts.UserId;
import com.xiaomi.passport.R;
import com.xiaomi.passport.widget.AlertControllerWrapper.AlertParams.ActionItem;
import com.xiaomi.passport.widget.MenuBuilder.Callback;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class AlertControllerImpl implements Callback {
    private ArrayList<ActionItem> mActionItems;
    private ListAdapter mAdapter;
    private final int mAlertDialogLayout;
    private ViewGroup mAlertDialogView;
    private OnClickListener mButtonHandler;
    private Button mButtonNegative;
    private Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    private Button mButtonNeutral;
    private Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    private Button mButtonPositive;
    private Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    private Button mButtonSelect;
    private int mCheckedItem;
    private boolean[] mCheckedItems;
    private Context mContext;
    private View mCustomTitleView;
    private DialogInterface mDialogInterface;
    private Handler mHandler;
    private final Runnable mInvalidateMenuRunnable;
    private MenuBuilder mMenu;
    private MenuPresenter.Callback mMenuPresenterCallback;
    private CharSequence mMessage;
    private TextView mMessageView;
    private DialogInterface.OnClickListener mOnActionItemClickListener;
    private ScrollView mScrollView;
    private CharSequence mTitle;
    private TextView mTitleView;
    private View mView;
    private final Window mWindow;
    private Window.Callback mWindowCallback;

    private static final class ButtonHandler extends Handler {
        private static final int MSG_DISMISS_DIALOG = 1;
        private WeakReference<DialogInterface> mDialog;

        public ButtonHandler(DialogInterface dialog) {
            this.mDialog = new WeakReference(dialog);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case -3:
                case -2:
                case UserId.USER_ALL /*-1*/:
                    ((DialogInterface.OnClickListener) msg.obj).onClick((DialogInterface) this.mDialog.get(), msg.what);
                    return;
                case MSG_DISMISS_DIALOG /*1*/:
                    ((DialogInterface) msg.obj).dismiss();
                    return;
                default:
                    return;
            }
        }
    }

    public AlertControllerImpl(Context context, DialogInterface dialogInterface, Window window) {
        this.mButtonHandler = new OnClickListener() {
            public void onClick(View v) {
                Message m = null;
                if (v == AlertControllerImpl.this.mButtonPositive && AlertControllerImpl.this.mButtonPositiveMessage != null) {
                    m = Message.obtain(AlertControllerImpl.this.mButtonPositiveMessage);
                } else if (v == AlertControllerImpl.this.mButtonNegative && AlertControllerImpl.this.mButtonNegativeMessage != null) {
                    m = Message.obtain(AlertControllerImpl.this.mButtonNegativeMessage);
                } else if (v == AlertControllerImpl.this.mButtonNeutral && AlertControllerImpl.this.mButtonNeutralMessage != null) {
                    m = Message.obtain(AlertControllerImpl.this.mButtonNeutralMessage);
                }
                if (m != null) {
                    m.sendToTarget();
                }
                AlertControllerImpl.this.mHandler.obtainMessage(1, AlertControllerImpl.this.mDialogInterface).sendToTarget();
            }
        };
        this.mCheckedItem = -1;
        this.mInvalidateMenuRunnable = new Runnable() {
            public void run() {
                MenuBuilder menu = AlertControllerImpl.this.createMenu();
                if (AlertControllerImpl.this.onCreatePanelMenu(menu) && AlertControllerImpl.this.onPreparePanelMenu(menu)) {
                    AlertControllerImpl.this.setMenu(menu);
                } else {
                    AlertControllerImpl.this.setMenu(null);
                }
            }
        };
        this.mMenuPresenterCallback = new MenuPresenter.Callback() {
            public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
            }

            public boolean onOpenSubMenu(MenuBuilder subMenu) {
                return false;
            }
        };
        this.mWindowCallback = new Window.Callback() {
            public boolean dispatchKeyEvent(KeyEvent event) {
                return false;
            }

            public boolean dispatchKeyShortcutEvent(KeyEvent event) {
                return false;
            }

            public boolean dispatchTouchEvent(MotionEvent event) {
                return false;
            }

            public boolean dispatchTrackballEvent(MotionEvent event) {
                return false;
            }

            public boolean dispatchGenericMotionEvent(MotionEvent event) {
                return false;
            }

            public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
                return false;
            }

            public View onCreatePanelView(int featureId) {
                return null;
            }

            public boolean onCreatePanelMenu(int featureId, Menu menu) {
                return false;
            }

            public boolean onPreparePanel(int featureId, View view, Menu menu) {
                return false;
            }

            public boolean onMenuOpened(int featureId, Menu menu) {
                return false;
            }

            public boolean onMenuItemSelected(int featureId, MenuItem item) {
                return false;
            }

            public void onWindowAttributesChanged(LayoutParams attrs) {
            }

            public void onContentChanged() {
            }

            public void onWindowFocusChanged(boolean hasFocus) {
            }

            public void onAttachedToWindow() {
            }

            public void onDetachedFromWindow() {
            }

            public void onPanelClosed(int featureId, Menu menu) {
            }

            public boolean onSearchRequested() {
                return false;
            }

            public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
                return null;
            }

            public void onActionModeStarted(ActionMode mode) {
            }

            public void onActionModeFinished(ActionMode mode) {
            }
        };
        this.mContext = context;
        this.mDialogInterface = dialogInterface;
        this.mWindow = window;
        this.mHandler = new ButtonHandler(dialogInterface);
        TypedArray a = context.obtainStyledAttributes(null, R.styleable.Passport_AlertDialog, 16842845, 0);
        this.mAlertDialogLayout = a.getResourceId(0, com.xiaomi.account.R.layout.passport_alert_dialog);
        a.recycle();
    }

    static boolean canTextInput(View v) {
        if (v.onCheckIsTextEditor()) {
            return true;
        }
        if (!(v instanceof ViewGroup)) {
            return false;
        }
        ViewGroup vg = (ViewGroup) v;
        int i = vg.getChildCount();
        while (i > 0) {
            i--;
            if (canTextInput(vg.getChildAt(i))) {
                return true;
            }
        }
        return false;
    }

    public void installContent() {
        this.mWindow.requestFeature(1);
        if (this.mView == null || !canTextInput(this.mView)) {
            this.mWindow.setFlags(131072, 131072);
        }
        ensureSubDecor();
        this.mAlertDialogView = (ViewGroup) this.mWindow.findViewById(com.xiaomi.account.R.id.parentPanel);
        setupView();
    }

    private void ensureSubDecor() {
        this.mWindow.setContentView(this.mAlertDialogLayout);
        this.mWindow.setGravity(80);
        this.mWindow.setLayout(-1, -2);
    }

    private void setMenu(MenuBuilder menu) {
        if (menu != this.mMenu) {
            this.mMenu = menu;
        }
    }

    MenuBuilder createMenu() {
        MenuBuilder menu = new MenuBuilder(this.mContext);
        menu.setCallback(this);
        return menu;
    }

    private boolean onCreatePanelMenu(MenuBuilder menu) {
        for (int i = 0; i < this.mActionItems.size(); i++) {
            ActionItem item = (ActionItem) this.mActionItems.get(i);
            menu.add(0, item.id, 0, item.label).setIcon(item.icon).setShowAsAction(2);
        }
        return true;
    }

    private boolean onPreparePanelMenu(MenuBuilder menu) {
        return true;
    }

    public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
        if (this.mOnActionItemClickListener != null) {
            this.mOnActionItemClickListener.onClick(this.mDialogInterface, item.getItemId());
        }
        return true;
    }

    public void onMenuModeChange(MenuBuilder menu) {
    }

    public void setTitle(CharSequence title) {
        this.mTitle = title;
        if (this.mTitleView != null) {
            this.mTitleView.setText(title);
        }
    }

    public void setCustomTitle(View customTitleView) {
        this.mCustomTitleView = customTitleView;
    }

    public void setMessage(CharSequence message) {
        this.mMessage = message;
        if (this.mMessageView != null) {
            this.mMessageView.setText(message);
        }
    }

    public void setView(View view) {
        this.mView = view;
    }

    public void setButton(int whichButton, CharSequence text, DialogInterface.OnClickListener listener, Message msg) {
        if (msg == null && listener != null) {
            msg = this.mHandler.obtainMessage(whichButton, listener);
        }
        switch (whichButton) {
            case -3:
                this.mButtonNeutralText = text;
                this.mButtonNeutralMessage = msg;
                return;
            case -2:
                this.mButtonNegativeText = text;
                this.mButtonNegativeMessage = msg;
                return;
            case UserId.USER_ALL /*-1*/:
                this.mButtonPositiveText = text;
                this.mButtonPositiveMessage = msg;
                return;
            default:
                throw new IllegalStateException("Button does not exist");
        }
    }

    public void setActionItems(ArrayList<ActionItem> actionItems, DialogInterface.OnClickListener listener) {
        this.mActionItems = actionItems;
        this.mOnActionItemClickListener = listener;
    }

    public void setCheckedItems(boolean[] checkedItems) {
        this.mCheckedItems = checkedItems;
    }

    public boolean[] getCheckedItems() {
        return this.mCheckedItems;
    }

    public DialogInterface getDialogInterface() {
        return this.mDialogInterface;
    }

    public Button getButton(int whichButton) {
        switch (whichButton) {
            case -3:
                return this.mButtonNeutral;
            case -2:
                return this.mButtonNegative;
            case UserId.USER_ALL /*-1*/:
                return this.mButtonPositive;
            default:
                return null;
        }
    }

    public void setAdapter(ListAdapter adapter) {
        this.mAdapter = adapter;
    }

    public void setCheckedItem(int checkedItem) {
        this.mCheckedItem = checkedItem;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return this.mScrollView != null && this.mScrollView.executeKeyEvent(event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return this.mScrollView != null && this.mScrollView.executeKeyEvent(event);
    }

    private void setupView() {
        setupTitle((ViewGroup) this.mAlertDialogView.findViewById(com.xiaomi.account.R.id.topPanel));
        setupContent((ViewGroup) this.mAlertDialogView.findViewById(com.xiaomi.account.R.id.contentPanel));
        setupCustom((FrameLayout) this.mAlertDialogView.findViewById(com.xiaomi.account.R.id.customPanel));
        setupButtons((ViewGroup) this.mAlertDialogView.findViewById(com.xiaomi.account.R.id.buttonPanel));
    }

    private void setupTitle(ViewGroup topPanel) {
        boolean hasTextTitle = false;
        if (this.mCustomTitleView != null) {
            int paddingTop;
            int paddingLeft;
            int paddingRight;
            topPanel.addView(this.mCustomTitleView, 0, new LinearLayout.LayoutParams(-1, -2));
            int defaultPaddingTop = this.mContext.getResources().getDimensionPixelSize(com.xiaomi.account.R.dimen.passport_dialog_title_vertical_padding);
            if (this.mCustomTitleView.getPaddingTop() != 0) {
                paddingTop = this.mCustomTitleView.getPaddingTop();
            } else {
                paddingTop = defaultPaddingTop;
            }
            int defaultPaddingHorizontal = this.mContext.getResources().getDimensionPixelSize(com.xiaomi.account.R.dimen.passport_dialog_title_horizontal_padding);
            if (this.mCustomTitleView.getPaddingLeft() != 0) {
                paddingLeft = this.mCustomTitleView.getPaddingLeft();
            } else {
                paddingLeft = defaultPaddingHorizontal;
            }
            if (this.mCustomTitleView.getPaddingRight() != 0) {
                paddingRight = this.mCustomTitleView.getPaddingRight();
            } else {
                paddingRight = defaultPaddingHorizontal;
            }
            this.mCustomTitleView.setPadding(paddingLeft, paddingTop, paddingRight, 0);
            topPanel.removeView(this.mAlertDialogView.findViewById(com.xiaomi.account.R.id.alertTitle));
            return;
        }
        if (!TextUtils.isEmpty(this.mTitle)) {
            hasTextTitle = true;
        }
        if (hasTextTitle) {
            this.mTitleView = (TextView) topPanel.findViewById(com.xiaomi.account.R.id.alertTitle);
            this.mTitleView.setText(this.mTitle);
            return;
        }
        topPanel.setVisibility(8);
    }

    private void setupContent(ViewGroup contentPanel) {
        this.mScrollView = (ScrollView) this.mAlertDialogView.findViewById(com.xiaomi.account.R.id.scrollView);
        this.mScrollView.setFocusable(false);
        this.mMessageView = (TextView) this.mAlertDialogView.findViewById(com.xiaomi.account.R.id.message);
        if (this.mMessageView != null) {
            if (this.mMessage != null) {
                this.mMessageView.setText(this.mMessage);
                return;
            }
            this.mMessageView.setVisibility(8);
            this.mScrollView.removeView(this.mMessageView);
            contentPanel.setVisibility(8);
        }
    }

    private void setupCustom(FrameLayout customPanel) {
        if (this.mView != null) {
            ((FrameLayout) this.mAlertDialogView.findViewById(16908331)).addView(this.mView, new ViewGroup.LayoutParams(-1, -1));
            if (this.mView instanceof ViewGroup) {
                int paddingTop;
                int paddingLeft;
                int paddingRight;
                ViewGroup viewGroup = this.mView;
                int defaultPaddingTop = this.mContext.getResources().getDimensionPixelSize(com.xiaomi.account.R.dimen.passport_dialog_custom_vertical_padding);
                if (viewGroup.getPaddingTop() != 0) {
                    paddingTop = viewGroup.getPaddingTop();
                } else {
                    paddingTop = defaultPaddingTop;
                }
                int defaultPaddingHorizontal = this.mContext.getResources().getDimensionPixelSize(com.xiaomi.account.R.dimen.passport_dialog_custom_horizontal_padding);
                if (viewGroup.getPaddingLeft() != 0) {
                    paddingLeft = viewGroup.getPaddingLeft();
                } else {
                    paddingLeft = defaultPaddingHorizontal;
                }
                if (viewGroup.getPaddingRight() != 0) {
                    paddingRight = viewGroup.getPaddingRight();
                } else {
                    paddingRight = defaultPaddingHorizontal;
                }
                viewGroup.setPadding(paddingLeft, paddingTop, paddingRight, viewGroup.getPaddingBottom());
                customPanel.setPadding(0, 0, 0, 0);
                return;
            }
            return;
        }
        this.mAlertDialogView.findViewById(com.xiaomi.account.R.id.customPanel).setVisibility(8);
    }

    private void setupButtons(ViewGroup buttonPanel) {
        boolean hasButton = false;
        this.mButtonPositive = (Button) buttonPanel.findViewById(16908313);
        if (this.mButtonPositive != null) {
            this.mButtonPositive.setOnClickListener(this.mButtonHandler);
            if (TextUtils.isEmpty(this.mButtonPositiveText)) {
                this.mButtonPositive.setVisibility(8);
            } else {
                this.mButtonPositive.setText(this.mButtonPositiveText);
                this.mButtonPositive.setVisibility(0);
                hasButton = true;
            }
        }
        this.mButtonNegative = (Button) buttonPanel.findViewById(16908314);
        if (this.mButtonNegative != null) {
            this.mButtonNegative.setOnClickListener(this.mButtonHandler);
            if (TextUtils.isEmpty(this.mButtonNegativeText)) {
                this.mButtonNegative.setVisibility(8);
            } else {
                this.mButtonNegative.setText(this.mButtonNegativeText);
                this.mButtonNegative.setVisibility(0);
                hasButton = true;
            }
        }
        this.mButtonNeutral = (Button) buttonPanel.findViewById(16908315);
        if (this.mButtonNeutral != null) {
            this.mButtonNeutral.setOnClickListener(this.mButtonHandler);
            if (TextUtils.isEmpty(this.mButtonNeutralText)) {
                this.mButtonNeutral.setVisibility(8);
            } else {
                this.mButtonNeutral.setText(this.mButtonNeutralText);
                this.mButtonNeutral.setVisibility(0);
                hasButton = true;
            }
        }
        if (!hasButton) {
            buttonPanel.setVisibility(8);
        }
    }
}
