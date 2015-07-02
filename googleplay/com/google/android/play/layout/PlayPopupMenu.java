package com.google.android.play.layout;

import android.content.Context;
import android.support.v7.widget.ListPopupWindow;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import com.google.android.play.R;
import java.util.ArrayList;
import java.util.List;

public class PlayPopupMenu implements OnDismissListener {
    protected final View mAnchor;
    protected final Context mContext;
    private OnDismissListener mOnPopupDismissListener;
    protected final List<PopupAction> mPopupActions;
    protected ListPopupWindow mPopupWindow;

    @Deprecated
    public interface OnActionSelectedListener {
        void onActionSelected();
    }

    public interface OnPopupActionSelectedListener {
        void onActionSelected(int i);
    }

    protected static class PopupAction {
        @Deprecated
        public final OnActionSelectedListener mActionListener;
        public final int mId;
        public final boolean mIsEnabled;
        public final OnPopupActionSelectedListener mPopupActionListener;
        public final CharSequence mTitle;

        @Deprecated
        public PopupAction(CharSequence title, boolean isEnabled, OnActionSelectedListener actionListener) {
            this.mId = 0;
            this.mTitle = title;
            this.mIsEnabled = isEnabled;
            this.mActionListener = actionListener;
            this.mPopupActionListener = null;
        }

        public void onSelect() {
            if (this.mPopupActionListener != null) {
                this.mPopupActionListener.onActionSelected(this.mId);
            } else if (this.mActionListener != null) {
                this.mActionListener.onActionSelected();
            }
        }
    }

    public static class PopupListAdapter extends BaseAdapter {
        protected final Context mContext;
        private List<PopupAction> mPopupActions;

        private static class ViewHolder {
            private TextView shortcut;
            private TextView title;

            private ViewHolder() {
            }
        }

        public PopupListAdapter(Context context, List<PopupAction> popupActions) {
            this.mContext = context;
            this.mPopupActions = popupActions;
        }

        public int getCount() {
            return this.mPopupActions.size();
        }

        public Object getItem(int position) {
            return this.mPopupActions.get(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(this.mContext).inflate(R.layout.abc_popup_menu_item_layout, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.title);
                viewHolder.shortcut = (TextView) convertView.findViewById(R.id.shortcut);
                convertView.setTag(viewHolder);
            }
            viewHolder = (ViewHolder) convertView.getTag();
            PopupAction popupAction = (PopupAction) this.mPopupActions.get(position);
            viewHolder.title.setText(popupAction.mTitle);
            viewHolder.title.setEnabled(popupAction.mIsEnabled);
            viewHolder.shortcut.setVisibility(8);
            return convertView;
        }

        public boolean isEnabled(int position) {
            return ((PopupAction) this.mPopupActions.get(position)).mIsEnabled;
        }

        public void onSelect(int position) {
            ((PopupAction) this.mPopupActions.get(position)).onSelect();
        }
    }

    public PlayPopupMenu(Context context, View anchor) {
        this.mContext = context;
        this.mAnchor = anchor;
        this.mPopupActions = new ArrayList();
    }

    @Deprecated
    public void addMenuItem(CharSequence title, boolean isEnabled, OnActionSelectedListener onActionSelectedListener) {
        this.mPopupActions.add(new PopupAction(title, isEnabled, onActionSelectedListener));
    }

    public void show() {
        this.mPopupWindow = new ListPopupWindow(this.mContext);
        final PopupListAdapter popupListAdapter = new PopupListAdapter(this.mContext, this.mPopupActions);
        this.mPopupWindow.setAdapter(popupListAdapter);
        this.mPopupWindow.setOnDismissListener(this);
        this.mPopupWindow.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position >= 0) {
                    PlayPopupMenu.this.dismiss();
                    popupListAdapter.onSelect(position);
                }
            }
        });
        this.mPopupWindow.setAnchorView(this.mAnchor);
        this.mPopupWindow.setContentWidth(Math.min(this.mContext.getResources().getDisplayMetrics().widthPixels, measureContentWidth(popupListAdapter, this.mContext)));
        this.mPopupWindow.setModal(true);
        this.mPopupWindow.show();
    }

    protected static int measureContentWidth(ListAdapter listAdapter, Context context) {
        int width = 0;
        View itemView = null;
        int itemType = 0;
        ViewGroup measureParent = new FrameLayout(context);
        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        for (int i = 0; i < count; i++) {
            int positionType = listAdapter.getItemViewType(i);
            if (positionType != itemType) {
                itemType = positionType;
                itemView = null;
            }
            itemView = listAdapter.getView(i, itemView, measureParent);
            itemView.measure(widthMeasureSpec, heightMeasureSpec);
            width = Math.max(width, itemView.getMeasuredWidth());
        }
        return width;
    }

    public void onDismiss() {
        this.mPopupWindow = null;
        if (this.mOnPopupDismissListener != null) {
            this.mOnPopupDismissListener.onDismiss();
        }
    }

    public void setOnPopupDismissListener(OnDismissListener popupDismissListener) {
        this.mOnPopupDismissListener = popupDismissListener;
    }

    public void dismiss() {
        if (this.mPopupWindow != null) {
            this.mPopupWindow.dismiss();
        }
    }
}
