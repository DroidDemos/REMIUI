package android.support.v7.widget;

import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v7.widget.RecyclerView.ViewHolder;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.List;

class AdapterHelper implements Callback {
    final Callback mCallback;
    final boolean mDisableRecycler;
    Runnable mOnItemProcessedCallback;
    final OpReorderer mOpReorderer;
    final ArrayList<UpdateOp> mPendingUpdates;
    final ArrayList<UpdateOp> mPostponedList;
    private Pool<UpdateOp> mUpdateOpPool;

    interface Callback {
        ViewHolder findViewHolder(int i);

        void markViewHoldersUpdated(int i, int i2);

        void offsetPositionsForAdd(int i, int i2);

        void offsetPositionsForMove(int i, int i2);

        void offsetPositionsForRemovingInvisible(int i, int i2);

        void offsetPositionsForRemovingLaidOutOrNewView(int i, int i2);

        void onDispatchFirstPass(UpdateOp updateOp);

        void onDispatchSecondPass(UpdateOp updateOp);
    }

    static class UpdateOp {
        int cmd;
        int itemCount;
        int positionStart;

        UpdateOp(int cmd, int positionStart, int itemCount) {
            this.cmd = cmd;
            this.positionStart = positionStart;
            this.itemCount = itemCount;
        }

        String cmdToString() {
            switch (this.cmd) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    return "add";
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    return "rm";
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    return "up";
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    return "mv";
                default:
                    return "??";
            }
        }

