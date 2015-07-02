package com.google.wireless.android.play.playlog.proto;

import com.google.android.gms.internal.xx;
import com.google.android.gms.internal.xy;
import com.google.android.gms.internal.yc;
import com.google.android.gms.internal.ye;
import com.google.android.gms.internal.yh;
import java.io.IOException;
import java.util.Arrays;

public interface ClientAnalytics {

    public static final class ActiveExperiments extends xy<ActiveExperiments> {
        public String[] clientAlteringExperiment;
        public int[] gwsExperiment;
        public String[] otherExperiment;

        public ActiveExperiments() {
            clear();
        }

        protected int c() {
            int i;
            int i2;
            int i3;
            int i4 = 0;
            int c = super.c();
            if (this.clientAlteringExperiment == null || this.clientAlteringExperiment.length <= 0) {
                i = c;
            } else {
                i2 = 0;
                i3 = 0;
                for (String str : this.clientAlteringExperiment) {
                    if (str != null) {
                        i3++;
                        i2 += xx.ex(str);
                    }
                }
                i = (c + i2) + (i3 * 1);
            }
            if (this.otherExperiment != null && this.otherExperiment.length > 0) {
                i3 = 0;
                c = 0;
                for (String str2 : this.otherExperiment) {
                    if (str2 != null) {
                        c++;
                        i3 += xx.ex(str2);
                    }
                }
                i = (i + i3) + (c * 1);
            }
            if (this.gwsExperiment == null || this.gwsExperiment.length <= 0) {
                return i;
            }
            i2 = 0;
            while (i4 < this.gwsExperiment.length) {
                i2 += xx.mi(this.gwsExperiment[i4]);
                i4++;
            }
            return (i + i2) + (this.gwsExperiment.length * 1);
        }

