package miui.external;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import miui.external.SdkConstants.SdkError;

public class i extends Application implements SdkConstants {
    private boolean HO;
    private h HP;

    public i() {
        if (i() && j()) {
            this.HO = true;
            this.HP = hP();
        }
    }

    private boolean i() {
        try {
            if (b.isMiuiSystem() || d.load(b.getApkPath(null, "com.miui.sdk", "miui"), null, b.getLibPath(null, "com.miui.sdk"), i.class.getClassLoader())) {
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
            int intValue = ((Integer) Class.forName("com.miui.internal.core.SdkManager").getMethod("initialize", new Class[]{Application.class, Map.class}).invoke(null, new Object[]{this, hashMap})).intValue();
            if (intValue == 0) {
                return true;
            }
            d("initialize", intValue);
            return false;
        } catch (Throwable th) {
            a(th);
            return false;
        }
    }

    private boolean k() {
        try {
            HashMap hashMap = new HashMap();
            int intValue = ((Integer) Class.forName("com.miui.internal.core.SdkManager").getMethod("start", new Class[]{Map.class}).invoke(null, new Object[]{hashMap})).intValue();
            if (intValue == 1) {
                e.a(SdkError.LOW_SDK_VERSION);
                return false;
            } else if (intValue == 0) {
                return true;
            } else {
                d("start", intValue);
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
        Log.e("miuisdk", "MIUI SDK encounter errors, please contact miuisdk@xiaomi.com for support.", th);
        e.a(SdkError.GENERIC);
    }

    private void d(String str, int i) {
        Log.e("miuisdk", "MIUI SDK encounter errors, please contact miuisdk@xiaomi.com for support. phase: " + str + " code: " + i);
        e.a(SdkError.GENERIC);
    }

    public h hP() {
        return null;
    }

    public final void onCreate() {
        if (this.HO) {
            if (this.HP != null) {
                this.HP.a(this);
            }
            if (!k()) {
                return;
            }
            if (this.HP != null) {
                this.HP.onCreate();
            } else {
                ao();
            }
        }
    }

    final void ao() {
        super.onCreate();
    }

    public final void onTerminate() {
        if (this.HP != null) {
            this.HP.onTerminate();
        } else {
            cG();
        }
    }

    final void cG() {
        super.onTerminate();
    }

    public final void onLowMemory() {
        if (this.HP != null) {
            this.HP.onLowMemory();
        } else {
            bu();
        }
    }

    final void bu() {
        super.onLowMemory();
    }

    public final void onTrimMemory(int i) {
        if (this.HP != null) {
            this.HP.onTrimMemory(i);
        } else {
            a(i);
        }
    }

    final void a(int i) {
        super.onTrimMemory(i);
    }

    public final void onConfigurationChanged(Configuration configuration) {
        if (this.HP != null) {
            this.HP.onConfigurationChanged(configuration);
        } else {
            a(configuration);
        }
    }

    final void a(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
}
