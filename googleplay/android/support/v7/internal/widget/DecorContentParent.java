package android.support.v7.internal.widget;

import android.support.v7.internal.app.WindowCallback;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.view.Menu;

public interface DecorContentParent {
    boolean canShowOverflowMenu();

    void dismissPopups();

    boolean hideOverflowMenu();

    void initFeature(int i);

    boolean isOverflowMenuShowPending();

    boolean isOverflowMenuShowing();

    void setMenu(Menu menu, Callback callback);

    void setMenuPrepared();

    void setWindowCallback(WindowCallback windowCallback);

    void setWindowTitle(CharSequence charSequence);

    boolean showOverflowMenu();
}
