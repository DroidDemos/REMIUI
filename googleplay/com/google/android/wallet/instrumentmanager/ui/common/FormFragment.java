package com.google.android.wallet.instrumentmanager.ui.common;

import android.os.Bundle;
import com.google.android.wallet.instrumentmanager.analytics.UiNode;
import com.google.android.wallet.instrumentmanager.common.util.ParcelableProto;
import com.google.protobuf.nano.MessageNano;
import java.util.ArrayList;

public abstract class FormFragment<T extends MessageNano> extends BaseInstrumentManagerFragment implements UiNode, Form, FormEventListener {
    protected int mCurrentFormIndex;
    protected T mFormProto;
    protected ArrayList<T> mFormProtos;
    private boolean mUiEnabled;

    protected abstract void doEnableUi();

    public FormFragment() {
        this.mUiEnabled = true;
        this.mCurrentFormIndex = -1;
    }

    public static <T extends MessageNano, F extends FormFragment<T>> Bundle createArgsForFormFragment(int resId, T formProto, Class<F> cls) {
        Bundle args = BaseInstrumentManagerFragment.createArgs(resId);
        args.putParcelable("formProto", ParcelableProto.forProto(formProto));
        return args;
    }

    public static <T extends MessageNano, F extends FormFragment<T>> Bundle createArgsForFormFragment(int resId, T[] formProtos, int initialFormIndex, Class<F> cls) {
        Bundle args = BaseInstrumentManagerFragment.createArgs(resId);
        args.putParcelableArrayList("formProtos", ParcelableProto.forProtoArray(formProtos));
        args.putInt("initialFormProtoIndex", initialFormIndex);
        return args;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mUiEnabled = savedInstanceState.getBoolean("uiEnabled", true);
        }
        Bundle args = getArguments();
        if (args.containsKey("formProto")) {
            this.mFormProto = ParcelableProto.getProtoFromBundle(args, "formProto");
        } else if (args.containsKey("formProtos")) {
            this.mFormProtos = ParcelableProto.getProtoArrayListFromBundle(args, "formProtos");
            if (savedInstanceState != null) {
                this.mCurrentFormIndex = savedInstanceState.getInt("currentFormIndex", -1);
            } else {
                this.mCurrentFormIndex = args.getInt("initialFormProtoIndex");
            }
        } else {
            throw new IllegalArgumentException("FormFragment cannot be created without required form proto");
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("uiEnabled", this.mUiEnabled);
        if (this.mFormProtos != null && this.mCurrentFormIndex >= 0) {
            outState.putInt("currentFormIndex", this.mCurrentFormIndex);
        }
    }

    public UiNode getParentUiNode() {
        return (UiNode) getParentFragment();
    }

    public void notifyFormEvent(int eventType, Bundle eventDetails) {
        ((FormEventListener) getParentFragment()).notifyFormEvent(eventType, eventDetails);
    }

    public boolean handleErrorMessageDismissed(String formId, int errorType) {
        return false;
    }

    public void onResume() {
        super.onResume();
        notifyFormEvent(4, Bundle.EMPTY);
    }

    public final boolean isUiEnabled() {
        return this.mUiEnabled;
    }

    public final void enableUi(boolean enableUi) {
        this.mUiEnabled = enableUi;
        doEnableUi();
    }

    public boolean shouldShowButtonBarExpandButton() {
        return false;
    }

    public void onButtonBarExpandButtonClicked() {
    }

    public String getButtonBarExpandButtonText() {
        return null;
    }

    public boolean shouldAutoSubmit() {
        return false;
    }
}
