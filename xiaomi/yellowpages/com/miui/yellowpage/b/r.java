package com.miui.yellowpage.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alipay.sdk.protocol.WindowData;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.AppRecommendation;
import com.miui.yellowpage.base.model.Banners;
import com.miui.yellowpage.base.model.Coupon;
import com.miui.yellowpage.base.model.Coupon.More;
import com.miui.yellowpage.base.model.Enrolling;
import com.miui.yellowpage.base.model.HmsMessageNotification;
import com.miui.yellowpage.base.model.Review.Detail;
import com.miui.yellowpage.base.model.Review.Summary;
import com.miui.yellowpage.base.model.ShopInformation;
import com.miui.yellowpage.base.model.ShopRecommendation;
import com.miui.yellowpage.base.model.YellowPageDataEntry;
import com.miui.yellowpage.base.model.YellowPageDataEntry.Type;
import com.miui.yellowpage.base.model.YellowPageGroupHeaderEntry;
import com.miui.yellowpage.base.model.YellowPageModuleEntry;
import com.miui.yellowpage.base.model.YellowPageServicesDataEntry;
import com.miui.yellowpage.base.model.YellowPageTitleDataEntry;
import com.miui.yellowpage.model.YellowPageDataDetailEntry;
import com.miui.yellowpage.ui.AppRecommendationListItem;
import com.miui.yellowpage.ui.BannerView;
import com.miui.yellowpage.ui.CouponListItem;
import com.miui.yellowpage.ui.EnrollingListItem;
import com.miui.yellowpage.ui.HmsMessageNotificationListItem;
import com.miui.yellowpage.ui.MoreCouponsListItem;
import com.miui.yellowpage.ui.MoreReviewListItem;
import com.miui.yellowpage.ui.ReviewDetailListItem;
import com.miui.yellowpage.ui.ShopInformationListItem;
import com.miui.yellowpage.ui.ShopRecommendationListItem;
import com.miui.yellowpage.ui.YellowPageGroupHeaderItem;
import com.miui.yellowpage.ui.YellowPageListItem;
import com.miui.yellowpage.ui.YellowPageListModuleItem;
import com.miui.yellowpage.ui.YellowPageServiceContainerItem;
import com.miui.yellowpage.ui.YellowPageTitleListItem;
import com.miui.yellowpage.ui.cs;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;

/* compiled from: YellowPageAdapter */
public class r extends a {
    private static EnumSet Fb;
    private static EnumMap Fc;
    private cs Fd;

    public /* bridge */ /* synthetic */ void bindView(View view, int i, Object obj) {
        a(view, i, (YellowPageDataEntry) obj);
    }

    public /* bridge */ /* synthetic */ View newView(Context context, Object obj, ViewGroup viewGroup) {
        return a(context, (YellowPageDataEntry) obj, viewGroup);
    }

    static {
        Fc = new EnumMap(Type.class);
        Fb = EnumSet.noneOf(Type.class);
        Fb.add(Type.GROUP_HEADER);
        Fb.add(Type.TITLE);
        Fc.put(Type.BANNERS, Integer.valueOf(R.layout.banner_view));
        Fc.put(Type.DETAIL, Integer.valueOf(R.layout.yellow_page_list_item));
        Fc.put(Type.MULTI_MODULE, Integer.valueOf(R.layout.yellow_page_list_module_item));
        Fc.put(Type.GROUP_HEADER, Integer.valueOf(R.layout.yellow_page_group_header_item));
        Fc.put(Type.REVIEW_DETAIL, Integer.valueOf(R.layout.review_detail_list_item));
        Fc.put(Type.REVIEW_SUMMARY, Integer.valueOf(R.layout.more_review_list_item));
        Fc.put(Type.COUPON_DETAIL, Integer.valueOf(R.layout.coupon_detail_list_item));
        Fc.put(Type.COUPON_MORE, Integer.valueOf(R.layout.more_coupons_list_item));
        Fc.put(Type.GALLERY, Integer.valueOf(R.layout.gallery_list_item));
        Fc.put(Type.SHOP_INFORMATION, Integer.valueOf(R.layout.shop_information_list_item));
        Fc.put(Type.SERVICES, Integer.valueOf(R.layout.yellow_page_service_container_item));
        Fc.put(Type.APP_RECOMMENDATION, Integer.valueOf(R.layout.app_recommendation_list_item));
        Fc.put(Type.SHOP_RECOMMENDATION, Integer.valueOf(R.layout.shop_recommendation_list_item));
        Fc.put(Type.TITLE, Integer.valueOf(R.layout.yellow_page_title_list_item));
        Fc.put(Type.ENROLLING, Integer.valueOf(R.layout.enrolling_list_item));
        Fc.put(Type.HMS_MESSAGE_NOTIFICATION, Integer.valueOf(R.layout.hms_message_notification_list_item));
    }

