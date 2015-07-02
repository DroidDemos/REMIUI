package com.google.android.finsky.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.finsky.protos.Toc.CarrierBillingConfig;
import com.google.android.finsky.protos.Toc.CorpusMetadata;
import com.google.android.finsky.protos.Toc.TocResponse;
import com.google.android.finsky.utils.ParcelableProto;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DfeToc extends DfeModel implements Parcelable {
    public static Creator<DfeToc> CREATOR;
    private final Map<Integer, CorpusMetadata> mCorpusMap;
    private final TocResponse mToc;

    public DfeToc(TocResponse response) {
        this.mCorpusMap = new LinkedHashMap();
        this.mToc = response;
        for (CorpusMetadata metadata : this.mToc.corpus) {
            this.mCorpusMap.put(Integer.valueOf(metadata.backend), metadata);
        }
    }

    public CorpusMetadata getCorpus(int backendId) {
        return (CorpusMetadata) this.mCorpusMap.get(Integer.valueOf(backendId));
    }

    public CorpusMetadata getCorpus(String landingUrl) {
        for (CorpusMetadata metadata : this.mToc.corpus) {
            if (metadata.landingUrl.equals(landingUrl)) {
                return metadata;
            }
        }
        return null;
    }

    public String getWidgetUrl(int backendId) {
        if (backendId == 0) {
            return this.mToc.recsWidgetUrl;
        }
        CorpusMetadata metadata = (CorpusMetadata) this.mCorpusMap.get(Integer.valueOf(backendId));
        return metadata != null ? metadata.recsWidgetUrl : null;
    }

    public List<CorpusMetadata> getCorpusList() {
        List<CorpusMetadata> list = new ArrayList();
        list.addAll(this.mCorpusMap.values());
        return list;
    }

    public String getRecsWidgetUrl() {
        return this.mToc.recsWidgetUrl;
    }

    public String getTosContent() {
        return this.mToc.tosContent;
    }

    public String getTosToken() {
        return this.mToc.tosToken;
    }

    public String getTosCheckboxTextMarketingEmails() {
        return this.mToc.tosCheckboxTextMarketingEmails;
    }

    public boolean hasCurrentUserPreviouslyOptedIn() {
        return this.mToc.userSettings.tosCheckboxMarketingEmailsOptedIn;
    }

    public String getHomeUrl() {
        return this.mToc.homeUrl;
    }

    public String getSocialHomeUrl() {
        return this.mToc.socialHomeUrl;
    }

    public String getHelpUrl() {
        return this.mToc.helpUrl;
    }

    public boolean hasIconOverrideUrl() {
        return this.mToc.hasIconOverrideUrl;
    }

    public String getIconOverrideUrl() {
        return this.mToc.iconOverrideUrl;
    }

    public boolean isAgeVerificationRequired() {
        return this.mToc.ageVerificationRequired;
    }

    public boolean isGplusSignupEnabled() {
        return this.mToc.gplusSignupEnabled;
    }

    public CarrierBillingConfig getCarrierBillingConfig() {
        if (this.mToc.billingConfig != null) {
            return this.mToc.billingConfig.carrierBillingConfig;
        }
        return null;
    }

    public static boolean isAggregatedHome(DfeToc toc, String url) {
        String homeUrl = toc.getHomeUrl();
        return !TextUtils.isEmpty(homeUrl) && homeUrl.equals(url);
    }

    public boolean isReady() {
        return true;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(ParcelableProto.forProto(this.mToc), 0);
    }

    static {
        CREATOR = new Creator<DfeToc>() {
            public DfeToc createFromParcel(Parcel source) {
                return new DfeToc((TocResponse) ParcelableProto.getProtoFromParcel(source));
            }

            public DfeToc[] newArray(int size) {
                return new DfeToc[size];
            }
        };
    }
}
