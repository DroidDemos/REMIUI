package com.google.android.wallet.instrumentmanager.pub.analytics.events;

import com.google.android.wallet.instrumentmanager.pub.analytics.InstrumentManagerUiElement;

public final class InstrumentManagerImpressionEvent {
    public final InstrumentManagerUiElement impressionTree;

    public InstrumentManagerImpressionEvent(InstrumentManagerUiElement impressionTree) {
        this.impressionTree = impressionTree;
    }

    public String toString() {
        StringBuilder treeString = new StringBuilder(32);
        treeString.append('\n');
        printTree(this.impressionTree, treeString, 0);
        return treeString.toString();
    }

    private static void printTree(InstrumentManagerUiElement element, StringBuilder treeString, int indent) {
        int i;
        String inc = "";
        for (i = 0; i < indent; i++) {
            inc = inc + "| ";
        }
        treeString.append(inc);
        treeString.append("|-");
        treeString.append(element.elementId);
        treeString.append(" tokenLen=").append(element.integratorLogToken.length);
        treeString.append('\n');
        if (element.children != null) {
            indent++;
            int length = element.children.size();
            for (i = 0; i < length; i++) {
                printTree((InstrumentManagerUiElement) element.children.get(i), treeString, indent);
            }
        }
    }
}
