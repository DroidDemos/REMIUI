package miui.external;

import android.content.res.Configuration;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import miui.external.SdkConstants.SdkError;

public class Application extends android.app.Application implements SdkConstants {
    private static final String PACKAGE_NAME = "com.miui.sdk";
    private boolean k;
    private ApplicationDelegate l;

    public Application() {
        if (i() && j()) {
            this.k = true;
            this.l = onCreateApplicationDelegate();
        }
    }

    private boolean i() {
        try {
            if (d.isMiuiSystem() || b.load(d.getApkPath(null, PACKAGE_NAME, d.MIUI_SYSTEM_APK_NAME), null, d.getLibPath(null, PACKAGE_NAME), Application.class.getClassLoader())) {
                return true;
            }
            e.a(SdkError.NO_SDK);
            return false;
        } catch (Throwable th) {
            a(th);
            return false;
        }
    }

    private boolean j() {
        try {
            HashMap hashMap = new HashMap();
            int intValue = ((Integer) Class.forName(SdkConstants.a).getMethod("initialize", new Class[]{android.app.Application.class, Map.class}).invoke(null, new Object[]{this, hashMap})).intValue();
            if (intValue == 0) {
                return true;
            }
            a("initialize", intValue);
            return false;
        } catch (Throwable th) {
            a(th);
            return false;
        }
    }

    private boolean k() {
        try {
            HashMap hashMap = new HashMap();
            int intValue = ((Integer) Class.forName(SdkConstants.a).getMethod("start", new Class[]{Map.class}).invoke(null, new Object[]{hashMap})).intValue();
            if (intValue == 1) {
                e.a(SdkError.LOW_SDK_VERSION);
                return false;
            } else if (intValue == 0) {
                return true;
            } else {
                a("start", intValue);
                return false;
            }
        } catch (Throwable th) {
            a(th);
            return false;
        }
    }

    private void a(Throwable th) {
        while (th != null && th.getCause() != null) {
            if (!(th instanceof InvocationTargetException)) {
                if (!(th instanceof ExceptionInInitializerError)) {
                    break;
                }
                th = th.getCause();
            } else {
                th = th.getCause();
            }
        }
        Log.e(SdkConstants.LOG_TAG, "MIUI SDK encounter errors, please contact miuisdk@xiaomi.com for support.", th);
        e.a(SdkError.GENERIC);
    }

    private void a(String str, int i) {
        Log.e(SdkConstants.LOG_TAG, "MIUI SDK encounter errors, please contact miuisdk@xiaomi.com for support. phase: " + str + " code: " + i);
        e.a(SdkError.GENERIC);
    }

    public ApplicationDelegate onCreateApplicationDelegate() {
        return null;
    }

    public final ApplicationDelegate getApplicationDelegate() {
        return this.l;
    }

    public final void onCreate() {
        if (this.k) {
            if (this.l != null) {
                this.l.a(this);
            }
            if (!k()) {
                return;
            }
            if (this.l != null) {
                this.l.onCreate();
            } else {
                l();
            }
        }
    }

    final void l() {
        super.onCreate();
    }

    public final void onTerminate() {
        if (this.l != null) {
            this.l.onTerminate();
        } else {
            m();
        }
    }

    final void m() {
        super.onTerminate();
    }

    public final void onLowMemory() {
        if (this.l != null) {
            this.l.onLowMemory();
        } else {
            n();
        }
    }

    final void n() {
        super.onLowMemory();
    }

    public final void onTrimMemory(int i) {
        if (this.l != null) {
            this.l.onTrimMemory(i);
        } else {
            a(i);
        }
    }

    final void a(int i) {
        super.onTrimMemory(i);
    }

    public final void onConfigurationChanged(Configuration configuration) {
        if (this.l != null) {
            this.l.onConfigurationChanged(configuration);
        } else {
            a(configuration);
        }
    }

    final void a(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
}
