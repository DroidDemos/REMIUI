package com.google.android.finsky.services;

import android.accounts.Account;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.protos.Toc.CorpusMetadata;
import com.google.android.finsky.protos.Toc.TocResponse;
import com.google.android.finsky.services.IMarketCatalogService.Stub;
import com.google.android.finsky.utils.GetTocHelper;
import com.google.android.finsky.utils.GetTocHelper.Listener;
import com.google.android.finsky.utils.Utils;
import java.util.concurrent.Semaphore;

public class MarketCatalogService extends Service {
    private final Stub mBinder;

    public MarketCatalogService() {
        this.mBinder = new Stub() {
            public boolean isBackendEnabled(String accountName, final int backendId) {
                Utils.ensureNotOnMainThread();
                final Semaphore sem = new Semaphore(0);
                final boolean[] enabled = new boolean[1];
                Account account = AccountHandler.findAccount(accountName, MarketCatalogService.this);
                if (account == null) {
                    return false;
                }
                GetTocHelper.getToc(FinskyApp.get().getDfeApi(account.name), false, new Listener() {
                    public void onResponse(TocResponse response) {
                        for (CorpusMetadata corpus : response.corpus) {
                            if (backendId == corpus.backend) {
                                enabled[0] = true;
                                break;
                            }
                        }
                        sem.release();
                    }

                    public void onErrorResponse(VolleyError error) {
                        sem.release();
                    }
                });
                sem.acquireUninterruptibly();
                MarketCatalogService.this.stopSelf();
                return enabled[0];
            }
        };
    }

    public IBinder onBind(Intent arg0) {
        return this.mBinder;
    }
}
