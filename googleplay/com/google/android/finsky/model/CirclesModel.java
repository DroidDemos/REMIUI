package com.google.android.finsky.model;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.GPlusUtils;
import com.google.android.finsky.utils.GPlusUtils.CirclePickerListener;
import com.google.android.finsky.utils.GPlusUtils.GetCirclesListener;
import com.google.android.finsky.utils.Lists;
import com.google.android.gms.common.people.data.AudienceMember;
import com.google.android.gms.people.PeopleClient;
import java.util.ArrayList;
import java.util.List;

public class CirclesModel implements CirclePickerListener {
    private ArrayList<AudienceMember> mCircles;
    private CirclesModelListener mCirclesModelListener;
    private boolean mHasRequestedCircles;
    private final String mOwnerAccountName;
    private final Document mTargetPersonDoc;

    public interface CirclesModelListener {
        void onCirclesUpdate(List<AudienceMember> list);
    }

    public CirclesModel(Document targetPerson, String ownerAccountName) {
        this.mTargetPersonDoc = targetPerson;
        this.mOwnerAccountName = ownerAccountName;
    }

    public Document getTargetPersonDoc() {
        return this.mTargetPersonDoc;
    }

    public String getOwnerAccountName() {
        return this.mOwnerAccountName;
    }

    public List<AudienceMember> getCircles() {
        return this.mCircles;
    }

    public void setCirclesModelListener(CirclesModelListener listener) {
        this.mCirclesModelListener = listener;
    }

    public void loadCircles(Context context, PeopleClient peopleClient) {
        if (!this.mHasRequestedCircles) {
            this.mHasRequestedCircles = true;
            GPlusUtils.getCircles(context, peopleClient, this.mTargetPersonDoc.getBackendDocId(), new GetCirclesListener() {
                public void onCirclesLoaded(ArrayList<AudienceMember> belongingCircles) {
                    CirclesModel.this.mCircles = belongingCircles;
                    CirclesModel.this.invokeListener();
                }

                public void onCirclesLoadedFailed() {
                    CirclesModel.this.mCircles = Lists.newArrayList();
                    CirclesModel.this.invokeListener();
                }
            });
            GPlusUtils.reattachToActiveCirclePickerIfMatches(this.mTargetPersonDoc.getBackendDocId(), this);
        }
    }

    public void launchCirclePicker(FragmentActivity activity) {
        if (this.mCircles != null) {
            GPlusUtils.checkGPlusAndLaunchCirclePicker(activity, this.mTargetPersonDoc.getBackendDocId(), this.mCircles, this);
        }
    }

    public void onCirclesSelected(ArrayList<AudienceMember> selectedCircles) {
        this.mCircles = selectedCircles;
        invokeListener();
    }

    private void invokeListener() {
        if (this.mCirclesModelListener != null) {
            this.mCirclesModelListener.onCirclesUpdate(this.mCircles);
        }
    }
}