    public r(Context context, cs csVar) {
        super(context);
        this.Fd = csVar;
    }

    public boolean isEnabled(int i) {
        boolean z = true;
        YellowPageDataEntry yellowPageDataEntry = (YellowPageDataEntry) getItem(i);
        if (yellowPageDataEntry.getItemType() != Type.SHOP_INFORMATION) {
            if (Fb.contains(yellowPageDataEntry.getItemType())) {
                z = false;
            }
            return z;
        } else if (((ShopInformation) yellowPageDataEntry).getIntent() != null) {
            return true;
        } else {
            return false;
        }
    }

    public void updateData(ArrayList arrayList) {
        super.updateData(arrayList);
    }

    public View a(Context context, YellowPageDataEntry yellowPageDataEntry, ViewGroup viewGroup) {
        Integer num = (Integer) Fc.get(yellowPageDataEntry.getItemType());
        if (num == null || num.intValue() == 0) {
            return null;
        }
        return LayoutInflater.from(context).inflate(num.intValue(), viewGroup, false);
    }

    public void a(View view, int i, YellowPageDataEntry yellowPageDataEntry) {
        switch (d.mA[Type.values()[getItemViewType(i)].ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                ((BannerView) view).a((Banners) yellowPageDataEntry, this.Fd);
                return;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                ((YellowPageListItem) view).a((YellowPageDataDetailEntry) yellowPageDataEntry);
                return;
            case WindowData.d /*3*/:
                ((YellowPageListModuleItem) view).a((YellowPageModuleEntry) yellowPageDataEntry);
                return;
            case Base64.CRLF /*4*/:
                ((YellowPageGroupHeaderItem) view).a((YellowPageGroupHeaderEntry) yellowPageDataEntry);
                return;
            case WindowData.f /*5*/:
                ((ReviewDetailListItem) view).a((Detail) yellowPageDataEntry);
                return;
            case WindowData.g /*6*/:
                ((MoreReviewListItem) view).a((Summary) yellowPageDataEntry);
                return;
            case WindowData.h /*7*/:
                ((CouponListItem) view).a(this.mContext, (Coupon.Detail) yellowPageDataEntry);
                return;
            case Base64.URL_SAFE /*8*/:
                ((MoreCouponsListItem) view).a((More) yellowPageDataEntry);
                return;
            case WindowData.k /*10*/:
                ((ShopInformationListItem) view).a((ShopInformation) yellowPageDataEntry);
                return;
            case 11:
                ((YellowPageServiceContainerItem) view).a((YellowPageServicesDataEntry) yellowPageDataEntry);
                return;
            case 12:
                ((AppRecommendationListItem) view).a((AppRecommendation) yellowPageDataEntry);
                return;
            case 13:
                ((ShopRecommendationListItem) view).a((ShopRecommendation) yellowPageDataEntry);
                return;
            case 14:
                ((YellowPageTitleListItem) view).a((YellowPageTitleDataEntry) yellowPageDataEntry);
                return;
            case 15:
                ((HmsMessageNotificationListItem) view).a((HmsMessageNotification) yellowPageDataEntry);
                return;
            case Base64.NO_CLOSE /*16*/:
                ((EnrollingListItem) view).a((Enrolling) yellowPageDataEntry);
                return;
            default:
                return;
        }
    }

    public int getItemViewType(int i) {
        return ((YellowPageDataEntry) getItem(i)).getItemType().ordinal();
    }

    public int getViewTypeCount() {
        return Type.values().length;
    }
}
