package miui.external;

import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.ContextWrapper;
import android.content.res.Configuration;

public abstract class ApplicationDelegate extends ContextWrapper implements ComponentCallbacks2 {
    private Application o;

    public ApplicationDelegate() {
        super(null);
    }

    void a(Application application) {
        this.o = application;
        attachBaseContext(application);
    }

    public Application getApplication() {
        return this.o;
    }

    public void onCreate() {
        this.o.l();
    }

    public void onTerminate() {
        this.o.m();
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.o.a(configuration);
    }

    public void onLowMemory() {
        this.o.n();
    }

    public void onTrimMemory(int i) {
        this.o.a(i);
    }

    public void registerComponentCallbacks(ComponentCallbacks componentCallbacks) {
        this.o.registerComponentCallbacks(componentCallbacks);
    }

    public void unregisterComponentCallbacks(ComponentCallbacks componentCallbacks) {
        this.o.unregisterComponentCallbacks(componentCallbacks);
    }

    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.o.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.o.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }
}
