package com.google.android.finsky.detailspage;

import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ModulesManager {
    private static List<Class> ANTENNA_MODULES;
    private static final List<Class> DETAILS_MODULES;
    private static final List<Class> DETAILS_MODULES_WIDE_LAYOUT;

    static {
        DETAILS_MODULES = Arrays.asList(new Class[]{HeaderListSpacerModule.class, TitleModule.class, WarningMessageModule.class, PostPurchaseClusterModule.class, SubscriptionsModule.class, DiscoveryBarModule.class, DescriptionTextModule.class, ScreenshotsModule.class, AboutAuthorTextModule.class, RateReviewModule.class, GooglePlusShareModule.class, SongListModule.class, EpisodeListModule.class, ReviewsSamplesModule.class, SecondaryActionsModule.class, BodyOfWorkCardClusterModule.class, MoreByCardClusterModule.class, RelatedCardClusterModule.class, CrossSellCardClusterModule.class, BylinesModule.class, FlagContentModule.class, FooterTextModule.class, FooterSpacerModule.class});
        DETAILS_MODULES_WIDE_LAYOUT = Arrays.asList(new Class[]{HeaderListSpacerModule.class, CombinedTitleModule.class, PostPurchaseClusterModule.class, SubscriptionsModule.class, DescriptionTextModule.class, ScreenshotsModule.class, AboutAuthorTextModule.class, RateReviewModule.class, GooglePlusShareModule.class, SongListModule.class, EpisodeListModule.class, ReviewsSamplesModule.class, SecondaryActionsModule.class, BodyOfWorkCardClusterModule.class, MoreByCardClusterModule.class, RelatedCardClusterModule.class, CrossSellCardClusterModule.class, BylinesModule.class, FlagContentModule.class, FooterTextModule.class, FooterSpacerModule.class});
        ANTENNA_MODULES = Arrays.asList(new Class[]{HeaderListSpacerModule.class, AntennaDescriptionTextModule.class, AntennaSongListModule.class, SecondaryActionsModule.class, AntennaAlbumsCardClusterModule.class});
    }

    public static List<FinskyModule> generateModules(Document doc, boolean useWideLayout) {
        if (isAntennaPage(doc)) {
            return generateModules(ANTENNA_MODULES);
        }
        if (useWideLayout) {
            return generateModules(DETAILS_MODULES_WIDE_LAYOUT);
        }
        return generateModules(DETAILS_MODULES);
    }

    private static boolean isAntennaPage(Document doc) {
        return doc.getBackend() == 2 && doc.hasAntennaInfo();
    }

    private static List<FinskyModule> generateModules(List<Class> moduleClasses) {
        List<FinskyModule> modules = Lists.newArrayList();
        for (int i = 0; i < moduleClasses.size(); i++) {
            try {
                modules.add((FinskyModule) ((Class) moduleClasses.get(i)).newInstance());
            } catch (Exception e) {
                FinskyLog.wtf("Exception trying to instantiate module: " + e.getMessage(), new Object[0]);
            }
        }
        return modules;
    }

    public static void remapModuleDataAfterRotation(List<ModuleData> moduleDataList, Document doc, boolean prevUseWideLayout, boolean currUseWideLayout) {
        if (!isAntennaPage(doc)) {
            CombinedTitleModuleData combinedTitleModuleData;
            List<Integer> indices;
            int i;
            if (!prevUseWideLayout && currUseWideLayout) {
                int titleModuleIndex = DETAILS_MODULES.indexOf(TitleModule.class);
                int warningMessageModuleIndex = DETAILS_MODULES.indexOf(WarningMessageModule.class);
                int discoveryBarModuleIndex = DETAILS_MODULES.indexOf(DiscoveryBarModule.class);
                combinedTitleModuleData = new CombinedTitleModuleData();
                combinedTitleModuleData.titleModuleData = (TitleModuleData) moduleDataList.get(titleModuleIndex);
                combinedTitleModuleData.warningMessageModuleData = (WarningMessageModuleData) moduleDataList.get(warningMessageModuleIndex);
                combinedTitleModuleData.discoveryBarModuleData = (DiscoveryBarModuleData) moduleDataList.get(discoveryBarModuleIndex);
                indices = Lists.newArrayList(Integer.valueOf(titleModuleIndex), Integer.valueOf(warningMessageModuleIndex), Integer.valueOf(discoveryBarModuleIndex));
                Collections.sort(indices);
                Collections.reverse(indices);
                for (i = 0; i < indices.size(); i++) {
                    moduleDataList.remove(((Integer) indices.get(i)).intValue());
                }
                moduleDataList.add(DETAILS_MODULES_WIDE_LAYOUT.indexOf(CombinedTitleModule.class), combinedTitleModuleData);
            }
            if (prevUseWideLayout && !currUseWideLayout) {
                int combinedTitleModuleIndex = DETAILS_MODULES_WIDE_LAYOUT.indexOf(CombinedTitleModule.class);
                combinedTitleModuleData = (CombinedTitleModuleData) moduleDataList.get(combinedTitleModuleIndex);
                if (combinedTitleModuleData == null) {
                    combinedTitleModuleData = new CombinedTitleModuleData();
                }
                moduleDataList.remove(combinedTitleModuleIndex);
                Map<Integer, ModuleData> indexToModuleData = Maps.newHashMap();
                indexToModuleData.put(Integer.valueOf(DETAILS_MODULES.indexOf(TitleModule.class)), combinedTitleModuleData.titleModuleData);
                indexToModuleData.put(Integer.valueOf(DETAILS_MODULES.indexOf(WarningMessageModule.class)), combinedTitleModuleData.warningMessageModuleData);
                indexToModuleData.put(Integer.valueOf(DETAILS_MODULES.indexOf(DiscoveryBarModule.class)), combinedTitleModuleData.discoveryBarModuleData);
                indices = Lists.newArrayList(indexToModuleData.keySet());
                Collections.sort(indices);
                for (i = 0; i < indices.size(); i++) {
                    int index = ((Integer) indices.get(i)).intValue();
                    moduleDataList.add(index, indexToModuleData.get(Integer.valueOf(index)));
                }
            }
        }
    }

    public static int remapModuleIndexAfterRotation(int prevModuleIndex, Document doc, boolean prevUseWideLayout, boolean currUseWideLayout) {
        if (isAntennaPage(doc)) {
            return prevModuleIndex;
        }
        if (!prevUseWideLayout && currUseWideLayout) {
            int newScrollIndex = prevModuleIndex;
            if (prevModuleIndex > DETAILS_MODULES.indexOf(TitleModule.class)) {
                newScrollIndex--;
            }
            if (prevModuleIndex > DETAILS_MODULES.indexOf(WarningMessageModule.class)) {
                newScrollIndex--;
            }
            if (prevModuleIndex > DETAILS_MODULES.indexOf(DiscoveryBarModule.class)) {
                newScrollIndex--;
            }
            if (newScrollIndex > DETAILS_MODULES_WIDE_LAYOUT.indexOf(CombinedTitleModule.class)) {
                newScrollIndex++;
            }
            return newScrollIndex;
        } else if (!prevUseWideLayout || currUseWideLayout) {
            return prevModuleIndex;
        } else {
            int newModuleIndex = prevModuleIndex;
            if (prevModuleIndex > DETAILS_MODULES_WIDE_LAYOUT.indexOf(CombinedTitleModule.class)) {
                newModuleIndex--;
            }
            List<Integer> indicesOfInsertions = Lists.newArrayList(Integer.valueOf(DETAILS_MODULES.indexOf(TitleModule.class)), Integer.valueOf(DETAILS_MODULES.indexOf(WarningMessageModule.class)), Integer.valueOf(DETAILS_MODULES.indexOf(DiscoveryBarModule.class)));
            Collections.sort(indicesOfInsertions);
            for (int i = 0; i < indicesOfInsertions.size(); i++) {
                if (newModuleIndex > ((Integer) indicesOfInsertions.get(i)).intValue()) {
                    newModuleIndex++;
                }
            }
            return newModuleIndex;
        }
    }
}
