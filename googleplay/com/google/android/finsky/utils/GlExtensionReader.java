package com.google.android.finsky.utils;

import android.opengl.GLES10;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

public class GlExtensionReader {
    private boolean mWasSystemUpgraded;

    public GlExtensionReader() {
        this.mWasSystemUpgraded = VendingUtils.wasSystemUpgraded();
    }

    public List<String> getGlExtensions() {
        List<String> sortedExtensions = Lists.newArrayList();
        String cachedExtensions = (String) VendingPreferences.CACHED_GL_EXTENSIONS.get();
        if (this.mWasSystemUpgraded || TextUtils.isEmpty(cachedExtensions)) {
            Set<String> unsortedExtensions;
            if (!((Boolean) VendingPreferences.GL_DRIVER_CRASHED.get()).booleanValue() || this.mWasSystemUpgraded) {
                VendingPreferences.GL_DRIVER_CRASHED.put(Boolean.valueOf(true));
                unsortedExtensions = getGlExtensionsFromDriver();
                VendingPreferences.GL_DRIVER_CRASHED.remove();
            } else {
                unsortedExtensions = Sets.newHashSet();
                unsortedExtensions.add("_android_driver_crashed");
            }
            sortedExtensions.addAll(unsortedExtensions);
            Collections.sort(sortedExtensions);
            VendingPreferences.CACHED_GL_EXTENSIONS.put(TextUtils.join(" ", sortedExtensions));
        } else {
            sortedExtensions.addAll(Arrays.asList(cachedExtensions.split(" ")));
        }
        return sortedExtensions;
    }

    private static void addExtensionsForConfig(EGL10 egl, EGLDisplay display, EGLConfig config, int[] surfaceSize, int[] contextAttribs, Set<String> glExtensions) {
        EGLContext context = egl.eglCreateContext(display, config, EGL10.EGL_NO_CONTEXT, contextAttribs);
        if (context != EGL10.EGL_NO_CONTEXT) {
            EGLSurface surface = egl.eglCreatePbufferSurface(display, config, surfaceSize);
            if (surface == EGL10.EGL_NO_SURFACE) {
                egl.eglDestroyContext(display, context);
                return;
            }
            egl.eglMakeCurrent(display, surface, surface, context);
            String extensionList = GLES10.glGetString(7939);
            if (!TextUtils.isEmpty(extensionList)) {
                for (String extension : extensionList.split(" ")) {
                    glExtensions.add(extension);
                }
            }
            egl.eglMakeCurrent(display, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            egl.eglDestroySurface(display, surface);
            egl.eglDestroyContext(display, context);
        }
    }

    Set<String> getGlExtensionsFromDriver() {
        Set<String> glExtensions = new HashSet();
        EGL10 egl = (EGL10) EGLContext.getEGL();
        if (egl == null) {
            FinskyLog.e("Couldn't get EGL", new Object[0]);
        } else {
            EGLDisplay display = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            egl.eglInitialize(display, new int[2]);
            int[] numConfigs = new int[1];
            if (egl.eglGetConfigs(display, null, 0, numConfigs)) {
                EGLConfig[] configs = new EGLConfig[numConfigs[0]];
                if (egl.eglGetConfigs(display, configs, numConfigs[0], numConfigs)) {
                    int[] surfaceSize = new int[]{12375, 1, 12374, 1, 12344};
                    int[] gles2 = new int[]{12440, 2, 12344};
                    int[] attrib = new int[1];
                    for (int i = 0; i < numConfigs[0]; i++) {
                        egl.eglGetConfigAttrib(display, configs[i], 12327, attrib);
                        if (attrib[0] != 12368) {
                            egl.eglGetConfigAttrib(display, configs[i], 12339, attrib);
                            if ((attrib[0] & 1) != 0) {
                                egl.eglGetConfigAttrib(display, configs[i], 12352, attrib);
                                if ((attrib[0] & 1) != 0) {
                                    addExtensionsForConfig(egl, display, configs[i], surfaceSize, null, glExtensions);
                                }
                                if ((attrib[0] & 4) != 0) {
                                    addExtensionsForConfig(egl, display, configs[i], surfaceSize, gles2, glExtensions);
                                }
                            }
                        }
                    }
                    egl.eglTerminate(display);
                } else {
                    FinskyLog.e("Couldn't get EGL configs", new Object[0]);
                }
            } else {
                FinskyLog.e("Couldn't get EGL config count", new Object[0]);
            }
        }
        return glExtensions;
    }
}
