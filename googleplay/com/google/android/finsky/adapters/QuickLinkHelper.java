package com.google.android.finsky.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.layout.play.PlayQuickLinkBase;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Browse.QuickLink;
import com.google.android.finsky.utils.IntMath;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.image.BitmapLoader;
import java.util.List;

public class QuickLinkHelper {

    public static class QuickLinkInfo {
        private final int mBackendId;
        private final QuickLink mQuickLink;

        public QuickLinkInfo(QuickLink quickLink, int backendId) {
            this.mQuickLink = quickLink;
            this.mBackendId = backendId;
        }
    }

    public static void getQuickLinksForStream(Context context, List<QuickLinkInfo> quickLinks, List<QuickLinkInfo> primaryStreamOutput, List<QuickLinkInfo> fallbackStreamOutput) {
        int numRequiredQuickLinks = 0;
        for (QuickLinkInfo link : quickLinks) {
            if (link.mQuickLink.displayRequired) {
                primaryStreamOutput.add(link);
                numRequiredQuickLinks++;
            }
        }
        int numQuickLinksPerRow = UiUtils.getStreamQuickLinkColumnCount(context.getResources(), numRequiredQuickLinks, quickLinks.size() - numRequiredQuickLinks);
        int remainder = (((int) Math.ceil(((double) numRequiredQuickLinks) / ((double) numQuickLinksPerRow))) * numQuickLinksPerRow) - numRequiredQuickLinks;
        for (QuickLinkInfo link2 : quickLinks) {
            if (!link2.mQuickLink.displayRequired) {
                if (remainder > 0) {
                    primaryStreamOutput.add(link2);
                    remainder--;
                } else {
                    fallbackStreamOutput.add(link2);
                }
            }
        }
    }

    public static View inflateQuickLinksRow(ViewGroup parent, LayoutInflater inflater, int numQuickLinksPerRow) {
        ViewGroup result = (ViewGroup) inflater.inflate(R.layout.quick_links_row, parent, false);
        for (int columnNum = 0; columnNum < numQuickLinksPerRow; columnNum++) {
            result.addView(inflater.inflate(R.layout.quick_link, result, false));
        }
        return result;
    }

    public static void bindQuickLinksRow(DfeToc dfeToc, NavigationManager navMgr, BitmapLoader bitmapLoader, ViewGroup quickLinksRow, QuickLinkInfo[] quickLinks, int quickLinkRowNum, int numQuickLinksPerRow, PlayStoreUiElementNode parentNode) {
        boolean isLastRow = quickLinkRowNum == IntMath.ceil(quickLinks.length, numQuickLinksPerRow) + -1;
        int numQuickLinks = quickLinks.length;
        int offset = quickLinkRowNum * numQuickLinksPerRow;
        int blockPaddingBottom = quickLinksRow.getResources().getDimensionPixelSize(R.dimen.quick_link_block_padding_bottom);
        for (int columnNum = 0; columnNum < quickLinksRow.getChildCount(); columnNum++) {
            PlayQuickLinkBase cell = (PlayQuickLinkBase) quickLinksRow.getChildAt(columnNum);
            int index = offset + columnNum;
            if (index >= numQuickLinks) {
                cell.setVisibility(4);
            } else {
                cell.setVisibility(0);
                QuickLinkInfo quickLinkInfo = quickLinks[index];
                cell.bind(quickLinkInfo.mQuickLink, quickLinkInfo.mBackendId, navMgr, dfeToc, bitmapLoader, parentNode);
            }
        }
        quickLinksRow.setPadding(0, 0, 0, isLastRow ? blockPaddingBottom : 0);
    }
}
