package com.google.android.play.drawer;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.google.android.play.R;
import com.google.android.play.drawer.PlayDrawerLayout.PlayDrawerDownloadSwitchConfig;

class PlayDrawerDownloadSwitchRow extends RelativeLayout implements OnClickListener, Checkable, android.widget.CompoundButton.OnCheckedChangeListener {
    private static final boolean SUPPORTS_STYLED_SWITCH;
    private TextView mActionTextView;
    private boolean mBroadcasting;
    private boolean mChecked;
    private int mCheckedTextColor;
    private OnCheckedChangeListener mListener;
    private Switch mSwitch;
    private final OnTouchListener mSwitchTouchListener;
    private int mUncheckedTextColor;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(PlayDrawerDownloadSwitchRow playDrawerDownloadSwitchRow, boolean z);
    }

    static {
        SUPPORTS_STYLED_SWITCH = VERSION.SDK_INT >= 16;
    }

    public PlayDrawerDownloadSwitchRow(Context context) {
        this(context, null);
    }

    public PlayDrawerDownloadSwitchRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mSwitchTouchListener = new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == 0) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        };
        this.mUncheckedTextColor = getResources().getColor(R.color.play_fg_primary);
    }

    public void configure(PlayDrawerDownloadSwitchConfig config) {
        this.mCheckedTextColor = config.checkedTextColor;
        int switchThumbDrawableId = config.thumbDrawableId;
        int switchTrackDrawableId = config.trackDrawableId;
        if (SUPPORTS_STYLED_SWITCH) {
            if (switchTrackDrawableId != -1) {
                this.mSwitch.setTrackResource(switchTrackDrawableId);
            }
            if (switchThumbDrawableId != -1) {
                this.mSwitch.setThumbResource(switchThumbDrawableId);
            }
            this.mSwitch.setContentDescription(config.actionText);
        }
        this.mActionTextView.setText(config.actionText);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mActionTextView = (TextView) findViewById(R.id.action_text);
        setOnClickListener(this);
        View switchView = findViewById(R.id.switch_button);
        if (switchView != null) {
            this.mSwitch = (Switch) switchView;
            this.mSwitch.setOnCheckedChangeListener(this);
            this.mSwitch.setOnTouchListener(this.mSwitchTouchListener);
        }
    }

    public void setChecked(boolean checked) {
        if (this.mChecked != checked) {
            setCheckedNoCallbacks(checked);
            if (!this.mBroadcasting) {
                if (VERSION.SDK_INT >= 14) {
                    sendAccessibilityEvent(2048);
                }
                this.mBroadcasting = true;
                if (this.mListener != null) {
                    this.mListener.onCheckedChanged(this, checked);
                }
                this.mBroadcasting = false;
            }
        }
    }

    public void setCheckedNoCallbacks(boolean checked) {
        this.mChecked = checked;
        if (SUPPORTS_STYLED_SWITCH && this.mSwitch != null) {
            this.mSwitch.setChecked(checked);
            this.mSwitch.refreshDrawableState();
        }
        this.mActionTextView.setTextColor(checked ? this.mCheckedTextColor : this.mUncheckedTextColor);
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    public void toggle() {
        setChecked(!this.mChecked);
    }

    protected int[] onCreateDrawableState(int extraSpace) {
        int[] superstate = super.onCreateDrawableState(extraSpace + 1);
        if (this.mChecked) {
            mergeDrawableStates(superstate, new int[]{16843014});
        }
        return superstate;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.mListener = listener;
    }

    public void onClick(View v) {
        toggle();
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked != this.mChecked) {
            setChecked(isChecked);
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(CheckBox.class.getName());
        info.setCheckable(true);
        info.setChecked(this.mChecked);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(PlayDrawerDownloadSwitchRow.class.getName());
        event.setChecked(this.mChecked);
    }
}
