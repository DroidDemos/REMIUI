package android.support.v7.internal.view.menu;

public interface MenuView {

    public interface ItemView {
        MenuItemImpl getItemData();

        void initialize(MenuItemImpl menuItemImpl, int i);

        boolean prefersCondensedTitle();
    }

    void initialize(MenuBuilder menuBuilder);
}
