package miui.external;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

class d {
    d() {
    }

    public static boolean load(String str, String str2, String str3, ClassLoader classLoader) {
        try {
            ClassLoader pathClassLoader;
            Object a = a(classLoader);
            if (str2 == null) {
                pathClassLoader = new PathClassLoader(str, str3, classLoader.getParent());
            } else {
                pathClassLoader = new DexClassLoader(str, str2, str3, classLoader.getParent());
            }
            Object a2 = a(pathClassLoader);
            a(a, ((Object[]) b(a2).get(a2))[0]);
            if (str3 != null) {
                a(a, new File(str3));
            }
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e2) {
        } catch (ClassNotFoundException e3) {
        } catch (NoSuchFieldException e4) {
        }
        return true;
    }

    private static Object a(ClassLoader classLoader) {
        if (classLoader instanceof BaseDexClassLoader) {
            Field[] declaredFields = BaseDexClassLoader.class.getDeclaredFields();
            int length = declaredFields.length;
            int i = 0;
            while (i < length) {
                Field field = declaredFields[i];
                if ("dalvik.system.DexPathList".equals(field.getType().getName())) {
                    field.setAccessible(true);
                    try {
                        return field.get(classLoader);
                    } catch (IllegalArgumentException e) {
                    } catch (IllegalAccessException e2) {
                    }
                } else {
                    i++;
                }
            }
        }
        throw new NoSuchFieldException("dexPathList field not found.");
    }

    private static void a(Object obj, Object obj2) {
        Field b = b(obj);
        Object[] objArr = (Object[]) b.get(obj);
        Object[] objArr2 = (Object[]) Array.newInstance(Class.forName("dalvik.system.DexPathList$Element"), objArr.length + 1);
        objArr2[0] = obj2;
        System.arraycopy(objArr, 0, objArr2, 1, objArr.length);
        b.set(obj, objArr2);
    }

    private static void a(Object obj, File file) {
        Field c = c(obj);
        File[] fileArr = (File[]) c.get(obj);
        Object obj2 = new File[(fileArr.length + 1)];
        obj2[0] = file;
        System.arraycopy(fileArr, 0, obj2, 1, fileArr.length);
        c.set(obj, obj2);
    }

    private static Field b(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            Class type = field.getType();
            if (type.isArray() && "dalvik.system.DexPathList$Element".equals(type.getComponentType().getName())) {
                field.setAccessible(true);
                return field;
            }
        }
        throw new NoSuchFieldException("dexElements field not found.");
    }

    private static Field c(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            Class type = field.getType();
            if (type.isArray() && type.getComponentType() == File.class) {
                field.setAccessible(true);
                return field;
            }
        }
        throw new NoSuchFieldException("nativeLibraryDirectories field not found.");
    }
}
