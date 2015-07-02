package com.google.android.wallet.instrumentmanager.config;

import com.google.android.gsf.GservicesValue;

public final class G {
    public static final GservicesValue<Boolean> allowGzippedResponses;
    public static final GservicesValue<Boolean> allowPiiLogging;
    public static final GservicesValue<Long> androidId;
    public static final GservicesValue<Integer> minApiLevelToShowAutocompleteForAccessibility;
    public static final GservicesValue<Integer> pageImpressionDelayBeforeTrackingMs;
    public static final GservicesValue<Integer> volleyApiRequestDefaultTimeoutMs;

    public static class dcb {
        public static GservicesValue<Integer> verifyAssociationRetries;
        public static GservicesValue<Integer> verifyAssociationRetryDelayMs;

        static {
            verifyAssociationRetries = GservicesValue.value("wallet.dcb.verify_association_retries", Integer.valueOf(0));
            verifyAssociationRetryDelayMs = GservicesValue.value("wallet.dcb.verify_association_retry_delay_ms", Integer.valueOf(3000));
        }
    }

    public static class googleplaces {
        public static final GservicesValue<String> supportedCountries;
        public static final GservicesValue<Integer> thresholdAddressLine1;
        public static final GservicesValue<Integer> thresholdDefault;

        static {
            supportedCountries = GservicesValue.value("wallet.google_places_autocomplete_supported_countries", "CA,FR,DE,US");
            thresholdAddressLine1 = GservicesValue.value("wallet.google_places_autocomplete_threshold_address_line_1", Integer.valueOf(4));
            thresholdDefault = GservicesValue.value("wallet.google_places_autocomplete_threshold_default", Integer.valueOf(2));
        }
    }

    public static class images {
        public static final GservicesValue<Integer> diskCacheSizeBytes;
        public static final GservicesValue<Integer> inMemoryCacheSizeDp;
        public static final GservicesValue<Boolean> useWebPForFife;

        static {
            useWebPForFife = GservicesValue.value("wallet.images.use_webp_for_fife", true);
            diskCacheSizeBytes = GservicesValue.value("wallet.images.disk_cache_size_bytes", Integer.valueOf(2097152));
            inMemoryCacheSizeDp = GservicesValue.value("wallet.images.in_memory_cache_size_dp", Integer.valueOf(9600));
        }
    }

    static {
        androidId = GservicesValue.value("android_id", Long.valueOf(0));
        minApiLevelToShowAutocompleteForAccessibility = GservicesValue.value("wallet.accessibility.min_api_level_to_show_autocomplete", Integer.valueOf(Integer.MAX_VALUE));
        allowPiiLogging = GservicesValue.value("wallet.allow_pii_logging", false);
        volleyApiRequestDefaultTimeoutMs = GservicesValue.value("wallet.volley_api_request_default_timeout", Integer.valueOf(10000));
        allowGzippedResponses = GservicesValue.value("wallet.allow_gzipped_responses", true);
        pageImpressionDelayBeforeTrackingMs = GservicesValue.value("wallet.page_impression_delay_before_tracking_ms", Integer.valueOf(100));
    }
}
