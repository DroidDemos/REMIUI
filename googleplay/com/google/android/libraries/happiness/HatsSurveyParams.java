package com.google.android.libraries.happiness;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HatsSurveyParams {
    private final Map<String, String> mLoggingParams;
    private final Map<String, String> mParams;

    public HatsSurveyParams(String zwiebackCookie, String siteId) {
        this.mParams = new HashMap();
        this.mLoggingParams = new HashMap();
        setParam("site_id", siteId);
        if (zwiebackCookie != null) {
            setParam("zwieback_cookie", zwiebackCookie);
        }
        setParam("survey_url", "http://www.google.com/insights/consumersurveys/async_survey");
        setParam("locale", "en-US");
    }

    public HatsSurveyParams setParam(String key, String value) {
        this.mParams.put(key, value);
        return this;
    }

    public String getParam(String key) {
        return (String) this.mParams.get(key);
    }

    public String toJS(String onObject) {
        StringBuilder paramsScript = new StringBuilder();
        paramsScript.append(onObject + "['params'] = {};\n");
        paramsScript.append(onObject + "['logging_params'] = {};\n");
        for (Entry<String, String> param : this.mParams.entrySet()) {
            paramsScript.append(String.format(onObject + "['params']['%s'] = '%s';\n", new Object[]{param.getKey(), param.getValue()}));
        }
        for (Entry<String, String> param2 : this.mLoggingParams.entrySet()) {
            paramsScript.append(String.format(onObject + "['logging_params']['%s'] = '%s'\n;", new Object[]{param2.getKey(), param2.getValue()}));
        }
        return paramsScript.toString();
    }
}
