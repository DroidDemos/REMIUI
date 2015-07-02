package com.miui.yellowpage.ui;

import android.content.Intent;
import android.graphics.Rect;
import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.model.YellowPageDataDetailEntry;
import com.miui.yellowpage.model.YellowPageDataDetailEntry.Type;

/* compiled from: YellowPageListItem */
class cl implements OnClickListener {
    final /* synthetic */ YellowPageListItem Dt;
    final /* synthetic */ YellowPageDataDetailEntry oh;

    cl(YellowPageListItem yellowPageListItem, YellowPageDataDetailEntry yellowPageDataDetailEntry) {
        this.Dt = yellowPageListItem;
        this.oh = yellowPageDataDetailEntry;
    }

    public void onClick(View view) {
        Intent dt = this.oh.dt();
        if (this.oh.du() == Type.PHONE) {
            int[] iArr = new int[2];
            this.Dt.zL.getLocationInWindow(iArr);
            dt.setSourceBounds(new Rect(iArr[0], iArr[1], iArr[0] + this.Dt.zL.getWidth(), iArr[1] + this.Dt.zL.getHeight()));
            this.Dt.getContext().startActivity(dt, null);
            return;
        }
        this.Dt.getContext().startActivity(dt);
    }
}
