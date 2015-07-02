package com.google.android.finsky.utils;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.UserManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Users {
    private final UserManagerFacade mUserManagerFacade;

    private static class UserManagerFacade {
        private UserManagerFacade() {
        }

        public boolean supportsMultipleUsers() {
            return false;
        }

        public boolean hasMultipleUsers() {
            return false;
        }

        public boolean isLimitedUser() {
            return false;
        }
    }

    private static class UserManagerSystemFacade extends UserManagerFacade {
        private Method mGetUsers;
        private Method mIsLimited;
        private Method mSupportsMultipleUsers;
        private UserManager mUserManager;

        public UserManagerSystemFacade(Context context) throws NoSuchMethodException {
            super();
            this.mUserManager = (UserManager) context.getSystemService("user");
            Class<?> userManagerClass = this.mUserManager.getClass();
            Class<?>[] emptyParamList = new Class[0];
            Method m = userManagerClass.getMethod("supportsMultipleUsers", emptyParamList);
            if (m.getReturnType().equals(Boolean.TYPE)) {
                this.mSupportsMultipleUsers = m;
            } else {
                FinskyLog.wtf("Return type %s is not correct for supportsMultipleUsers", m.getReturnType());
                this.mSupportsMultipleUsers = null;
            }
            m = userManagerClass.getMethod("getUsers", emptyParamList);
            if (m.getReturnType().equals(List.class)) {
                this.mGetUsers = m;
            } else {
                FinskyLog.wtf("Return type %s is not correct for getUsers", m.getReturnType());
                this.mGetUsers = null;
            }
            if (VERSION.SDK_INT >= 18) {
                m = userManagerClass.getMethod("isLinkedUser", emptyParamList);
                if (m.getReturnType().equals(Boolean.TYPE)) {
                    this.mIsLimited = m;
                    return;
                }
                FinskyLog.wtf("Return type %s is not correct for isLimited", m.getReturnType());
                this.mIsLimited = null;
            }
        }

        public boolean isLimitedUser() {
            if (this.mIsLimited == null) {
                return super.isLimitedUser();
            }
            try {
                return ((Boolean) this.mIsLimited.invoke(this.mUserManager, (Object[]) null)).booleanValue();
            } catch (IllegalArgumentException e) {
                return false;
            } catch (IllegalAccessException e2) {
                return false;
            } catch (InvocationTargetException e3) {
                return false;
            }
        }

        public boolean supportsMultipleUsers() {
            if (this.mSupportsMultipleUsers == null) {
                return super.supportsMultipleUsers();
            }
            try {
                return ((Boolean) this.mSupportsMultipleUsers.invoke(this.mUserManager, (Object[]) null)).booleanValue();
            } catch (IllegalArgumentException e) {
                return false;
            } catch (IllegalAccessException e2) {
                return false;
            } catch (InvocationTargetException e3) {
                return false;
            }
        }

        public boolean hasMultipleUsers() {
            if (this.mGetUsers == null) {
                return super.hasMultipleUsers();
            }
            try {
                return ((List) this.mGetUsers.invoke(this.mUserManager, (Object[]) null)).size() > 1;
            } catch (IllegalArgumentException e) {
                return false;
            } catch (IllegalAccessException e2) {
                return false;
            } catch (InvocationTargetException e3) {
                return false;
            }
        }
    }

    public Users(Context context) {
        if (VERSION.SDK_INT < 17) {
            this.mUserManagerFacade = new UserManagerFacade();
            return;
        }
        UserManagerFacade facade;
        try {
            facade = new UserManagerSystemFacade(context);
        } catch (NoSuchMethodException nsme) {
            FinskyLog.wtf("Unable to reflect into UserManager: %s", nsme);
            facade = new UserManagerFacade();
        }
        this.mUserManagerFacade = facade;
    }

    public boolean supportsMultiUser() {
        return this.mUserManagerFacade.supportsMultipleUsers();
    }

    public boolean hasMultipleUsers() {
        return supportsMultiUser() && this.mUserManagerFacade.hasMultipleUsers();
    }

    public boolean isLimitedUser() {
        return this.mUserManagerFacade.isLimitedUser();
    }
}
