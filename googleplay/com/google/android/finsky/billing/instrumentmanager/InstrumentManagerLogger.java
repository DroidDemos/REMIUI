package com.google.android.finsky.billing.instrumentmanager;

import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore;
import com.google.android.finsky.analytics.PlayStore.PlayStoreBackgroundActionEvent;
import com.google.android.finsky.analytics.PlayStore.PlayStoreClickEvent;
import com.google.android.finsky.analytics.PlayStore.PlayStoreImpressionEvent;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.utils.Lists;
import com.google.android.wallet.instrumentmanager.pub.analytics.CreditCardEntryAction;
import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerAnalyticsEventListener;
import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerUiElement;
import com.google.android.wallet.instrumentmanager.pub.analytics.events.InstrumentManagerBackgroundEvent;
import com.google.android.wallet.instrumentmanager.pub.analytics.events.InstrumentManagerClickEvent;
import com.google.android.wallet.instrumentmanager.pub.analytics.events.InstrumentManagerImpressionEvent;
import java.util.ArrayList;

public class InstrumentManagerLogger implements InstrumentManagerAnalyticsEventListener {
    private final FinskyEventLog mLogger;
    private final PlayStoreUiElementNode mNode;

    public InstrumentManagerLogger(PlayStoreUiElementNode node, FinskyEventLog logger) {
        this.mNode = node;
        this.mLogger = logger;
        if (node == null) {
            throw new IllegalArgumentException("node cannot be null");
        }
    }

    public void onBackgroundEvent(InstrumentManagerBackgroundEvent imEvent) {
        this.mLogger.logBackgroundEvent(convertBackgroundEvent(imEvent));
    }

    public void onClickEvent(InstrumentManagerClickEvent imEvent) {
        this.mLogger.logClickEvent(convertClickEvent(imEvent));
    }

    public void onImpressionEvent(InstrumentManagerImpressionEvent imEvent) {
        this.mLogger.logImpressionEvent(convertImpressionEvent(imEvent));
    }

    PlayStoreBackgroundActionEvent convertBackgroundEvent(InstrumentManagerBackgroundEvent imEvent) {
        BackgroundEventBuilder builder = new BackgroundEventBuilder(imEvent.backgroundEventType).setExceptionType(imEvent.exceptionType).setServerLatencyMs(imEvent.serverLatencyMs).setClientLatencyMs(imEvent.clientLatencyMs).setErrorCode(imEvent.resultCode);
        if (imEvent.attempts > 0) {
            builder.setAttempts(imEvent.attempts);
        }
        if (imEvent.integratorLogToken != null && imEvent.integratorLogToken.length > 0) {
            builder.setServerLogsCookie(imEvent.integratorLogToken);
        }
        CreditCardEntryAction imAction = imEvent.creditCardEntryAction;
        if (imAction != null) {
            PlayStore.CreditCardEntryAction playAction = new PlayStore.CreditCardEntryAction();
            playAction.panOcrEnabled = imAction.panOcrEnabled;
            playAction.hasPanOcrEnabled = true;
            playAction.panEntryType = imAction.panEntryType;
            playAction.hasPanEntryType = true;
            playAction.panRecognizedByOcr = imAction.panRecognizedByOcr;
            playAction.hasPanRecognizedByOcr = true;
            playAction.panValidationErrorOccurred = imAction.panValidationErrorOccurred;
            playAction.hasPanValidationErrorOccurred = true;
            playAction.expDateOcrEnabled = imAction.expDateOcrEnabled;
            playAction.hasExpDateOcrEnabled = true;
            playAction.expDateEntryType = imAction.expDateEntryType;
            playAction.hasExpDateEntryType = true;
            playAction.expDateRecognizedByOcr = imAction.expDateRecognizedByOcr;
            playAction.hasExpDateRecognizedByOcr = true;
            playAction.expDateValidationErrorOccurred = imAction.expDateValidationErrorOccurred;
            playAction.hasExpDateValidationErrorOccurred = true;
            playAction.numOcrAttempts = imAction.numOcrAttempts;
            playAction.hasNumOcrAttempts = true;
            playAction.ocrExitReason = imAction.ocrExitReason;
            playAction.hasOcrExitReason = true;
            builder.setCreditCardEntryAction(playAction);
        }
        return builder.build();
    }

    PlayStoreClickEvent convertClickEvent(InstrumentManagerClickEvent imEvent) {
        int length = imEvent.clickPath.size();
        if (length == 0) {
            throw new IllegalArgumentException("Click path must have at least one item");
        }
        ArrayList<PlayStoreUiElement> path = Lists.newArrayList(length + 1);
        for (int i = 0; i < length; i++) {
            path.add(createPlayStoreUiElement((InstrumentManagerUiElement) imEvent.clickPath.get(i)));
        }
        PlayStoreUiElementNode node = this.mNode;
        do {
            path.add(FinskyEventLog.cloneElement(node.getPlayStoreUiElement()));
            node = node.getParentNode();
        } while (node != null);
        PlayStoreClickEvent playEvent = FinskyEventLog.obtainPlayStoreClickEvent();
        playEvent.elementPath = (PlayStoreUiElement[]) path.toArray(new PlayStoreUiElement[path.size()]);
        return playEvent;
    }

    PlayStoreImpressionEvent convertImpressionEvent(InstrumentManagerImpressionEvent imEvent) {
        ArrayList<PlayStoreUiElement> path = Lists.newArrayList();
        for (PlayStoreUiElementNode node = this.mNode; node != null; node = node.getParentNode()) {
            path.add(node.getPlayStoreUiElement());
        }
        PlayStoreUiElement tree = FinskyEventLog.pathToTree(path);
        PlayStoreUiElement element = tree;
        while (element.child != null && element.child.length != 0) {
            element = element.child[0];
        }
        if (element.type != this.mNode.getPlayStoreUiElement().type) {
            throw new IllegalStateException("Unexpected types in tree: " + element.type + " and " + this.mNode.getPlayStoreUiElement().type);
        }
        PlayStoreUiElement subTree = convertUiElement(imEvent.impressionTree);
        element.child = new PlayStoreUiElement[]{subTree};
        PlayStoreImpressionEvent playEvent = FinskyEventLog.obtainPlayStoreImpressionEvent();
        playEvent.tree = tree;
        return playEvent;
    }

    private PlayStoreUiElement convertUiElement(InstrumentManagerUiElement imElement) {
        PlayStoreUiElement playElement = createPlayStoreUiElement(imElement);
        if (!(imElement.children == null || imElement.children.isEmpty())) {
            int numChildren = imElement.children.size();
            playElement.child = new PlayStoreUiElement[numChildren];
            for (int i = 0; i < numChildren; i++) {
                playElement.child[i] = convertUiElement((InstrumentManagerUiElement) imElement.children.get(i));
            }
        }
        return playElement;
    }

    private PlayStoreUiElement createPlayStoreUiElement(InstrumentManagerUiElement imElement) {
        PlayStoreUiElement playElement = FinskyEventLog.obtainPlayStoreUiElement(imElement.elementId);
        if (imElement.integratorLogToken != null && imElement.integratorLogToken.length > 0) {
            playElement.serverLogsCookie = imElement.integratorLogToken;
            playElement.hasServerLogsCookie = true;
        }
        return playElement;
    }
}
