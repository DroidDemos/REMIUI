package com.google.android.libraries.bind.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.google.android.libraries.bind.card.GroupRowLayout;
import com.google.android.libraries.bind.data.BindingViewGroup;
import com.google.android.libraries.bind.logging.Logd;
import com.google.android.libraries.bind.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class ViewHeap {
    private static final Logd LOGD;
    private static final WeakHashMap<View, View> inUseDebugMap;
    private final Map<Integer, List<View>> heap;
    private int inflatedCount;
    private final LayoutInflater inflater;
    private boolean isDisabledUntilNextGet;
    private int returnedCount;
    private int reusedCount;

    static {
        LOGD = Logd.get(ViewHeap.class);
        inUseDebugMap = null;
    }

    public View get(int viewResId, View convertView, LayoutParams layoutParams) {
        boolean z;
        String str;
        Integer convertViewResId = null;
        boolean z2 = true;
        if (layoutParams != null) {
            z = true;
        } else {
            z = false;
        }
        Util.checkPrecondition(z);
        this.isDisabledUntilNextGet = false;
        if (convertView != null) {
            convertViewResId = (Integer) convertView.getTag();
        }
        Logd logd = LOGD;
        String str2 = "convertViewResId: %s";
        Object[] objArr = new Object[1];
        if (convertViewResId == null) {
            str = "null";
        } else {
            str = getResourceName(convertViewResId.intValue());
        }
        objArr[0] = str;
        logd.v(str2, objArr);
        if (convertViewResId != null) {
            if (convertView instanceof ViewGroup) {
                LOGD.v("recycling children", new Object[0]);
                tryRecycleChildren((ViewGroup) convertView);
            }
            if (viewResId == convertViewResId.intValue()) {
                logReuse(viewResId, false);
                convertView.setLayoutParams(layoutParams);
                if (inUseDebugMap == null) {
                    return convertView;
                }
                Util.checkPrecondition(inUseDebugMap.containsKey(convertView));
                return convertView;
            }
        }
        List<View> views = (List) this.heap.get(Integer.valueOf(viewResId));
        View view = null;
        if (views != null && views.size() > 0) {
            view = (View) views.remove(views.size() - 1);
        }
        if (view == null) {
            try {
                view = this.inflater.inflate(viewResId, null, false);
                view.setTag(Integer.valueOf(viewResId));
                logInflate(viewResId);
            } catch (RuntimeException e) {
                LOGD.e("Failed to inflate view resource: %s", Util.getResourceName(viewResId));
                throw e;
            }
        }
        logReuse(viewResId, true);
        view.setLayoutParams(layoutParams);
        if (inUseDebugMap != null) {
            if (inUseDebugMap.put(view, view) != null) {
                z2 = false;
            }
            Util.checkPrecondition(z2);
        }
        return view;
    }

    private void logReuse(int viewResId, boolean fromViewHeap) {
        this.reusedCount++;
        if (!fromViewHeap) {
            this.returnedCount++;
        }
        LOGD.d("Reusing view of type %s, efficiency: %d %%", getResourceName(viewResId), Integer.valueOf(efficiencyPercent()));
    }

    private void logInflate(int viewResId) {
        this.inflatedCount++;
        LOGD.i("Inflating view of type %s, efficiency: %d %%", getResourceName(viewResId), Integer.valueOf(efficiencyPercent()));
    }

    private int efficiencyPercent() {
        return (this.reusedCount * 100) / (this.reusedCount + this.inflatedCount);
    }

    public void recycle(View view) {
        if (((Integer) view.getTag()) != null) {
            if ((view instanceof BindingViewGroup) && !((BindingViewGroup) view).isOwnedByParent()) {
                ((BindingViewGroup) view).prepareForRecycling();
            }
            if (view instanceof GroupRowLayout) {
                ((GroupRowLayout) view).prepareGroupRowForRecycling();
            }
            if (view instanceof ViewGroup) {
                tryRecycleChildren((ViewGroup) view);
            }
            if (!(view instanceof BindingViewGroup) || ((BindingViewGroup) view).isOwnedByParent()) {
                view.measure(0, 0);
            }
            transferOwnershipToHeap(view);
        }
    }

    private void tryRecycleChildren(ViewGroup viewGroup) {
        for (int i = viewGroup.getChildCount() - 1; i >= 0; i--) {
            View childView = viewGroup.getChildAt(i);
            if ((childView instanceof BindingViewGroup) && !((BindingViewGroup) childView).isOwnedByParent()) {
                viewGroup.removeViewAt(i);
                recycle(childView);
            } else if (childView instanceof ViewGroup) {
                tryRecycleChildren((ViewGroup) childView);
            }
        }
    }

    private void transferOwnershipToHeap(View view) {
        boolean z;
        boolean z2 = true;
        if (view.getParent() == null) {
            z = true;
        } else {
            z = false;
        }
        Util.checkPrecondition(z);
        Integer viewResId = (Integer) view.getTag();
        if (this.isDisabledUntilNextGet) {
            LOGD.v("The heap is temporarily disabled after being cleared, not recycling view of type %s", getResourceName(viewResId.intValue()));
            return;
        }
        List<View> views = (List) this.heap.get(viewResId);
        if (views == null) {
            views = new ArrayList();
        }
        views.add(view);
        LOGD.v("Recycled view of type %s, got %d in heap", getResourceName(viewResId.intValue()), Integer.valueOf(views.size()));
        this.heap.put(viewResId, views);
        if (inUseDebugMap != null) {
            if (inUseDebugMap.remove(view) == null) {
                z2 = false;
            }
            Util.checkPrecondition(z2, "Recycling a view we didn't create: " + view);
        }
        this.returnedCount++;
    }

    private String getResourceName(int viewResId) {
        if (LOGD.isEnabled()) {
            return Util.getResourceName(viewResId);
        }
        return Integer.toString(viewResId);
    }
}
