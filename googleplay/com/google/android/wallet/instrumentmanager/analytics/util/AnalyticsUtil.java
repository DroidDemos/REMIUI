package com.google.android.wallet.instrumentmanager.analytics.util;

import com.google.android.wallet.instrumentmanager.analytics.UiNode;
import com.google.android.wallet.instrumentmanager.pub.analytics.CreditCardEntryAction;
import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerAnalyticsEventDispatcher;
import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerUiElement;
import com.google.android.wallet.instrumentmanager.pub.analytics.events.InstrumentManagerBackgroundEvent;
import com.google.android.wallet.instrumentmanager.pub.analytics.events.InstrumentManagerClickEvent;
import com.google.android.wallet.instrumentmanager.pub.analytics.events.InstrumentManagerImpressionEvent;
import java.util.ArrayList;
import java.util.List;

public final class AnalyticsUtil {
    public static void createAndSendRequestSentBackgroundEvent(int backgroundEventType, byte[] integratorLogToken) {
        InstrumentManagerAnalyticsEventDispatcher.sendBackgroundEvent(new InstrumentManagerBackgroundEvent(backgroundEventType, 0, null, -1, -1, -1, integratorLogToken));
    }

    public static void createAndSendResponseReceivedBackgroundEvent(int backgroundEventType, int resultCode, byte[] integratorLogToken) {
        createAndSendResponseReceivedBackgroundEvent(backgroundEventType, resultCode, null, -1, -1, integratorLogToken);
    }

    public static void createAndSendResponseReceivedBackgroundEvent(int backgroundEventType, int resultCode, String exceptionType, long clientLatencyMs, long serverLatencyMs, byte[] integratorLogToken) {
        InstrumentManagerAnalyticsEventDispatcher.sendBackgroundEvent(new InstrumentManagerBackgroundEvent(backgroundEventType, resultCode, exceptionType, clientLatencyMs, serverLatencyMs, -1, integratorLogToken));
    }

    public static void createAndSendCreditCardEntryActionBackgroundEvent(CreditCardEntryAction creditCardEntryAction, byte[] integratorLogToken) {
        InstrumentManagerAnalyticsEventDispatcher.sendBackgroundEvent(new InstrumentManagerBackgroundEvent(creditCardEntryAction, integratorLogToken));
    }

    public static void createAndSendRetryAttemptBackgroundEvent(int backgroundEventType, int attempts, byte[] integratorLogToken) {
        InstrumentManagerAnalyticsEventDispatcher.sendBackgroundEvent(new InstrumentManagerBackgroundEvent(backgroundEventType, 0, null, -1, -1, attempts, integratorLogToken));
    }

    public static void createAndSendClickEvent(UiNode parentOfClickedElement, int clickedElementId) {
        ArrayList<InstrumentManagerUiElement> clickPath = new ArrayList();
        clickPath.add(new InstrumentManagerUiElement(clickedElementId));
        for (UiNode currentElement = parentOfClickedElement; currentElement != null; currentElement = currentElement.getParentUiNode()) {
            clickPath.add(currentElement.getUiElement());
        }
        InstrumentManagerAnalyticsEventDispatcher.sendClickEvent(new InstrumentManagerClickEvent(clickPath));
    }

    public static void createAndSendImpressionEvent(UiNode nodeShown) {
        createAndSendImpressionEvent(nodeShown, -1);
    }

    public static void createAndSendImpressionEvent(UiNode nodeShown, int transientElementId) {
        UiNode rootUiNode = nodeShown;
        while (rootUiNode.getParentUiNode() != null) {
            rootUiNode = rootUiNode.getParentUiNode();
        }
        InstrumentManagerUiElement impressionTree = cloneElement(rootUiNode.getUiElement());
        addChildrenToTreeWithInjection(impressionTree, rootUiNode.getChildren(), nodeShown.getUiElement().elementId, transientElementId);
        InstrumentManagerAnalyticsEventDispatcher.sendImpressionEvent(new InstrumentManagerImpressionEvent(impressionTree));
    }

    private static void addChildrenToTreeWithInjection(InstrumentManagerUiElement parent, List<UiNode> children, int injectedElementParentId, int injectedElementId) {
        if ((children != null && !children.isEmpty()) || (injectedElementId != -1 && parent.elementId == injectedElementParentId)) {
            ArrayList<InstrumentManagerUiElement> childUiElements = new ArrayList();
            if (injectedElementId != -1 && parent.elementId == injectedElementParentId) {
                childUiElements.add(new InstrumentManagerUiElement(injectedElementId));
            }
            if (children != null) {
                int length = children.size();
                for (int i = 0; i < length; i++) {
                    UiNode node = (UiNode) children.get(i);
                    InstrumentManagerUiElement newElement = cloneElement(node.getUiElement());
                    childUiElements.add(newElement);
                    addChildrenToTreeWithInjection(newElement, node.getChildren(), injectedElementParentId, injectedElementId);
                }
            }
            parent.children = childUiElements;
        }
    }

    private static InstrumentManagerUiElement cloneElement(InstrumentManagerUiElement original) {
        return new InstrumentManagerUiElement(original.elementId, original.integratorLogToken);
    }
}
