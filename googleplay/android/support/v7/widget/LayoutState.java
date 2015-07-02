package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

class LayoutState {
    int mAvailable;
    int mCurrentPosition;
    int mExtra;
    int mItemDirection;
    int mLayoutDirection;

    LayoutState() {
        this.mExtra = 0;
    }

    boolean hasMore(State state) {
        return this.mCurrentPosition >= 0 && this.mCurrentPosition < state.getItemCount();
    }

    View next(Recycler recycler) {
        View view = recycler.getViewForPosition(this.mCurrentPosition);
        this.mCurrentPosition += this.mItemDirection;
        return view;
    }
}
