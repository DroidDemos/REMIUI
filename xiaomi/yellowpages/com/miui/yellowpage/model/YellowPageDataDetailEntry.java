package com.miui.yellowpage.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.AppRecommendation;
import com.miui.yellowpage.base.model.Banners;
import com.miui.yellowpage.base.model.Coupon;
import com.miui.yellowpage.base.model.Coupon.More;
import com.miui.yellowpage.base.model.Enrolling.Enroll;
import com.miui.yellowpage.base.model.Enrolling.Manage;
import com.miui.yellowpage.base.model.Gallery;
import com.miui.yellowpage.base.model.HmsMessageNotification;
import com.miui.yellowpage.base.model.Review.Detail;
import com.miui.yellowpage.base.model.Review.Summary;
import com.miui.yellowpage.base.model.ShopInformation;
import com.miui.yellowpage.base.model.ShopRecommendation;
import com.miui.yellowpage.base.model.Social;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.model.YellowPage.Provider;
import com.miui.yellowpage.base.model.YellowPageDataEntry;
import com.miui.yellowpage.base.model.YellowPageDownloadAppModuleEntry;
import com.miui.yellowpage.base.model.YellowPageExtraData;
import com.miui.yellowpage.base.model.YellowPageGroupHeaderEntry;
import com.miui.yellowpage.base.model.YellowPageModuleEntry;
import com.miui.yellowpage.base.model.YellowPageModuleEntry.MultiModuleStyle;
import com.miui.yellowpage.base.model.YellowPageModuleEntry.MultiModuleStyle.Style;
import com.miui.yellowpage.base.model.YellowPageServicesDataEntry;
import com.miui.yellowpage.base.model.YellowPageTitleDataEntry;
import com.miui.yellowpage.base.reference.RefMethods.IntentScope;
import com.miui.yellowpage.base.utils.BusinessStatistics.StatsContext;
import com.miui.yellowpage.base.utils.IntentUtil;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.utils.r;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import miui.telephony.PhoneNumberUtils.PhoneNumber;
import miui.yellowpage.YellowPagePhone;
import miui.yellowpage.YellowPageProvider;
import miui.yellowpage.YellowPageUtils;

public class YellowPageDataDetailEntry extends YellowPageDataEntry {
    private String mData;
    private boolean mHighlightData;
    private Intent mMainActionIntent;
    private Drawable mSecondaryActionIcon;
    private Intent mSecondaryActionIntent;
    private Type mType;
    private String mTypeString;

    enum Group {
        BANNER(R.string.group_name_banner),
        TITLE(R.string.group_name_title),
        BASIC(R.string.group_name_basic),
        SERVICE(R.string.group_name_service),
        SHOP_INFO(R.string.group_name_shop_info),
        GALLERY(R.string.group_name_gallery),
        MORE(R.string.group_name_more),
        SHOP_RECOMMENDATION(R.string.group_name_shop_recommendation),
        DATA_SOURCE(R.string.group_name_data_source),
        MANAGEMENT(R.string.group_name_management),
        ENROLL(R.string.group_name_enroll);
        
        private int mNameRes;

        private Group(int i) {
            this.mNameRes = i;
        }

        public String w(Context context) {
            return context.getResources().getString(this.mNameRes);
        }

        public static Group L(String str) {
            Group group = null;
            if (!TextUtils.isEmpty(str)) {
                try {
                    group = valueOf(str);
                } catch (IllegalArgumentException e) {
                    Log.d("YellowPageDataDetailEntry", e.getMessage());
                }
            }
            return group;
        }
    }

    public enum Type {
        ADDRESS("address"),
        PHONE("phone"),
        RINGTONE("ringtone"),
        NICKNAME("nickname"),
        WEBSITE("website"),
        BRIEF("brief"),
        PROVIDER("provider"),
        SOCIAL("social"),
        MODULE("module"),
        MI_PUB_CLICK("mipub_menu_click"),
        MI_PUB_VIEW("mipub_menu_view"),
        CORRECTION("correction"),
        ABOUT("about");
        
