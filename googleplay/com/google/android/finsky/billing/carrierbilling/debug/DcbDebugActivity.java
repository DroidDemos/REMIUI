package com.google.android.finsky.billing.carrierbilling.debug;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RatingBar;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.action.CarrierCredentialsAction;
import com.google.android.finsky.billing.carrierbilling.action.CarrierParamsAction;
import com.google.android.finsky.billing.carrierbilling.action.CarrierProvisioningAction;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.Toc.TocResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.GetTocHelper;
import com.google.android.finsky.utils.GetTocHelper.Listener;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Utils;
import java.util.Collection;
import java.util.Collections;

public class DcbDebugActivity extends FragmentActivity {
    private static final Collection<DcbDetail> GSERVICES_DETAILS;
    private RatingBar mCredStatus;
    private RatingBar mDcbParamStatus;
    private CarrierBillingStorage mDcbStorage;
    private RatingBar mProvStatus;
    private final Runnable updateStatusRunnable;

    private class ErrorRunnable implements Runnable {
        private final Runnable mChainedRunnable;
        private final String mErrorMessage;

        public ErrorRunnable(String errorMessage, Runnable chainedRunnable) {
            this.mErrorMessage = errorMessage;
            this.mChainedRunnable = chainedRunnable;
        }

        public void run() {
            Toast.makeText(DcbDebugActivity.this, this.mErrorMessage, 1).show();
            this.mChainedRunnable.run();
        }
    }

    public DcbDebugActivity() {
        this.updateStatusRunnable = new Runnable() {
            public void run() {
                Utils.ensureOnMainThread();
                DcbDebugActivity.this.updateStatus();
            }
        };
    }

