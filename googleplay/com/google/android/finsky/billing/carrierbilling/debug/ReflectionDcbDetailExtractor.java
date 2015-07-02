package com.google.android.finsky.billing.carrierbilling.debug;

import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ReflectionDcbDetailExtractor implements DcbDetailExtractor {
    private Collection<DcbDetail> mCachedDetails;
    private final Object mObject;
    private final String mRootName;

    public ReflectionDcbDetailExtractor(Object o, String rootName) {
        this.mCachedDetails = null;
        this.mObject = o;
        this.mRootName = rootName;
    }

    public Collection<DcbDetail> getDetails() {
        if (this.mCachedDetails == null) {
            this.mCachedDetails = buildDetails(this.mObject, this.mRootName);
        }
        return this.mCachedDetails;
    }

    private static String methodNameToTitle(String methodName) {
        if (methodName.startsWith("get")) {
            return methodName.substring(3);
        }
        return methodName;
    }

    private static void buildDetailsHelper(Object value, String name, List<DcbDetail> result) {
        if (value == null) {
            result.add(new SimpleDetail(name, null));
            return;
        }
        Class<?> clz = value.getClass();
        boolean isArray = isArray(value);
        boolean isIterable = isIterable(value);
        if (!clz.isPrimitive()) {
            if (!clz.isAssignableFrom(Number.class)) {
                if (!clz.isAssignableFrom(String.class)) {
                    if (isIterable || isArray) {
                        Iterable<? extends Object> items;
                        if (isArray) {
                            items = Arrays.asList((Object[]) value);
                        } else {
                            items = (Iterable) value;
                        }
                        int x = 0;
                        for (Object item : items) {
                            buildDetailsHelper(item, name + "." + x, result);
                            x++;
                        }
                        return;
                    }
                    List<DcbDetail> tmpResults = Lists.newArrayList();
                    for (Method m : clz.getDeclaredMethods()) {
                        if (m.getName().startsWith("get") && (m.getModifiers() & 1) != 0) {
                            if (m.getParameterTypes().length == 0) {
                                try {
                                    buildDetailsHelper(m.invoke(value, new Object[0]), name + "." + methodNameToTitle(m.getName()), tmpResults);
                                } catch (IllegalArgumentException e) {
                                    FinskyLog.wtf(e, "Shouldn't happen with no-arg methods", new Object[0]);
                                } catch (IllegalAccessException e2) {
                                    FinskyLog.wtf(e2, "Shouldn't happen with public methods", new Object[0]);
                                } catch (InvocationTargetException e3) {
                                    String causeClass = "unknown";
                                    if (e3.getCause() != null) {
                                        causeClass = e3.getCause().getClass().getName();
                                    }
                                    FinskyLog.e("%s throw exception (%s): %s", m.getName(), causeClass, e3.getMessage());
                                }
                            }
                        }
                    }
                    if (tmpResults.isEmpty()) {
                        result.add(new SimpleDetail(name, value.toString()));
                        return;
                    }
                    result.addAll(tmpResults);
                    return;
                }
            }
        }
        result.add(new SimpleDetail(name, value.toString()));
    }

    private static Collection<DcbDetail> buildDetails(Object o, String rootName) {
        List<DcbDetail> result = new ArrayList();
        buildDetailsHelper(o, rootName, result);
        return result;
    }

    static boolean isArray(Object o) {
        if (o == null) {
            return false;
        }
        return o.getClass().isArray();
    }

    static boolean isIterable(Object o) {
        if (o == null) {
            return false;
        }
        return Iterable.class.isAssignableFrom(o.getClass());
    }
}
