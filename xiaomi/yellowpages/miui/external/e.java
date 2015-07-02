package miui.external;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.IBinder;
import java.lang.reflect.Field;
import miui.external.SdkConstants.SdkError;

final class e extends Instrumentation implements SdkConstants {
    private SdkError dx;

    private e(SdkError sdkError) {
        this.dx = sdkError;
    }

    static void a(SdkError sdkError) {
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field a = a(cls, invoke, (Instrumentation) cls.getMethod("getInstrumentation", new Class[0]).invoke(invoke, new Object[0]), null, null);
            Instrumentation instrumentation = (Instrumentation) a.get(invoke);
            e eVar = new e(sdkError);
            for (Class cls2 = Instrumentation.class; cls2 != null; cls2 = cls2.getSuperclass()) {
                for (Field field : cls2.getDeclaredFields()) {
                    field.setAccessible(true);
                    field.set(eVar, field.get(instrumentation));
                }
            }
            a.set(invoke, eVar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Field a(Class cls, Object obj, Object obj2, String str, Class cls2) {
        int length;
        int i;
        Field field;
        Field[] declaredFields = cls.getDeclaredFields();
        if (!(obj == null || obj2 == null)) {
            length = declaredFields.length;
            i = 0;
            while (i < length) {
                field = declaredFields[i];
                field.setAccessible(true);
                try {
                    if (field.get(obj) == obj2) {
                        break;
                    }
                    i++;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (str != null) {
            for (Field field2 : declaredFields) {
                if (field2.getName().equals(str)) {
                    field2.setAccessible(true);
                    break;
                }
            }
        }
        field2 = null;
        if (cls2 == null) {
            for (Field field3 : declaredFields) {
                if (field3.getType() == cls2 || field3.getType().isInstance(cls2)) {
                    if (field2 == null) {
                        field2 = field3;
                    } else {
                        throw new NoSuchFieldException("More than one matched field found: " + field2.getName() + " and " + field3.getName());
                    }
                }
            }
            if (field2 == null) {
                throw new NoSuchFieldException("No such field found of value " + obj2);
            }
            field2.setAccessible(true);
        }
        return field2;
    }

    public Activity newActivity(Class cls, Context context, IBinder iBinder, Application application, Intent intent, ActivityInfo activityInfo, CharSequence charSequence, Activity activity, String str, Object obj) {
        Intent intent2;
        Class cls2;
        if (cls.getSimpleName().startsWith("SdkError")) {
            intent2 = intent;
            cls2 = cls;
        } else {
            cls2 = SdkErrorActivity.class;
            if (intent == null) {
                intent = new Intent();
            }
            intent.putExtra("com.miui.sdk.error", this.dx);
            intent2 = intent;
        }
        return super.newActivity(cls2, context, iBinder, application, intent2, activityInfo, charSequence, activity, str, obj);
    }

    public Activity newActivity(ClassLoader classLoader, String str, Intent intent) {
        if (!str.startsWith("SdkError")) {
            str = SdkErrorActivity.class.getName();
            if (intent == null) {
                intent = new Intent();
            }
            intent.putExtra("com.miui.sdk.error", this.dx);
        }
        return super.newActivity(classLoader, str, intent);
    }
}
