package com.google.android.finsky.experiments;

public interface Experiments {
    String getEnabledExperimentsHeaderValue();

    String getUnsupportedExperimentsHeaderValue();

    boolean hasEnabledExperiments();

    boolean hasUnsupportedExperiments();

    boolean isEnabled(String str);

    void setExperiments(String[] strArr);
}