        private String mMimeTypeString;

        private Type(String str) {
            this.mMimeTypeString = str;
        }

        public String ar() {
            return this.mMimeTypeString;
        }
    }

    public YellowPageDataDetailEntry() {
        super(com.miui.yellowpage.base.model.YellowPageDataEntry.Type.DETAIL);
    }

    public YellowPageDataDetailEntry a(Drawable drawable) {
        this.mSecondaryActionIcon = drawable;
        return this;
    }

    public Drawable dr() {
        return this.mSecondaryActionIcon;
    }

    public YellowPageDataDetailEntry c(Intent intent) {
        this.mMainActionIntent = intent;
        return this;
    }

    public Intent ds() {
        return this.mMainActionIntent;
    }

    public YellowPageDataDetailEntry d(Intent intent) {
        this.mSecondaryActionIntent = intent;
        return this;
    }

    public Intent dt() {
        return this.mSecondaryActionIntent;
    }

    public YellowPageDataDetailEntry aG(String str) {
        this.mData = str;
        return this;
    }

    public String getData() {
        return this.mData;
    }

    public YellowPageDataDetailEntry a(Type type) {
        this.mType = type;
        return this;
    }

    public Type du() {
        return this.mType;
    }

    public YellowPageDataDetailEntry aH(String str) {
        this.mTypeString = str;
        return this;
    }

    public String getTypeString() {
        return this.mTypeString;
    }

    public boolean dv() {
        return this.mHighlightData;
    }

    private static YellowPagePhone a(Context context, YellowPage yellowPage, String str) {
        if (TextUtils.isEmpty(str) || yellowPage == null || yellowPage.getPhones() == null) {
            return null;
        }
        List<YellowPagePhone> phones = yellowPage.getPhones();
        CharSequence normalizedNumber = YellowPageUtils.getNormalizedNumber(context, str);
        for (YellowPagePhone yellowPagePhone : phones) {
            CharSequence normalizedNumber2 = yellowPagePhone.getNormalizedNumber();
            if (normalizedNumber.contains(",") || normalizedNumber2.contains(",")) {
                if (TextUtils.equals(normalizedNumber, normalizedNumber2)) {
                    return yellowPagePhone;
                }
            } else if (PhoneNumberUtils.compare(normalizedNumber, normalizedNumber2)) {
                return yellowPagePhone;
            }
        }
        return null;
    }

