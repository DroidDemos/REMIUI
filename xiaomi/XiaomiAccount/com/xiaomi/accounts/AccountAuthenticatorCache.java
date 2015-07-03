package com.xiaomi.accounts;

import android.accounts.AuthenticatorDescription;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import com.google.android.collect.Maps;
import com.xiaomi.passport.R;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

public class AccountAuthenticatorCache {
    private static final String TAG = "Account";
    private final String mAttributesName;
    private Context mContext;
    private final String mInterfaceName;
    private final String mMetaDataName;
    private Map<AuthenticatorDescription, ServiceInfo<AuthenticatorDescription>> mServices;
    private final Object mServicesLock;

    public static class ServiceInfo<V> {
        public final ComponentName componentName;
        public final V type;
        public final int uid;

        public ServiceInfo(V type, ComponentName componentName, int uid) {
            this.type = type;
            this.componentName = componentName;
            this.uid = uid;
        }

        public String toString() {
            return "ServiceInfo: " + this.type + ", " + this.componentName + ", uid " + this.uid;
        }
    }

    public AccountAuthenticatorCache(Context context) {
        this.mServicesLock = new Object();
        this.mContext = context;
        this.mInterfaceName = AccountManager.AUTHENTICATOR_META_DATA_NAME;
        this.mMetaDataName = AccountManager.AUTHENTICATOR_META_DATA_NAME;
        this.mAttributesName = MiAccountManager.LOGIN_ACCOUNTS_CHANGED_ACTION;
        generateServicesMap();
    }

    void generateServicesMap() {
        Iterator i$;
        PackageManager pm = this.mContext.getPackageManager();
        ArrayList<ServiceInfo<AuthenticatorDescription>> serviceInfos = new ArrayList();
        for (ResolveInfo resolveInfo : pm.queryIntentServices(new Intent(this.mInterfaceName), 128)) {
            ServiceInfo<AuthenticatorDescription> info;
            try {
                info = parseServiceInfo(resolveInfo);
                if (info == null) {
                    Log.w(TAG, "Unable to load service info " + resolveInfo.toString());
                } else {
                    serviceInfos.add(info);
                }
            } catch (XmlPullParserException e) {
                Log.w(TAG, "Unable to load service info " + resolveInfo.toString(), e);
            } catch (IOException e2) {
                Log.w(TAG, "Unable to load service info " + resolveInfo.toString(), e2);
            }
        }
        synchronized (this.mServicesLock) {
            this.mServices = Maps.newHashMap();
            i$ = serviceInfos.iterator();
            while (i$.hasNext()) {
                info = (ServiceInfo) i$.next();
                this.mServices.put(info.type, info);
            }
        }
    }

    private ServiceInfo<AuthenticatorDescription> parseServiceInfo(ResolveInfo service) throws XmlPullParserException, IOException {
        android.content.pm.ServiceInfo si = service.serviceInfo;
        ComponentName componentName = new ComponentName(si.packageName, si.name);
        PackageManager pm = this.mContext.getPackageManager();
        XmlResourceParser parser = null;
        try {
            parser = si.loadXmlMetaData(pm, this.mMetaDataName);
            if (parser == null) {
                throw new XmlPullParserException("No " + this.mMetaDataName + " meta-data");
            }
            AttributeSet attrs = Xml.asAttributeSet(parser);
            int type;
            do {
                type = parser.next();
                if (type == 1) {
                    break;
                }
            } while (type != 2);
            if (this.mAttributesName.equals(parser.getName())) {
                ServiceInfo<AuthenticatorDescription> serviceInfo;
                AuthenticatorDescription v = parseServiceAttributes(pm.getResourcesForApplication(si.applicationInfo), si.packageName, attrs);
                if (v == null) {
                    serviceInfo = null;
                    if (parser != null) {
                        parser.close();
                    }
                } else {
                    serviceInfo = new ServiceInfo(v, componentName, service.serviceInfo.applicationInfo.uid);
                    if (parser != null) {
                        parser.close();
                    }
                }
                return serviceInfo;
            }
            throw new XmlPullParserException("Meta-data does not start with " + this.mAttributesName + " tag");
        } catch (NameNotFoundException e) {
            throw new XmlPullParserException("Unable to load resources for pacakge " + si.packageName);
        } catch (Throwable th) {
            if (parser != null) {
                parser.close();
            }
        }
    }

    public AuthenticatorDescription parseServiceAttributes(Resources res, String packageName, AttributeSet attrs) {
        TypedArray sa = res.obtainAttributes(attrs, R.styleable.Passport_AccountAuthenticator);
        try {
            String accountType = sa.getString(0);
            int labelId = sa.getResourceId(1, 0);
            int iconId = sa.getResourceId(2, 0);
            int smallIconId = sa.getResourceId(3, 0);
            int prefId = sa.getResourceId(4, 0);
            if (TextUtils.isEmpty(accountType)) {
                return null;
            }
            AuthenticatorDescription authenticatorDescription = new AuthenticatorDescription(accountType, packageName, labelId, iconId, smallIconId, prefId);
            sa.recycle();
            return authenticatorDescription;
        } finally {
            sa.recycle();
        }
    }

    public ServiceInfo<AuthenticatorDescription> getServiceInfo(AuthenticatorDescription type) {
        ServiceInfo<AuthenticatorDescription> serviceInfo;
        synchronized (this.mServicesLock) {
            serviceInfo = (ServiceInfo) this.mServices.get(type);
        }
        return serviceInfo;
    }

    public Collection<ServiceInfo<AuthenticatorDescription>> getAllServices() {
        Collection<ServiceInfo<AuthenticatorDescription>> unmodifiableCollection;
        synchronized (this.mServicesLock) {
            unmodifiableCollection = Collections.unmodifiableCollection(this.mServices.values());
        }
        return unmodifiableCollection;
    }
}
