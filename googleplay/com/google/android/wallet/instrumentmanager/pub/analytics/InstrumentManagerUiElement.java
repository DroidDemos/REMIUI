package com.google.android.wallet.instrumentmanager.pub.analytics;

import com.google.protobuf.nano.WireFormatNano;
import java.util.List;

public final class InstrumentManagerUiElement {
    public List<InstrumentManagerUiElement> children;
    public final int elementId;
    public final byte[] integratorLogToken;

    public InstrumentManagerUiElement(int elementId) {
        this(elementId, WireFormatNano.EMPTY_BYTES);
    }

    public InstrumentManagerUiElement(int elementId, byte[] integratorLogToken) {
        this.elementId = elementId;
        this.integratorLogToken = integratorLogToken;
    }
}
