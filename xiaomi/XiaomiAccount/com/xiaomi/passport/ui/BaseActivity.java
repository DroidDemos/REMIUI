package com.xiaomi.passport.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import com.xiaomi.account.R;
import com.xiaomi.passport.PassportExternal;
import com.xiaomi.passport.utils.SystemBarTintManager;
import java.lang.reflect.InvocationTargetException;

public class BaseActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        if (PassportExternal.getActivityInterface() != null) {
            PassportExternal.getActivityInterface().onPreCreate(this);
        }
        super.onCreate(savedInstanceState);
        if (isTranslucentStatusBar()) {
            setTranslucentStatusBar();
        }
        if (PassportExternal.getActivityInterface() != null) {
            PassportExternal.getActivityInterface().onPostCreate(this);
        }
    }

    public void setContentView(int layoutResID) {
        setContentView(LayoutInflater.from(this).inflate(layoutResID, null));
    }

    public void setContentView(View view) {
        if (isTranslucentStatusBar()) {
            view.setFitsSystemWindows(true);
        }
        super.setContentView(view);
    }

    protected void setTranslucentStatusBar() {
        try {
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Window window = getWindow();
            window.setFlags(67108864, 67108864);
            int tranceFlag = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT").getInt(layoutParams);
            int darkModeFlag = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(layoutParams);
            window.getClass().getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE}).invoke(window, new Object[]{Integer.valueOf(tranceFlag | darkModeFlag), Integer.valueOf(tranceFlag | darkModeFlag)});
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintEnabled(true);
            tintManager.setTintColor(getResources().getColor(R.color.passport_title_bar_color_white));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
        } catch (IllegalArgumentException e5) {
            e5.printStackTrace();
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
        }
    }

    protected boolean isTranslucentStatusBar() {
        boolean setValue = true;
        if (PassportExternal.getActivityInterface() != null) {
            setValue = PassportExternal.getActivityInterface().useTranslucentStatusBar(this);
        }
        return setValue && needTranslucentStatusBar();
    }

    protected boolean needTranslucentStatusBar() {
        return true;
    }
}