        public String toString() {
            return "[" + cmdToString() + ",s:" + this.positionStart + "c:" + this.itemCount + "]";
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            UpdateOp op = (UpdateOp) o;
            if (this.cmd != op.cmd) {
                return false;
            }
            if (this.cmd == 3 && Math.abs(this.itemCount - this.positionStart) == 1 && this.itemCount == op.positionStart && this.positionStart == op.itemCount) {
                return true;
            }
            if (this.itemCount != op.itemCount) {
                return false;
            }
            if (this.positionStart != op.positionStart) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.cmd * 31) + this.positionStart) * 31) + this.itemCount;
        }
    }

    AdapterHelper(Callback callback) {
        this(callback, false);
    }

    AdapterHelper(Callback callback, boolean disableRecycler) {
        this.mUpdateOpPool = new SimplePool(30);
        this.mPendingUpdates = new ArrayList();
        this.mPostponedList = new ArrayList();
        this.mCallback = callback;
        this.mDisableRecycler = disableRecycler;
        this.mOpReorderer = new OpReorderer(this);
    }

    void reset() {
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        recycleUpdateOpsAndClearList(this.mPostponedList);
    }

    void preProcess() {
        this.mOpReorderer.reorderOps(this.mPendingUpdates);
        int count = this.mPendingUpdates.size();
        for (int i = 0; i < count; i++) {
            UpdateOp op = (UpdateOp) this.mPendingUpdates.get(i);
            switch (op.cmd) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    applyAdd(op);
                    break;
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    applyRemove(op);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    applyUpdate(op);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    applyMove(op);
                    break;
            }
            if (this.mOnItemProcessedCallback != null) {
                this.mOnItemProcessedCallback.run();
            }
        }
        this.mPendingUpdates.clear();
    }

    void consumePostponedUpdates() {
        int count = this.mPostponedList.size();
        for (int i = 0; i < count; i++) {
            this.mCallback.onDispatchSecondPass((UpdateOp) this.mPostponedList.get(i));
        }
        recycleUpdateOpsAndClearList(this.mPostponedList);
    }

    private void applyMove(UpdateOp op) {
        postponeAndUpdateViewHolders(op);
    }

    private void applyRemove(UpdateOp op) {
        int tmpStart = op.positionStart;
        int tmpCount = 0;
        int tmpEnd = op.positionStart + op.itemCount;
        int type = -1;
        int position = op.positionStart;
        while (position < tmpEnd) {
            boolean typeChanged = false;
            if (this.mCallback.findViewHolder(position) != null || canFindInPreLayout(position)) {
                if (type == 0) {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(1, tmpStart, tmpCount));
                    typeChanged = true;
                }
                type = 1;
            } else {
                if (type == 1) {
                    postponeAndUpdateViewHolders(obtainUpdateOp(1, tmpStart, tmpCount));
                    typeChanged = true;
                }
                type = 0;
            }
            if (typeChanged) {
                position -= tmpCount;
                tmpEnd -= tmpCount;
                tmpCount = 1;
            } else {
                tmpCount++;
            }
            position++;
        }
        if (tmpCount != op.itemCount) {
            recycleUpdateOp(op);
            op = obtainUpdateOp(1, tmpStart, tmpCount);
        }
        if (type == 0) {
            dispatchAndUpdateViewHolders(op);
        } else {
            postponeAndUpdateViewHolders(op);
        }
    }

    private void applyUpdate(UpdateOp op) {
        int tmpStart = op.positionStart;
        int tmpCount = 0;
        int tmpEnd = op.positionStart + op.itemCount;
        int type = -1;
        int position = op.positionStart;
        while (position < tmpEnd) {
            if (this.mCallback.findViewHolder(position) != null || canFindInPreLayout(position)) {
                if (type == 0) {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(2, tmpStart, tmpCount));
                    tmpCount = 0;
                    tmpStart = position;
                }
                type = 1;
            } else {
                if (type == 1) {
                    postponeAndUpdateViewHolders(obtainUpdateOp(2, tmpStart, tmpCount));
                    tmpCount = 0;
                    tmpStart = position;
                }
                type = 0;
            }
            tmpCount++;
            position++;
        }
        if (tmpCount != op.itemCount) {
            recycleUpdateOp(op);
            op = obtainUpdateOp(2, tmpStart, tmpCount);
        }
        if (type == 0) {
            dispatchAndUpdateViewHolders(op);
        } else {
            postponeAndUpdateViewHolders(op);
        }
    }

    private void dispatchAndUpdateViewHolders(UpdateOp op) {
        if (op.cmd == 0 || op.cmd == 3) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int positionMultiplier;
        int tmpStart = updatePositionWithPostponed(op.positionStart, op.cmd);
        int tmpCnt = 1;
        int offsetPositionForPartial = op.positionStart;
        switch (op.cmd) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                positionMultiplier = 0;
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                positionMultiplier = 1;
                break;
            default:
                throw new IllegalArgumentException("op should be remove or update." + op);
        }
        for (int p = 1; p < op.itemCount; p++) {
            int updatedPos = updatePositionWithPostponed(op.positionStart + (positionMultiplier * p), op.cmd);
            boolean continuous = false;
            switch (op.cmd) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    continuous = updatedPos == tmpStart;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    if (updatedPos == tmpStart + 1) {
                        continuous = true;
                    } else {
                        continuous = false;
                    }
                    break;
            }
            if (continuous) {
                tmpCnt++;
            } else {
                UpdateOp tmp = obtainUpdateOp(op.cmd, tmpStart, tmpCnt);
                dispatchFirstPassAndUpdateViewHolders(tmp, offsetPositionForPartial);
                recycleUpdateOp(tmp);
                if (op.cmd == 2) {
                    offsetPositionForPartial += tmpCnt;
                }
                tmpStart = updatedPos;
                tmpCnt = 1;
            }
        }
        recycleUpdateOp(op);
        if (tmpCnt > 0) {
            tmp = obtainUpdateOp(op.cmd, tmpStart, tmpCnt);
            dispatchFirstPassAndUpdateViewHolders(tmp, offsetPositionForPartial);
            recycleUpdateOp(tmp);
        }
    }

    void dispatchFirstPassAndUpdateViewHolders(UpdateOp op, int offsetStart) {
        this.mCallback.onDispatchFirstPass(op);
        switch (op.cmd) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mCallback.offsetPositionsForRemovingInvisible(offsetStart, op.itemCount);
                return;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                this.mCallback.markViewHoldersUpdated(offsetStart, op.itemCount);
                return;
            default:
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    private int updatePositionWithPostponed(int pos, int cmd) {
        int i;
        for (i = this.mPostponedList.size() - 1; i >= 0; i--) {
            UpdateOp postponed = (UpdateOp) this.mPostponedList.get(i);
            if (postponed.cmd == 3) {
                int start;
                int end;
                if (postponed.positionStart < postponed.itemCount) {
                    start = postponed.positionStart;
                    end = postponed.itemCount;
                } else {
                    start = postponed.itemCount;
                    end = postponed.positionStart;
                }
                if (pos < start || pos > end) {
                    if (pos < postponed.positionStart) {
                        if (cmd == 0) {
                            postponed.positionStart++;
                            postponed.itemCount++;
                        } else if (cmd == 1) {
                            postponed.positionStart--;
                            postponed.itemCount--;
                        }
                    }
                } else if (start == postponed.positionStart) {
                    if (cmd == 0) {
                        postponed.itemCount++;
                    } else if (cmd == 1) {
                        postponed.itemCount--;
                    }
                    pos++;
                } else {
                    if (cmd == 0) {
                        postponed.positionStart++;
                    } else if (cmd == 1) {
                        postponed.positionStart--;
                    }
                    pos--;
                }
            } else if (postponed.positionStart <= pos) {
                if (postponed.cmd == 0) {
                    pos -= postponed.itemCount;
                } else if (postponed.cmd == 1) {
                    pos += postponed.itemCount;
                }
            } else if (cmd == 0) {
                postponed.positionStart++;
            } else if (cmd == 1) {
                postponed.positionStart--;
            }
        }
        for (i = this.mPostponedList.size() - 1; i >= 0; i--) {
            UpdateOp op = (UpdateOp) this.mPostponedList.get(i);
            if (op.cmd == 3) {
                if (op.itemCount == op.positionStart || op.itemCount < 0) {
                    this.mPostponedList.remove(i);
                    recycleUpdateOp(op);
                }
            } else if (op.itemCount <= 0) {
                this.mPostponedList.remove(i);
                recycleUpdateOp(op);
            }
        }
        return pos;
    }

    private boolean canFindInPreLayout(int position) {
        int count = this.mPostponedList.size();
        for (int i = 0; i < count; i++) {
            UpdateOp op = (UpdateOp) this.mPostponedList.get(i);
            if (op.cmd == 3) {
                if (findPositionOffset(op.itemCount, i + 1) == position) {
                    return true;
                }
            } else if (op.cmd == 0) {
                int end = op.positionStart + op.itemCount;
                for (int pos = op.positionStart; pos < end; pos++) {
                    if (findPositionOffset(pos, i + 1) == position) {
                        return true;
                    }
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    private void applyAdd(UpdateOp op) {
        postponeAndUpdateViewHolders(op);
    }

    private void postponeAndUpdateViewHolders(UpdateOp op) {
        this.mPostponedList.add(op);
        switch (op.cmd) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                this.mCallback.offsetPositionsForAdd(op.positionStart, op.itemCount);
                return;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mCallback.offsetPositionsForRemovingLaidOutOrNewView(op.positionStart, op.itemCount);
                return;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                this.mCallback.markViewHoldersUpdated(op.positionStart, op.itemCount);
                return;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                this.mCallback.offsetPositionsForMove(op.positionStart, op.itemCount);
                return;
            default:
                throw new IllegalArgumentException("Unknown update op type for " + op);
        }
    }

    boolean hasPendingUpdates() {
        return this.mPendingUpdates.size() > 0;
    }

    int findPositionOffset(int position) {
        return findPositionOffset(position, 0);
    }

    int findPositionOffset(int position, int firstPostponedItem) {
        int count = this.mPostponedList.size();
        for (int i = firstPostponedItem; i < count; i++) {
            UpdateOp op = (UpdateOp) this.mPostponedList.get(i);
            if (op.cmd == 3) {
                if (op.positionStart == position) {
                    position = op.itemCount;
                } else {
                    if (op.positionStart < position) {
                        position--;
                    }
                    if (op.itemCount <= position) {
                        position++;
                    }
                }
            } else if (op.positionStart > position) {
                continue;
            } else if (op.cmd == 1) {
                if (position < op.positionStart + op.itemCount) {
                    return -1;
                }
                position -= op.itemCount;
            } else if (op.cmd == 0) {
                position += op.itemCount;
            }
        }
        return position;
    }

    boolean onItemRangeChanged(int positionStart, int itemCount) {
        this.mPendingUpdates.add(obtainUpdateOp(2, positionStart, itemCount));
        if (this.mPendingUpdates.size() == 1) {
            return true;
        }
        return false;
    }

    boolean onItemRangeInserted(int positionStart, int itemCount) {
        this.mPendingUpdates.add(obtainUpdateOp(0, positionStart, itemCount));
        if (this.mPendingUpdates.size() == 1) {
            return true;
        }
        return false;
    }

    boolean onItemRangeRemoved(int positionStart, int itemCount) {
        this.mPendingUpdates.add(obtainUpdateOp(1, positionStart, itemCount));
        if (this.mPendingUpdates.size() == 1) {
            return true;
        }
        return false;
    }

    void consumeUpdatesInOnePass() {
        consumePostponedUpdates();
        int count = this.mPendingUpdates.size();
        for (int i = 0; i < count; i++) {
            UpdateOp op = (UpdateOp) this.mPendingUpdates.get(i);
            switch (op.cmd) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    this.mCallback.onDispatchSecondPass(op);
                    this.mCallback.offsetPositionsForAdd(op.positionStart, op.itemCount);
                    break;
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    this.mCallback.onDispatchSecondPass(op);
                    this.mCallback.offsetPositionsForRemovingInvisible(op.positionStart, op.itemCount);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    this.mCallback.onDispatchSecondPass(op);
                    this.mCallback.markViewHoldersUpdated(op.positionStart, op.itemCount);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    this.mCallback.onDispatchSecondPass(op);
                    this.mCallback.offsetPositionsForMove(op.positionStart, op.itemCount);
                    break;
            }
            if (this.mOnItemProcessedCallback != null) {
                this.mOnItemProcessedCallback.run();
            }
        }
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
    }

    public UpdateOp obtainUpdateOp(int cmd, int positionStart, int itemCount) {
        UpdateOp op = (UpdateOp) this.mUpdateOpPool.acquire();
        if (op == null) {
            return new UpdateOp(cmd, positionStart, itemCount);
        }
        op.cmd = cmd;
        op.positionStart = positionStart;
        op.itemCount = itemCount;
        return op;
    }

    public void recycleUpdateOp(UpdateOp op) {
        if (!this.mDisableRecycler) {
            this.mUpdateOpPool.release(op);
        }
    }

    void recycleUpdateOpsAndClearList(List<UpdateOp> ops) {
        int count = ops.size();
        for (int i = 0; i < count; i++) {
            recycleUpdateOp((UpdateOp) ops.get(i));
        }
        ops.clear();
    }
}
