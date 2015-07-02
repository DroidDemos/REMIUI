package miui.external;

import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.ContextWrapper;
import android.content.res.Configuration;

public abstract class h extends ContextWrapper implements ComponentCallbacks2 {
    private i CR;

    public h() {
        super(null);
    }

    void a(i iVar) {
        this.CR = iVar;
        attachBaseContext(iVar);
    }

    public void onCreate() {
        this.CR.ao();
    }

    public void onTerminate() {
        this.CR.cG();
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.CR.a(configuration);
    }

    public void onLowMemory() {
        this.CR.bu();
    }

    public void onTrimMemory(int i) {
        this.CR.a(i);
    }

    public void registerComponentCallbacks(ComponentCallbacks componentCallbacks) {
        this.CR.registerComponentCallbacks(componentCallbacks);
    }

    public void unregisterComponentCallbacks(ComponentCallbacks componentCallbacks) {
        this.CR.unregisterComponentCallbacks(componentCallbacks);
    }
}
