package com.google.android.finsky.experiments;

import android.text.TextUtils;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.utils.ArrayUtils;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Sets;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.analytics.ClientAnalytics.ActiveExperiments;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FinskyExperiments implements Experiments {
    private static final Set<String> sRecognizedExperiments;
    private String mAccountName;
    private ActiveExperiments mActiveExperiments;
    private final Set<String> mEnabledExperiments;
    private String mEnabledHeaderValue;
    private String mUnsupportedHeaderValue;

    static {
        sRecognizedExperiments = Sets.newHashSet();
        sRecognizedExperiments.add("cl:billing.show_buy_verb_in_button");
        sRecognizedExperiments.add("cl:billing.purchase_button_show_wallet_3d_icon");
        sRecognizedExperiments.add("cl:billing.hide_sale_prices");
        sRecognizedExperiments.add("cl:billing.cleanup_auth_settings");
        sRecognizedExperiments.add("cl:billing.prompt_for_fop");
        sRecognizedExperiments.add("cl:billing.prompt_for_fop_ui_mode_radio_button");
        sRecognizedExperiments.add("cl:billing.prompt_for_fop_ui_mode_billing_profile");
        sRecognizedExperiments.add("cl:billing.prompt_for_fop_ui_mode_billing_profile_nested");
        sRecognizedExperiments.add("cl:billing.prompt_for_fop_ui_mode_billing_profile_more_details");
        sRecognizedExperiments.add("cl:billing.prompt_for_fop_ui_mode_billing_profile_not_now");
        sRecognizedExperiments.add("cl:billing.setupwizard_prompt_for_fop_ui_mode_radio_button");
        sRecognizedExperiments.add("cl:billing.setupwizard_prompt_for_fop_ui_mode_billing_profile");
        sRecognizedExperiments.add("cl:billing.setupwizard_prompt_for_fop_ui_mode_billing_profile_more_details");
        sRecognizedExperiments.add("cl:billing.cart_details_old_expander");
        sRecognizedExperiments.add("cl:billing.hide_edit_payment_my_account");
        sRecognizedExperiments.add("cl:ui.enable_post_purchase_xsell_for_all_corpora");
        sRecognizedExperiments.add("cl:details.album_start_cover_expanded");
        sRecognizedExperiments.add("cl:installer.force_through_sdcard");
        sRecognizedExperiments.add("cl:details.album_all_access_enabled");
        sRecognizedExperiments.add("cl:details.hide_download_count_in_title");
        sRecognizedExperiments.add("cl:auth.use_gms_core_based_auth");
        sRecognizedExperiments.add("cl:search.cap_local_suggestions_2");
        sRecognizedExperiments.add("cl:search.cap_local_suggestions_3");
        sRecognizedExperiments.add("cl:animations.transitions_enabled");
        sRecognizedExperiments.add("cl:search.use_dfe_for_music_search_suggestions");
        sRecognizedExperiments.add("cl:ENABLE_DFE_REQUEST_LOGGING");
        sRecognizedExperiments.add("cl:search.dora_searchbox_enabled");
        sRecognizedExperiments.add("cl:search.dora_searchbox_zero_query_suggest_enabled");
        sRecognizedExperiments.add("cl:ui.clear_backstack_after_timeout");
        sRecognizedExperiments.add("cl:search.hide_ordinals_from_search_results");
        sRecognizedExperiments.add("cl:installer.preserve_old_obb_files");
        sRecognizedExperiments.add("cl:details.details_page_v2_enabled");
        sRecognizedExperiments.add("cl:ui.happiness_survey_in_search_with_snows");
        sRecognizedExperiments.add("cl:ui.happiness_survey_in_search_no_snows");
        sRecognizedExperiments.add("cl:ui.happiness_survey_in_home");
    }

    public FinskyExperiments(String accountName) {
        this.mActiveExperiments = new ActiveExperiments();
        this.mEnabledExperiments = Sets.newHashSet();
        this.mAccountName = accountName;
        loadExperimentsFromDisk();
    }

    public synchronized ActiveExperiments getActiveExperiments() {
        return this.mActiveExperiments;
    }

    public synchronized boolean isEnabled(String experimentId) {
        return this.mEnabledExperiments.contains(experimentId);
    }

    public synchronized boolean hasEnabledExperiments() {
        return this.mEnabledExperiments.size() > 0;
    }

    public synchronized boolean hasUnsupportedExperiments() {
        return !TextUtils.isEmpty(this.mUnsupportedHeaderValue);
    }

    public synchronized String getEnabledExperimentsHeaderValue() {
        return this.mEnabledHeaderValue;
    }

    public synchronized String getUnsupportedExperimentsHeaderValue() {
        return this.mUnsupportedHeaderValue;
    }

    public synchronized void setExperiments(String[] experiments) {
        SharedPreference<String> preference = FinskyPreferences.experimentList.get(this.mAccountName);
        String joinedExperiments = Utils.commaPackStrings(experiments);
        if (!TextUtils.equals((CharSequence) preference.get(), joinedExperiments)) {
            preference.put(joinedExperiments);
            setExperimentsInternal(experiments);
        }
    }

    private void setExperimentsInternal(String[] experiments) {
        this.mEnabledExperiments.clear();
        this.mEnabledHeaderValue = null;
        this.mUnsupportedHeaderValue = null;
        this.mActiveExperiments = new ActiveExperiments();
        ArrayList<String> clientAlteringExperiments = Lists.newArrayList();
        ArrayList<String> otherExperiments = Lists.newArrayList();
        experiments = (String[]) ArrayUtils.concatenate(experiments, Utils.commaUnpackStrings((String) G.additionalExperiments.get()));
        if (((Boolean) DfeApiConfig.showStagingData.get()).booleanValue()) {
            this.mEnabledExperiments.add("android_group:eng.finsky.merchandising.staging");
            clientAlteringExperiments.add("android_group:eng.finsky.merchandising.staging");
        }
        List<String> unsupportedExperiments = Lists.newArrayList();
        for (String experiment : experiments) {
            if (sRecognizedExperiments.contains(experiment)) {
                this.mEnabledExperiments.add(experiment);
                clientAlteringExperiments.add(experiment);
            } else {
                unsupportedExperiments.add(experiment);
                otherExperiments.add(experiment);
            }
        }
        if (clientAlteringExperiments.size() > 0) {
            this.mActiveExperiments.clientAlteringExperiment = (String[]) clientAlteringExperiments.toArray(new String[clientAlteringExperiments.size()]);
        }
        if (otherExperiments.size() > 0) {
            this.mActiveExperiments.otherExperiment = (String[]) otherExperiments.toArray(new String[otherExperiments.size()]);
        }
        this.mEnabledHeaderValue = TextUtils.join(",", this.mEnabledExperiments);
        this.mUnsupportedHeaderValue = TextUtils.join(",", unsupportedExperiments);
    }

    private void loadExperimentsFromDisk() {
        setExperimentsInternal(Utils.commaUnpackStrings((String) FinskyPreferences.experimentList.get(this.mAccountName).get()));
    }
}
