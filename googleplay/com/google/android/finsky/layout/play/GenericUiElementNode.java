package com.google.android.finsky.layout.play;

import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElementInfo;

public class GenericUiElementNode implements PlayStoreUiElementNode {
    private PlayStoreUiElementNode mParentNode;
    private PlayStoreUiElement mUiElementProto;

    public GenericUiElementNode(int elementType, byte[] elementServerCookie, PlayStoreUiElementInfo elementInfo, PlayStoreUiElementNode parent) {
        this.mUiElementProto = null;
        this.mParentNode = null;
        reset(elementType, elementServerCookie, elementInfo, parent);
    }

    public void reset(int elementType, byte[] elementServerCookie, PlayStoreUiElementInfo elementInfo, PlayStoreUiElementNode parent) {
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(elementType);
        FinskyEventLog.setServerLogCookie(this.mUiElementProto, elementServerCookie);
        if (elementInfo != null) {
            this.mUiElementProto.clientLogsCookie = elementInfo;
        }
        this.mParentNode = parent;
    }

    public void setServerLogsCookie(byte[] elementServerCookie) {
        FinskyEventLog.setServerLogCookie(this.mUiElementProto, elementServerCookie);
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }

    public PlayStoreUiElementNode getParentNode() {
        return this.mParentNode;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyEventLog.childImpression(this, childNode);
    }
}
