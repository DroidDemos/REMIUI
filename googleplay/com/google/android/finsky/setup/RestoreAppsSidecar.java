package com.google.android.finsky.setup;

import android.os.Bundle;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.fragments.SidecarFragment;
import com.google.android.finsky.protos.Restore.BackupDocumentInfo;
import com.google.android.finsky.protos.Restore.GetBackupDocumentChoicesResponse;
import com.google.android.finsky.utils.ConsistencyTokenHelper;
import com.google.android.finsky.utils.ConsistencyTokenHelper.Listener;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.volley.DisplayMessageError;

public class RestoreAppsSidecar extends SidecarFragment {
    private BackupDocumentInfo[] mBackupDocumentInfos;
    private DfeApi mDfeApi;

    public static RestoreAppsSidecar newInstance(String accountName) {
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        RestoreAppsSidecar result = new RestoreAppsSidecar();
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mDfeApi = FinskyApp.get().getDfeApi(getArguments().getString("authAccount"));
    }

    public void fetchBackupDocs(final long androidId) {
        if (getState() == 4) {
            FinskyLog.wtf("Making another load request while in loading state: %d", Integer.valueOf(getState()));
            return;
        }
        setState(4, 0);
        ConsistencyTokenHelper.get(FinskyApp.get(), new Listener() {
            public void onTokenReceived(String checkinConsistencyToken) {
                RestoreAppsSidecar.this.mDfeApi.getBackupDocumentChoices(androidId, checkinConsistencyToken, new Response.Listener<GetBackupDocumentChoicesResponse>() {
                    public void onResponse(GetBackupDocumentChoicesResponse response) {
                        RestoreAppsSidecar.this.mBackupDocumentInfos = response.backupDocumentInfo;
                        RestoreAppsSidecar.this.setState(5, 0);
                    }
                }, new ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        String serverMessage = null;
                        if (volleyError instanceof DisplayMessageError) {
                            serverMessage = ((DisplayMessageError) volleyError).getDisplayErrorHtml();
                        }
                        FinskyLog.e("Unable to fetch backup apps: %s (%s)", volleyError, serverMessage);
                        RestoreAppsSidecar.this.mBackupDocumentInfos = null;
                        RestoreAppsSidecar.this.setState(6, 0);
                    }
                });
            }
        });
    }

    public BackupDocumentInfo[] getBackupDocumentInfos() {
        return this.mBackupDocumentInfos;
    }
}