        public ActiveExperiments clear() {
            this.clientAlteringExperiment = yh.aYA;
            this.otherExperiment = yh.aYA;
            this.gwsExperiment = yh.aYv;
            this.aYj = null;
            this.aYu = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ActiveExperiments)) {
                return false;
            }
            ActiveExperiments activeExperiments = (ActiveExperiments) o;
            return (yc.equals(this.clientAlteringExperiment, activeExperiments.clientAlteringExperiment) && yc.equals(this.otherExperiment, activeExperiments.otherExperiment) && yc.equals(this.gwsExperiment, activeExperiments.gwsExperiment)) ? a(activeExperiments) : false;
        }

        public int hashCode() {
            return ((((((yc.hashCode(this.clientAlteringExperiment) + 527) * 31) + yc.hashCode(this.otherExperiment)) * 31) + yc.hashCode(this.gwsExperiment)) * 31) + vL();
        }

        public void writeTo(xx output) throws IOException {
            int i = 0;
            if (this.clientAlteringExperiment != null && this.clientAlteringExperiment.length > 0) {
                for (String str : this.clientAlteringExperiment) {
                    if (str != null) {
                        output.b(1, str);
                    }
                }
            }
            if (this.otherExperiment != null && this.otherExperiment.length > 0) {
                for (String str2 : this.otherExperiment) {
                    if (str2 != null) {
                        output.b(2, str2);
                    }
                }
            }
            if (this.gwsExperiment != null && this.gwsExperiment.length > 0) {
                while (i < this.gwsExperiment.length) {
                    output.C(3, this.gwsExperiment[i]);
                    i++;
                }
            }
            super.writeTo(output);
        }
    }

    public static final class AppUsage1pLogEvent extends xy<AppUsage1pLogEvent> {
        public String androidPackageName;
        public int appType;
        public String version;

        public AppUsage1pLogEvent() {
            clear();
        }

        protected int c() {
            int c = super.c();
            if (this.appType != 0) {
                c += xx.E(1, this.appType);
            }
            if (!this.androidPackageName.equals("")) {
                c += xx.j(2, this.androidPackageName);
            }
            return !this.version.equals("") ? c + xx.j(3, this.version) : c;
        }

        public AppUsage1pLogEvent clear() {
            this.appType = 0;
            this.androidPackageName = "";
            this.version = "";
            this.aYj = null;
            this.aYu = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppUsage1pLogEvent)) {
                return false;
            }
            AppUsage1pLogEvent appUsage1pLogEvent = (AppUsage1pLogEvent) o;
            if (this.appType != appUsage1pLogEvent.appType) {
                return false;
            }
            if (this.androidPackageName == null) {
                if (appUsage1pLogEvent.androidPackageName != null) {
                    return false;
                }
            } else if (!this.androidPackageName.equals(appUsage1pLogEvent.androidPackageName)) {
                return false;
            }
            if (this.version == null) {
                if (appUsage1pLogEvent.version != null) {
                    return false;
                }
            } else if (!this.version.equals(appUsage1pLogEvent.version)) {
                return false;
            }
            return a(appUsage1pLogEvent);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.androidPackageName == null ? 0 : this.androidPackageName.hashCode()) + ((this.appType + 527) * 31)) * 31;
            if (this.version != null) {
                i = this.version.hashCode();
            }
            return ((hashCode + i) * 31) + vL();
        }

        public void writeTo(xx output) throws IOException {
            if (this.appType != 0) {
                output.C(1, this.appType);
            }
            if (!this.androidPackageName.equals("")) {
                output.b(2, this.androidPackageName);
            }
            if (!this.version.equals("")) {
                output.b(3, this.version);
            }
            super.writeTo(output);
        }
    }

    public static final class LogEvent extends xy<LogEvent> {
        public AppUsage1pLogEvent appUsage1P;
        public int eventCode;
        public int eventFlowId;
        public long eventTimeMs;
        public ActiveExperiments exp;
        public boolean isUserInitiated;
        public byte[] sourceExtension;
        public byte[] sourceExtensionJs;
        public byte[] sourceExtensionJson;
        public String tag;
        public String testId;
        public long timezoneOffsetSeconds;
        public LogEventKeyValues[] value;

        public LogEvent() {
            clear();
        }

        protected int c() {
            int c = super.c();
            if (this.eventTimeMs != 0) {
                c += xx.d(1, this.eventTimeMs);
            }
            if (!this.tag.equals("")) {
                c += xx.j(2, this.tag);
            }
            if (this.value != null && this.value.length > 0) {
                int i = c;
                for (ye yeVar : this.value) {
                    if (yeVar != null) {
                        i += xx.c(3, yeVar);
                    }
                }
                c = i;
            }
            if (!Arrays.equals(this.sourceExtension, yh.aYC)) {
                c += xx.b(6, this.sourceExtension);
            }
            if (this.exp != null) {
                c += xx.c(7, this.exp);
            }
            if (!Arrays.equals(this.sourceExtensionJs, yh.aYC)) {
                c += xx.b(8, this.sourceExtensionJs);
            }
            if (this.appUsage1P != null) {
                c += xx.c(9, this.appUsage1P);
            }
            if (this.isUserInitiated) {
                c += xx.c(10, this.isUserInitiated);
            }
            if (this.eventCode != 0) {
                c += xx.E(11, this.eventCode);
            }
            if (this.eventFlowId != 0) {
                c += xx.E(12, this.eventFlowId);
            }
            if (!Arrays.equals(this.sourceExtensionJson, yh.aYC)) {
                c += xx.b(13, this.sourceExtensionJson);
            }
            if (!this.testId.equals("")) {
                c += xx.j(14, this.testId);
            }
            return this.timezoneOffsetSeconds != 0 ? c + xx.e(15, this.timezoneOffsetSeconds) : c;
        }

        public LogEvent clear() {
            this.eventTimeMs = 0;
            this.tag = "";
            this.eventCode = 0;
            this.eventFlowId = 0;
            this.isUserInitiated = false;
            this.value = LogEventKeyValues.emptyArray();
            this.appUsage1P = null;
            this.sourceExtension = yh.aYC;
            this.sourceExtensionJs = yh.aYC;
            this.sourceExtensionJson = yh.aYC;
            this.exp = null;
            this.testId = "";
            this.timezoneOffsetSeconds = 0;
            this.aYj = null;
            this.aYu = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LogEvent)) {
                return false;
            }
            LogEvent logEvent = (LogEvent) o;
            if (this.eventTimeMs != logEvent.eventTimeMs) {
                return false;
            }
            if (this.tag == null) {
                if (logEvent.tag != null) {
                    return false;
                }
            } else if (!this.tag.equals(logEvent.tag)) {
                return false;
            }
            if (this.eventCode != logEvent.eventCode || this.eventFlowId != logEvent.eventFlowId || this.isUserInitiated != logEvent.isUserInitiated || !yc.equals(this.value, logEvent.value)) {
                return false;
            }
            if (this.appUsage1P == null) {
                if (logEvent.appUsage1P != null) {
                    return false;
                }
            } else if (!this.appUsage1P.equals(logEvent.appUsage1P)) {
                return false;
            }
            if (!Arrays.equals(this.sourceExtension, logEvent.sourceExtension) || !Arrays.equals(this.sourceExtensionJs, logEvent.sourceExtensionJs) || !Arrays.equals(this.sourceExtensionJson, logEvent.sourceExtensionJson)) {
                return false;
            }
            if (this.exp == null) {
                if (logEvent.exp != null) {
                    return false;
                }
            } else if (!this.exp.equals(logEvent.exp)) {
                return false;
            }
            if (this.testId == null) {
                if (logEvent.testId != null) {
                    return false;
                }
            } else if (!this.testId.equals(logEvent.testId)) {
                return false;
            }
            return this.timezoneOffsetSeconds == logEvent.timezoneOffsetSeconds ? a(logEvent) : false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.exp == null ? 0 : this.exp.hashCode()) + (((((((((this.appUsage1P == null ? 0 : this.appUsage1P.hashCode()) + (((((this.isUserInitiated ? 1231 : 1237) + (((((((this.tag == null ? 0 : this.tag.hashCode()) + ((((int) (this.eventTimeMs ^ (this.eventTimeMs >>> 32))) + 527) * 31)) * 31) + this.eventCode) * 31) + this.eventFlowId) * 31)) * 31) + yc.hashCode(this.value)) * 31)) * 31) + Arrays.hashCode(this.sourceExtension)) * 31) + Arrays.hashCode(this.sourceExtensionJs)) * 31) + Arrays.hashCode(this.sourceExtensionJson)) * 31)) * 31;
            if (this.testId != null) {
                i = this.testId.hashCode();
            }
            return ((((hashCode + i) * 31) + ((int) (this.timezoneOffsetSeconds ^ (this.timezoneOffsetSeconds >>> 32)))) * 31) + vL();
        }

        public void writeTo(xx output) throws IOException {
            if (this.eventTimeMs != 0) {
                output.b(1, this.eventTimeMs);
            }
            if (!this.tag.equals("")) {
                output.b(2, this.tag);
            }
            if (this.value != null && this.value.length > 0) {
                for (ye yeVar : this.value) {
                    if (yeVar != null) {
                        output.a(3, yeVar);
                    }
                }
            }
            if (!Arrays.equals(this.sourceExtension, yh.aYC)) {
                output.a(6, this.sourceExtension);
            }
            if (this.exp != null) {
                output.a(7, this.exp);
            }
            if (!Arrays.equals(this.sourceExtensionJs, yh.aYC)) {
                output.a(8, this.sourceExtensionJs);
            }
            if (this.appUsage1P != null) {
                output.a(9, this.appUsage1P);
            }
            if (this.isUserInitiated) {
                output.b(10, this.isUserInitiated);
            }
            if (this.eventCode != 0) {
                output.C(11, this.eventCode);
            }
            if (this.eventFlowId != 0) {
                output.C(12, this.eventFlowId);
            }
            if (!Arrays.equals(this.sourceExtensionJson, yh.aYC)) {
                output.a(13, this.sourceExtensionJson);
            }
            if (!this.testId.equals("")) {
                output.b(14, this.testId);
            }
            if (this.timezoneOffsetSeconds != 0) {
                output.c(15, this.timezoneOffsetSeconds);
            }
            super.writeTo(output);
        }
    }

    public static final class LogEventKeyValues extends xy<LogEventKeyValues> {
        private static volatile LogEventKeyValues[] aYG;
        public String key;
        public String value;

        public LogEventKeyValues() {
            clear();
        }

        public static LogEventKeyValues[] emptyArray() {
            if (aYG == null) {
                synchronized (yc.aYt) {
                    if (aYG == null) {
                        aYG = new LogEventKeyValues[0];
                    }
                }
            }
            return aYG;
        }

        protected int c() {
            int c = super.c();
            if (!this.key.equals("")) {
                c += xx.j(1, this.key);
            }
            return !this.value.equals("") ? c + xx.j(2, this.value) : c;
        }

        public LogEventKeyValues clear() {
            this.key = "";
            this.value = "";
            this.aYj = null;
            this.aYu = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LogEventKeyValues)) {
                return false;
            }
            LogEventKeyValues logEventKeyValues = (LogEventKeyValues) o;
            if (this.key == null) {
                if (logEventKeyValues.key != null) {
                    return false;
                }
            } else if (!this.key.equals(logEventKeyValues.key)) {
                return false;
            }
            if (this.value == null) {
                if (logEventKeyValues.value != null) {
                    return false;
                }
            } else if (!this.value.equals(logEventKeyValues.value)) {
                return false;
            }
            return a(logEventKeyValues);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.key == null ? 0 : this.key.hashCode()) + 527) * 31;
            if (this.value != null) {
                i = this.value.hashCode();
            }
            return ((hashCode + i) * 31) + vL();
        }

        public void writeTo(xx output) throws IOException {
            if (!this.key.equals("")) {
                output.b(1, this.key);
            }
            if (!this.value.equals("")) {
                output.b(2, this.value);
            }
            super.writeTo(output);
        }
    }
}