    static {
        GSERVICES_DETAILS = Collections.unmodifiableCollection(Lists.newArrayList(new GServicesDetail(G.vendingDcbConnectionType), new GServicesDetail(G.vendingDcbProxyHost), new GServicesDetail(G.vendingDcbProxyPort), new GServicesDetail(G.vendingCarrierCredentialsBufferMs), new GServicesDetail(G.vendingCarrierProvisioningRefreshFrequencyMs), new GServicesDetail(G.vendingCarrierProvisioningRetryMs)));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dcb_debug, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                refreshAllInfo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refreshAllInfo() {
        final Runnable postRefreshProvRunnable = new Runnable() {
            public void run() {
                DcbDebugActivity.this.updateStatus();
                DcbDebugActivity.this.refreshCredentials(DcbDebugActivity.this.updateStatusRunnable, DcbDebugActivity.this.updateStatusRunnable);
            }
        };
        final Runnable postRefreshDcbParamsRunnable = new Runnable() {
            public void run() {
                DcbDebugActivity.this.updateStatus();
                DcbDebugActivity.this.refreshProvisioning(postRefreshProvRunnable, DcbDebugActivity.this.updateStatusRunnable);
            }
        };
        BillingLocator.initCarrierBillingStorage(new Runnable() {
            public void run() {
                DcbDebugActivity.this.updateStatus();
                DcbDebugActivity.this.refreshDcbParams(postRefreshDcbParamsRunnable, DcbDebugActivity.this.updateStatusRunnable);
            }
        });
    }

    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dcb_debug);
        super.onCreate(savedInstanceState);
        this.mProvStatus = (RatingBar) findViewById(R.id.get_prov_status);
        this.mProvStatus.setEnabled(false);
        this.mCredStatus = (RatingBar) findViewById(R.id.get_cred_status);
        this.mCredStatus.setEnabled(false);
        this.mDcbParamStatus = (RatingBar) findViewById(R.id.dcb_param_status);
        this.mDcbParamStatus.setEnabled(false);
        this.mDcbStorage = BillingLocator.getCarrierBillingStorage();
        registerForContextMenu(findViewById(R.id.get_prov_status));
        registerForContextMenu(findViewById(R.id.get_prov_status_text));
        registerForContextMenu(findViewById(R.id.get_cred_status));
        registerForContextMenu(findViewById(R.id.get_cred_status_text));
        registerForContextMenu(findViewById(R.id.dcb_param_status));
        registerForContextMenu(findViewById(R.id.dcb_param_status_text));
        findViewById(R.id.get_prov_status_text).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                DcbDebugActivity.this.displayProvisioning();
            }
        });
        findViewById(R.id.get_cred_status_text).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                DcbDebugActivity.this.displayCredentials();
            }
        });
        findViewById(R.id.dcb_param_status_text).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                DcbDebugActivity.this.displayDcbParams();
            }
        });
        findViewById(R.id.view_dcb_gservices).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                DcbDebugDetailsFragment.create(new DcbDetailExtractor() {
                    public Collection<DcbDetail> getDetails() {
                        return DcbDebugActivity.GSERVICES_DETAILS;
                    }
                }, "DCB GServices Values").show(DcbDebugActivity.this.getSupportFragmentManager().beginTransaction(), "dcbGservices");
            }
        });
    }

    private static int whichField(View v) {
        switch (v.getId()) {
            case R.id.dcb_param_status:
            case R.id.dcb_param_status_text:
                return 16;
            case R.id.get_prov_status:
            case R.id.get_prov_status_text:
                return 32;
            case R.id.get_cred_status:
            case R.id.get_cred_status_text:
                return 48;
            default:
                FinskyLog.wtf("Unknown view id %d", Integer.valueOf(v.getId()));
                return -1;
        }
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        int whichField = whichField(v);
        boolean refreshEnabled = false;
        boolean isProvisioned = CarrierBillingUtils.isProvisioned(this.mDcbStorage);
        boolean hasDcbParams;
        if (this.mDcbStorage.getParams() != null) {
            hasDcbParams = true;
        } else {
            hasDcbParams = false;
        }
        if (whichField == 32 && hasDcbParams) {
            refreshEnabled = true;
        }
        if (whichField == 48 && hasDcbParams && isProvisioned) {
            refreshEnabled = true;
        }
        if (whichField == 16) {
            refreshEnabled = true;
        }
        menu.add(0, whichField | 1, 0, R.string.view_details);
        menu.add(0, whichField | 2, 1, R.string.refresh_item).setEnabled(refreshEnabled);
        menu.add(0, whichField | 3, 2, R.string.clear_item);
    }

    public boolean onContextItemSelected(MenuItem item) {
        int whichField = item.getItemId() & 240;
        switch (item.getItemId() & 15) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                handleMenuViewDetails(whichField);
                return true;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                handleMenuRefreshItem(whichField);
                return true;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                handleMenuClearItem(whichField);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void handleMenuClearItem(int whichField) {
        switch (whichField) {
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                this.mDcbStorage.clearParams();
                break;
            case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                this.mDcbStorage.clearProvisioning();
                break;
            case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                this.mDcbStorage.clearCredentials();
                break;
            default:
                FinskyLog.wtf("Got unexpected whichField %s", Integer.valueOf(whichField));
                break;
        }
        updateStatus();
    }

    private void handleMenuRefreshItem(int whichField) {
        switch (whichField) {
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                refreshDcbParams(this.updateStatusRunnable, this.updateStatusRunnable);
                return;
            case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                refreshProvisioning(this.updateStatusRunnable, this.updateStatusRunnable);
                return;
            case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                refreshCredentials(this.updateStatusRunnable, this.updateStatusRunnable);
                return;
            default:
                FinskyLog.wtf("Got unexpected whichField %s", Integer.valueOf(whichField));
                return;
        }
    }

    private void handleMenuViewDetails(int whichField) {
        switch (whichField) {
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                displayDcbParams();
                return;
            case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                displayProvisioning();
                return;
            case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                displayCredentials();
                return;
            default:
                FinskyLog.wtf("Got unexpected whichField %s", Integer.valueOf(whichField));
                return;
        }
    }

    private void displayDcbParams() {
        DcbDebugDetailsFragment.create(new ReflectionDcbDetailExtractor(this.mDcbStorage.getParams(), "dcb"), "DCB Params").show(getSupportFragmentManager().beginTransaction(), "showDcbParams");
    }

    private void displayProvisioning() {
        DcbDebugDetailsFragment.create(new ReflectionDcbDetailExtractor(this.mDcbStorage.getProvisioning(), "prov"), "Provisioning").show(getSupportFragmentManager().beginTransaction(), "showProvisioning");
    }

    private void displayCredentials() {
        DcbDebugDetailsFragment.create(new ReflectionDcbDetailExtractor(this.mDcbStorage.getCredentials(), "cred"), "Credentials").show(getSupportFragmentManager().beginTransaction(), "showCredentials");
    }

    private void updateStatus() {
        float f;
        float f2 = 1.0f;
        boolean isProvisioned = CarrierBillingUtils.isProvisioned(this.mDcbStorage);
        boolean validCredentials = CarrierBillingUtils.areCredentialsValid(this.mDcbStorage);
        boolean hasDcbParams = this.mDcbStorage.getParams() != null;
        RatingBar ratingBar = this.mProvStatus;
        if (isProvisioned) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        ratingBar.setRating(f);
        ratingBar = this.mCredStatus;
        if (validCredentials) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        ratingBar.setRating(f);
        RatingBar ratingBar2 = this.mDcbParamStatus;
        if (!hasDcbParams) {
            f2 = 0.0f;
        }
        ratingBar2.setRating(f2);
    }

    private void refreshCredentials(Runnable successRunnable, Runnable errorRunnable) {
        new CarrierCredentialsAction().run(this.mDcbStorage.getProvisioning().getProvisioningId(), null, successRunnable, new ErrorRunnable("Error refreshing credentials", errorRunnable));
    }

    private void refreshProvisioning(Runnable successRunnable, Runnable errorRunnable) {
        new CarrierProvisioningAction().forceRun(successRunnable, new ErrorRunnable("Error refreshing provisioning", errorRunnable));
    }

    private void refreshDcbParams(final Runnable successRunnable, final Runnable errorRunnable) {
        GetTocHelper.getToc(FinskyApp.get().getDfeApi(), false, new Listener() {
            public void onResponse(TocResponse response) {
                if (response.billingConfig == null) {
                    Toast.makeText(DcbDebugActivity.this, String.format("Error - no billing config in toc", new Object[0]), 1).show();
                    errorRunnable.run();
                    return;
                }
                new CarrierParamsAction(response.billingConfig.carrierBillingConfig).run(successRunnable);
            }

            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DcbDebugActivity.this, String.format("Error getting toc: %s", new Object[]{error}), 1).show();
                errorRunnable.run();
            }
        });
    }
}
