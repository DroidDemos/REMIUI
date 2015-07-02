package android.support.v7.graphics;

import android.graphics.Color;
import com.google.android.wallet.instrumentmanager.R;

final class ColorUtils {
    static void RGBtoHSL(int r, int g, int b, float[] hsl) {
        float s;
        float h;
        float rf = ((float) r) / 255.0f;
        float gf = ((float) g) / 255.0f;
        float bf = ((float) b) / 255.0f;
        float max = Math.max(rf, Math.max(gf, bf));
        float min = Math.min(rf, Math.min(gf, bf));
        float deltaMaxMin = max - min;
        float l = (max + min) / 2.0f;
        if (max == min) {
            s = 0.0f;
            h = 0.0f;
        } else {
            if (max == rf) {
                h = ((gf - bf) / deltaMaxMin) % 6.0f;
            } else if (max == gf) {
                h = ((bf - rf) / deltaMaxMin) + 2.0f;
            } else {
                h = ((rf - gf) / deltaMaxMin) + 4.0f;
            }
            s = deltaMaxMin / (1.0f - Math.abs((2.0f * l) - 1.0f));
        }
        hsl[0] = (60.0f * h) % 360.0f;
        hsl[1] = s;
        hsl[2] = l;
    }

    static int HSLtoRGB(float[] hsl) {
        float h = hsl[0];
        float s = hsl[1];
        float l = hsl[2];
        float c = (1.0f - Math.abs((2.0f * l) - 1.0f)) * s;
        float m = l - (0.5f * c);
        float x = c * (1.0f - Math.abs(((h / 60.0f) % 2.0f) - 1.0f));
        int r = 0;
        int g = 0;
        int b = 0;
        switch (((int) h) / 60) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                r = Math.round(255.0f * (c + m));
                g = Math.round(255.0f * (x + m));
                b = Math.round(255.0f * m);
                break;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                r = Math.round(255.0f * (x + m));
                g = Math.round(255.0f * (c + m));
                b = Math.round(255.0f * m);
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                r = Math.round(255.0f * m);
                g = Math.round(255.0f * (c + m));
                b = Math.round(255.0f * (x + m));
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                r = Math.round(255.0f * m);
                g = Math.round(255.0f * (x + m));
                b = Math.round(255.0f * (c + m));
                break;
            case R.styleable.WalletImFormEditText_required /*4*/:
                r = Math.round(255.0f * (x + m));
                g = Math.round(255.0f * m);
                b = Math.round(255.0f * (c + m));
                break;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                r = Math.round(255.0f * (c + m));
                g = Math.round(255.0f * m);
                b = Math.round(255.0f * (x + m));
                break;
        }
        return Color.rgb(Math.max(0, Math.min(255, r)), Math.max(0, Math.min(255, g)), Math.max(0, Math.min(255, b)));
    }
}