    public static ArrayList a(Context context, YellowPage yellowPage, String str, ArrayList arrayList, StatsContext statsContext) {
        int i = 0;
        ArrayList arrayList2 = new ArrayList();
        if (yellowPage == null) {
            return arrayList2;
        }
        Group L;
        Gallery gallery = null;
        Style style = Style.NORMAL;
        EnumMap enumMap = new EnumMap(Group.class);
        for (Enum put : Group.values()) {
            enumMap.put(put, new ArrayList());
        }
        YellowPageDataEntry b = b(context, yellowPage, str);
        if (b != null) {
            ((ArrayList) enumMap.get(Group.BASIC)).add(b);
        }
        b = d(context, yellowPage);
        if (b != null) {
            ((ArrayList) enumMap.get(Group.BASIC)).add(b);
        }
        ((ArrayList) enumMap.get(Group.MORE)).addAll(b(context, yellowPage));
        b = e(context, yellowPage);
        if (b != null) {
            ((ArrayList) enumMap.get(Group.MORE)).add(b);
        }
        Collection c = c(context, yellowPage);
        if (c != null) {
            ((ArrayList) enumMap.get(Group.DATA_SOURCE)).addAll(c);
        }
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Style style2;
                Gallery gallery2;
                YellowPageDataEntry yellowPageDataEntry = (YellowPageDataEntry) it.next();
                if (yellowPageDataEntry instanceof YellowPageModuleEntry) {
                    L = Group.L(((YellowPageModuleEntry) yellowPageDataEntry).getGroup());
                    if (L != null) {
                        ((ArrayList) enumMap.get(L)).add(yellowPageDataEntry);
                    }
                }
                if (yellowPageDataEntry instanceof YellowPageDownloadAppModuleEntry) {
                    ((ArrayList) enumMap.get(Group.MORE)).add(yellowPageDataEntry);
                    style2 = style;
                    gallery2 = gallery;
                } else if (yellowPageDataEntry instanceof Detail) {
                    ((ArrayList) enumMap.get(Group.SHOP_INFO)).add(yellowPageDataEntry);
                    style2 = style;
                    gallery2 = gallery;
                } else if (yellowPageDataEntry instanceof Summary) {
                    ((ArrayList) enumMap.get(Group.SHOP_INFO)).add(yellowPageDataEntry);
                    style2 = style;
                    gallery2 = gallery;
                } else if (yellowPageDataEntry instanceof Coupon.Detail) {
                    ((ArrayList) enumMap.get(Group.SHOP_INFO)).add(yellowPageDataEntry);
                    style2 = style;
                    gallery2 = gallery;
                } else if (yellowPageDataEntry instanceof More) {
                    ((ArrayList) enumMap.get(Group.SHOP_INFO)).add(yellowPageDataEntry);
                    style2 = style;
                    gallery2 = gallery;
                } else if (yellowPageDataEntry instanceof Banners) {
                    ((ArrayList) enumMap.get(Group.BANNER)).add(yellowPageDataEntry);
                    style2 = style;
                    gallery2 = gallery;
                } else if (yellowPageDataEntry instanceof ShopInformation) {
                    ((ArrayList) enumMap.get(Group.SHOP_INFO)).add(yellowPageDataEntry);
                    style2 = style;
                    gallery2 = gallery;
                } else if (yellowPageDataEntry instanceof MultiModuleStyle) {
                    style2 = ((MultiModuleStyle) yellowPageDataEntry).getStyle();
                    gallery2 = gallery;
                } else if (yellowPageDataEntry instanceof AppRecommendation) {
                    ((ArrayList) enumMap.get(Group.MORE)).add(yellowPageDataEntry);
                    style2 = style;
                    gallery2 = gallery;
                } else if (yellowPageDataEntry instanceof ShopRecommendation) {
                    ((ArrayList) enumMap.get(Group.SHOP_RECOMMENDATION)).add(yellowPageDataEntry);
                    style2 = style;
                    gallery2 = gallery;
                } else if (yellowPageDataEntry instanceof HmsMessageNotification) {
                    HmsMessageNotification hmsMessageNotification = (HmsMessageNotification) yellowPageDataEntry;
                    if (TextUtils.isEmpty(hmsMessageNotification.getSnippet())) {
                        hmsMessageNotification.setSnippet(context.getString(R.string.hms_no_subject_view));
                    }
                    ((ArrayList) enumMap.get(Group.BASIC)).add(yellowPageDataEntry);
                    style2 = style;
                    gallery2 = gallery;
                } else if (yellowPageDataEntry instanceof Gallery) {
                    gallery2 = (Gallery) yellowPageDataEntry;
                    style2 = style;
                } else if (yellowPageDataEntry instanceof Manage) {
                    ((ArrayList) enumMap.get(Group.MANAGEMENT)).add(yellowPageDataEntry);
                    style2 = style;
                    gallery2 = gallery;
                } else if (yellowPageDataEntry instanceof Enroll) {
                    ((ArrayList) enumMap.get(Group.ENROLL)).add(yellowPageDataEntry);
                    style2 = style;
                    gallery2 = gallery;
                } else {
                    ((ArrayList) enumMap.get(Group.SERVICE)).add(yellowPageDataEntry);
                    style2 = style;
                    gallery2 = gallery;
                }
                style = style2;
                gallery = gallery2;
            }
        }
        b = a(yellowPage, gallery, statsContext);
        if (b != null) {
            ((ArrayList) enumMap.get(Group.TITLE)).add(b);
        }
        if (style == Style.GRID && ((ArrayList) enumMap.get(Group.SERVICE)).size() > 0) {
            YellowPageServicesDataEntry yellowPageId = new YellowPageServicesDataEntry().setServices((ArrayList) enumMap.get(Group.SERVICE)).setYellowPageId(yellowPage.getId());
            ((ArrayList) enumMap.get(Group.SERVICE)).clear();
            ((ArrayList) enumMap.get(Group.SERVICE)).add(yellowPageId);
        }
        while (i < Group.values().length) {
            L = Group.values()[i];
            ArrayList arrayList3 = (ArrayList) enumMap.get(Group.values()[i]);
            if (arrayList3.size() > 0) {
                Object w = L.w(context);
                if (!(TextUtils.isEmpty(w) || (style == Style.GRID && L == Group.SERVICE))) {
                    arrayList2.add(new YellowPageGroupHeaderEntry(w));
                }
                arrayList2.addAll(arrayList3);
            }
            i++;
        }
        return arrayList2;
    }

    private static YellowPageDataEntry a(YellowPage yellowPage, Gallery gallery, StatsContext statsContext) {
        YellowPageDataEntry yellowPageTitleDataEntry = new YellowPageTitleDataEntry();
        yellowPageTitleDataEntry.setAlbumPhotoUrl(yellowPage.getThumbnailName());
        yellowPageTitleDataEntry.setTitle(yellowPage.getName());
        yellowPageTitleDataEntry.setBrief(yellowPage.getBrief());
        yellowPageTitleDataEntry.setStatisticsContext(statsContext);
        YellowPageExtraData fromJson = YellowPageExtraData.fromJson(yellowPage.getExtraData());
        if (fromJson == null) {
            return yellowPageTitleDataEntry;
        }
        if (fromJson.isTitleHidden()) {
            return null;
        }
        yellowPageTitleDataEntry.setExtraData(fromJson);
        yellowPageTitleDataEntry.setStyle(fromJson.getTitleStyle());
        if (gallery == null) {
            return yellowPageTitleDataEntry;
        }
        yellowPageTitleDataEntry.setAlbumIntent(gallery.getIntent());
        yellowPageTitleDataEntry.setPhotoCount(gallery.getCount());
        if (TextUtils.isEmpty(gallery.getPhotoUrl())) {
            return yellowPageTitleDataEntry;
        }
        yellowPageTitleDataEntry.setAlbumPhotoUrl(gallery.getPhotoUrl());
        return yellowPageTitleDataEntry;
    }

    private static YellowPageDataEntry b(Context context, YellowPage yellowPage, String str) {
        List phones = yellowPage.getPhones();
        YellowPagePhone a = a(context, yellowPage, str);
        if (a == null) {
            if (phones == null || phones.size() <= 0) {
                a = null;
            } else {
                a = (YellowPagePhone) phones.get(0);
            }
        }
        if (a != null) {
            return b(context, a);
        }
        return null;
    }

    private static YellowPageDataEntry b(Context context, YellowPagePhone yellowPagePhone) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("yellowpage://quick_yellow_page?id=" + yellowPagePhone.getYellowPageId()));
        Intent processIntentScope = IntentScope.processIntentScope(context, new Intent("android.intent.action.CALL_PRIVILEGED", Uri.fromParts("tel", yellowPagePhone.getNumber(), null)), IntentScope.PACKAGE_NAME_PHONE);
        Object location = PhoneNumber.getLocation(context, yellowPagePhone.getNumber());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(yellowPagePhone.getTag());
        if (!TextUtils.isEmpty(location)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(context.getString(R.string.phone_info_divider));
            }
            stringBuilder.append(location);
        }
        return new YellowPageDataDetailEntry().aH(stringBuilder.toString()).a(context.getResources().getDrawable(R.drawable.more_phone_numbers)).c(processIntentScope).d(intent).aG(yellowPagePhone.getNumber()).a(Type.PHONE);
    }

    private static List b(Context context, YellowPage yellowPage) {
        List arrayList = new ArrayList();
        List<Social> socials = yellowPage.getSocials();
        if (socials != null && socials.size() > 0) {
            for (Social social : socials) {
                if (!TextUtils.isEmpty(social.getUrl())) {
                    Intent intent = null;
                    if (social.getProviderId() == 5) {
                        intent = p(context, social.getName());
                    }
                    if (intent == null) {
                        intent = new Intent("android.intent.action.VIEW");
                        intent.setData(aI(social.getUrl()));
                    }
                    arrayList.add(new YellowPageDataDetailEntry().a(new BitmapDrawable(context.getResources(), YellowPageUtils.getProvider(context, social.getProviderId()).getBigIcon())).c(intent).aG(social.getName()).a(Type.SOCIAL));
                }
            }
        }
        return arrayList;
    }

    private static List c(Context context, YellowPage yellowPage) {
        List arrayList = new ArrayList();
        List<Provider> providerList = yellowPage.getProviderList();
        if (providerList != null) {
            for (Provider a : providerList) {
                YellowPageDataEntry a2 = a(context, yellowPage, a);
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
        }
        return arrayList;
    }

    private static YellowPageDataEntry a(Context context, YellowPage yellowPage, Provider provider) {
        YellowPageProvider provider2 = YellowPageUtils.getProvider(context, provider.getId());
        if (provider2.isMiui() || TextUtils.isEmpty(provider.getSourceUrl())) {
            return null;
        }
        Intent f = f(context, yellowPage);
        if (f == null) {
            f = new Intent("android.intent.action.VIEW");
            f.setData(aI(provider.getSourceUrl()));
        }
        return new YellowPageDataDetailEntry().a(new BitmapDrawable(context.getResources(), provider2.getBigIcon())).c(f).aG(provider2.getName()).a(Type.PROVIDER);
    }

    private static YellowPageDataEntry d(Context context, YellowPage yellowPage) {
        if (TextUtils.isEmpty(yellowPage.getAddress())) {
            return null;
        }
        Intent g = g(context, yellowPage);
        if (!IntentUtil.canResolveIntent(context, g)) {
            g = new Intent("android.intent.action.VIEW");
            g.setData(Uri.parse("http://mo.amap.com/?q=" + yellowPage.getLatitude() + "," + yellowPage.getLongitude() + "&name=" + yellowPage.getName() + "&dev=0"));
        }
        return new YellowPageDataDetailEntry().a(context.getResources().getDrawable(R.drawable.ic_arrow_right)).c(g).aG(yellowPage.getAddress()).a(Type.ADDRESS);
    }

    private static YellowPageDataEntry e(Context context, YellowPage yellowPage) {
        if (TextUtils.isEmpty(yellowPage.getUrl())) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(aI(yellowPage.getUrl()));
        return new YellowPageDataDetailEntry().c(intent).a(context.getResources().getDrawable(R.drawable.ic_arrow_right)).aG(context.getString(R.string.yellow_page_website)).a(Type.WEBSITE);
    }

    private static Uri aI(String str) {
        if (!(str.startsWith("http://") || str.startsWith("https://"))) {
            str = "http://" + str;
        }
        return Uri.parse(str);
    }

    private static Intent f(Context context, YellowPage yellowPage) {
        if (yellowPage.getProviderId() == 4) {
            return o(context, yellowPage.getSourceId());
        }
        return null;
    }

    private static Intent o(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("dianping://shopinfo?id=" + str + "&utm_=w_open_xiaomi"));
        if (IntentUtil.canResolveIntent(context, intent)) {
            return intent;
        }
        return null;
    }

    private static Intent g(Context context, YellowPage yellowPage) {
        UnsupportedEncodingException unsupportedEncodingException;
        UnsupportedEncodingException unsupportedEncodingException2;
        Intent intent = null;
        if (yellowPage == null) {
            return null;
        }
        try {
            Intent intent2;
            if (!(TextUtils.isEmpty(yellowPage.getLongitude()) || TextUtils.isEmpty(yellowPage.getLatitude()))) {
                if (r.q(context, "com.autonavi.minimap")) {
                    intent2 = new Intent("android.intent.action.VIEW");
                    try {
                        intent2.setData(Uri.parse("androidamap://viewMap?sourceApplication=com.miui.yellowpage&poiname=" + URLEncoder.encode(yellowPage.getName(), "UTF-8") + "&lat=" + yellowPage.getLatitude() + "&lon=" + yellowPage.getLongitude() + "&dev=0"));
                        intent2.setPackage("com.autonavi.minimap");
                        if (intent2 == null) {
                            return intent2;
                        }
                        try {
                            intent = new Intent("android.intent.action.VIEW");
                            intent.setData(Uri.parse("geo:0,0?q=" + URLEncoder.encode(yellowPage.getAddress() + " " + yellowPage.getName(), "UTF-8")));
                            return intent;
                        } catch (UnsupportedEncodingException e) {
                            unsupportedEncodingException = e;
                            intent = intent2;
                            unsupportedEncodingException2 = unsupportedEncodingException;
                            unsupportedEncodingException2.printStackTrace();
                            return intent;
                        }
                    } catch (UnsupportedEncodingException e2) {
                        unsupportedEncodingException = e2;
                        intent = intent2;
                        unsupportedEncodingException2 = unsupportedEncodingException;
                        unsupportedEncodingException2.printStackTrace();
                        return intent;
                    }
                } else if (r.q(context, "com.baidu.BaiduMap")) {
                    intent2 = new Intent("android.intent.action.VIEW");
                    try {
                        intent2.setData(Uri.parse("bdapp://map/marker?location=" + yellowPage.getLatitude() + "," + yellowPage.getLongitude() + "&title=" + URLEncoder.encode(yellowPage.getName(), "UTF-8") + "&content=" + URLEncoder.encode(yellowPage.getAddress(), "UTF-8") + "&src=com.miui.yellowpage" + "&coord_type=gcj02"));
                        intent2.setPackage("com.baidu.BaiduMap");
                        if (intent2 == null) {
                            return intent2;
                        }
                        intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse("geo:0,0?q=" + URLEncoder.encode(yellowPage.getAddress() + " " + yellowPage.getName(), "UTF-8")));
                        return intent;
                    } catch (UnsupportedEncodingException e22) {
                        unsupportedEncodingException = e22;
                        intent = intent2;
                        unsupportedEncodingException2 = unsupportedEncodingException;
                        unsupportedEncodingException2.printStackTrace();
                        return intent;
                    }
                }
            }
            intent2 = null;
            if (intent2 == null) {
                return intent2;
            }
            intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("geo:0,0?q=" + URLEncoder.encode(yellowPage.getAddress() + " " + yellowPage.getName(), "UTF-8")));
            return intent;
        } catch (UnsupportedEncodingException e3) {
            unsupportedEncodingException2 = e3;
            unsupportedEncodingException2.printStackTrace();
            return intent;
        }
    }

    private static Intent p(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Intent intent;
        try {
            intent = new Intent("android.intent.action.VIEW", Uri.parse("sinaweibo://userinfo?nick=" + URLEncoder.encode(str, "UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            intent = null;
        }
        if (IntentUtil.canResolveIntent(context, intent)) {
            return intent;
        }
        return null;
    }
}
