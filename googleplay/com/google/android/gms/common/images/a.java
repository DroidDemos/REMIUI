package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.images.ImageManager.OnImageLoadedListener;
import com.google.android.gms.internal.jq;
import com.google.android.gms.internal.js;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.kl;
import java.lang.ref.WeakReference;

public abstract class a {
    final a UM;
    protected int UO;
    protected OnImageLoadedListener UR;
    protected int UV;

    static final class a {
        public final Uri uri;

        public a(Uri uri) {
            this.uri = uri;
        }

        public boolean equals(Object obj) {
            if (obj instanceof a) {
                return this == obj ? true : kl.equal(((a) obj).uri, this.uri);
            } else {
                return false;
            }
        }

        public int hashCode() {
            return kl.hashCode(this.uri);
        }
    }

    public static final class c extends a {
        private WeakReference<OnImageLoadedListener> UX;

        protected void a(Drawable drawable, boolean z, boolean z2, boolean z3) {
            if (!z2) {
                OnImageLoadedListener onImageLoadedListener = (OnImageLoadedListener) this.UX.get();
                if (onImageLoadedListener != null) {
                    onImageLoadedListener.onImageLoaded(this.UM.uri, drawable, z3);
                }
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof c)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            c cVar = (c) obj;
            OnImageLoadedListener onImageLoadedListener = (OnImageLoadedListener) this.UX.get();
            OnImageLoadedListener onImageLoadedListener2 = (OnImageLoadedListener) cVar.UX.get();
            boolean z = onImageLoadedListener2 != null && onImageLoadedListener != null && kl.equal(onImageLoadedListener2, onImageLoadedListener) && kl.equal(cVar.UM, this.UM);
            return z;
        }

        public int hashCode() {
            return kl.hashCode(this.UM);
        }
    }

    private Drawable a(Context context, js jsVar, int i) {
        Resources resources = context.getResources();
        if (this.UV <= 0) {
            return resources.getDrawable(i);
        }
        com.google.android.gms.internal.js.a aVar = new com.google.android.gms.internal.js.a(i, this.UV);
        Drawable drawable = (Drawable) jsVar.get(aVar);
        if (drawable != null) {
            return drawable;
        }
        drawable = resources.getDrawable(i);
        if ((this.UV & 1) != 0) {
            drawable = a(resources, drawable);
        }
        jsVar.put(aVar, drawable);
        return drawable;
    }

    protected Drawable a(Resources resources, Drawable drawable) {
        return jq.a(resources, drawable);
    }

    void a(Context context, Bitmap bitmap, boolean z) {
        ju.h(bitmap);
        if ((this.UV & 1) != 0) {
            bitmap = jq.a(bitmap);
        }
        Drawable bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        if (this.UR != null) {
            this.UR.onImageLoaded(this.UM.uri, bitmapDrawable, true);
        }
        a(bitmapDrawable, z, false, true);
    }

    void a(Context context, js jsVar, boolean z) {
        Drawable drawable = null;
        if (this.UO != 0) {
            drawable = a(context, jsVar, this.UO);
        }
        if (this.UR != null) {
            this.UR.onImageLoaded(this.UM.uri, drawable, false);
        }
        a(drawable, z, false, false);
    }

    protected abstract void a(Drawable drawable, boolean z, boolean z2, boolean z3);
}
