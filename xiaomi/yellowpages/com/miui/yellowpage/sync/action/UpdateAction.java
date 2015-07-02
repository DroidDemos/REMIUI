package com.miui.yellowpage.sync.action;

import android.text.TextUtils;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.sync.a.a.a;
import com.miui.yellowpage.sync.a.a.e;
import com.miui.yellowpage.sync.a.a.f;
import com.miui.yellowpage.sync.a.a.g;
import com.miui.yellowpage.sync.a.a.h;
import com.miui.yellowpage.sync.a.a.i;
import com.miui.yellowpage.sync.a.a.j;
import com.miui.yellowpage.sync.a.b.b;
import com.miui.yellowpage.sync.a.b.d;
import com.miui.yellowpage.sync.a.c.c;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateAction implements Serializable {
    protected Action mAction;
    protected boolean mBuiltin;
    protected boolean mForced;
    protected Type mType;
    protected boolean mWifiOnly;

    public enum Action {
        UPDATE,
        DELETE;

        public static Action cn(String str) {
            for (Action action : values()) {
                if (TextUtils.equals(str, action.toString().toLowerCase())) {
                    return action;
                }
            }
            return null;
        }
    }

    public enum Type {
        PULL_TASK_DAEMON("pull_task_daemon", b.class, a.class),
        UGC("ugc", c.class, a.class),
        SUBSCRIBER("subscriber", com.miui.yellowpage.sync.a.b.c.class, a.class),
        ATD_CATEGORY("atd-category", com.miui.yellowpage.sync.a.a.b.class, d.class),
        YELLOWPAGE("yellowpage", a.class, d.class),
        WHITE_LIST("white-list", j.class, d.class),
        PROVIDER("provider", com.miui.yellowpage.sync.a.a.c.class, d.class),
        SETTINGS("settings", g.class, d.class),
        IVR("ivr", h.class, d.class),
        ANTISPAM("antispam", i.class, d.class),
        WEB_RESOURCE("web-resource", f.class, d.class),
        ORDINARY_YELLOWPAGE("unbuildin-yellowpage", d.class, c.class),
        MESSAGING_TEMPLATE("sms-template", e.class, d.class);
        
        private String mName;
        private Class mParser;
        private Class mTask;

        private Type(String str, Class cls, Class cls2) {
            this.mName = str;
            this.mTask = cls;
            this.mParser = cls2;
        }

        public Class aD() {
            return this.mTask;
        }

        public Class aE() {
            return this.mParser;
        }

        public static Type F(String str) {
            for (Type type : values()) {
                if (TextUtils.equals(str, type.mName)) {
                    return type;
                }
            }
            return null;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UpdateAction[");
        stringBuilder.append("action=").append(this.mAction).append(", ");
        stringBuilder.append("type=").append(this.mType).append(", ");
        stringBuilder.append("builtin=").append(this.mBuiltin).append(", ");
        stringBuilder.append("forced=").append(this.mForced).append(", ");
        stringBuilder.append("wifi only=").append(this.mWifiOnly);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public boolean cH() {
        return this.mAction == Action.DELETE;
    }

    public UpdateAction o(boolean z) {
        this.mWifiOnly = z;
        return this;
    }

    public boolean isWifiOnly() {
        return this.mWifiOnly;
    }

    public UpdateAction p(boolean z) {
        this.mBuiltin = z;
        return this;
    }

    public boolean cI() {
        return this.mForced;
    }

    public UpdateAction q(boolean z) {
        this.mForced = z;
        return this;
    }

    public Type cJ() {
        return this.mType;
    }

    public UpdateAction a(Type type) {
        this.mType = type;
        return this;
    }

    public UpdateAction a(Action action) {
        this.mAction = action;
        return this;
    }

    public static UpdateAction i(String str) {
        try {
            Type F = Type.F(new JSONObject(str).getString(MiniDefine.m));
            if (F != null) {
                return ((b) F.aE().newInstance()).i(str);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InstantiationException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
        return null;
    }
}
