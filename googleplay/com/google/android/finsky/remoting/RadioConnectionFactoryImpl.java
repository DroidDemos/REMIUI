package com.google.android.finsky.remoting;

import android.net.ConnectivityManager;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.play.utils.config.GservicesValue;
import com.google.android.wallet.instrumentmanager.R;

public class RadioConnectionFactoryImpl implements RadioConnectionFactory {
    private static final RadioConnection NULL_RADIO_CONNECTION;
    private static final GservicesValue<Boolean> USE_RADIO;
    private final ConnectivityManager mConnMgr;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$remoting$RadioConnectionFactoryImpl$ConnectionType;

        static {
            $SwitchMap$com$google$android$finsky$remoting$RadioConnectionFactoryImpl$ConnectionType = new int[ConnectionType.values().length];
            try {
                $SwitchMap$com$google$android$finsky$remoting$RadioConnectionFactoryImpl$ConnectionType[ConnectionType.HIPRI.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$remoting$RadioConnectionFactoryImpl$ConnectionType[ConnectionType.DUN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$finsky$remoting$RadioConnectionFactoryImpl$ConnectionType[ConnectionType.SUPL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$finsky$remoting$RadioConnectionFactoryImpl$ConnectionType[ConnectionType.DEFAULT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private enum ConnectionType {
        HIPRI,
        SUPL,
        DUN,
        DEFAULT;

        public static ConnectionType parse(String value) {
            for (ConnectionType type : values()) {
                if (type.name().equals(value)) {
                    return type;
                }
            }
            return DEFAULT;
        }
    }

    static {
        NULL_RADIO_CONNECTION = new RadioConnection() {
            public void start() {
            }

            public void stop() {
            }

            public void ensureRouteToHost(String url) {
            }
        };
        USE_RADIO = GservicesValue.value("vending.use_radio", Boolean.valueOf(true));
    }

    public RadioConnectionFactoryImpl(ConnectivityManager connMgr) {
        this.mConnMgr = connMgr;
    }

    private RadioConnection createNewConnectionByType(ConnectionType connectionType) {
        switch (AnonymousClass2.$SwitchMap$com$google$android$finsky$remoting$RadioConnectionFactoryImpl$ConnectionType[connectionType.ordinal()]) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return new RadioConnectionWithFallback(new RadioConnectionImpl(this.mConnMgr, 5, PhoneFeature.ENABLE_HIPRI), new RadioConnectionImpl(this.mConnMgr, 2, PhoneFeature.ENABLE_MMS));
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return new RadioConnectionImpl(this.mConnMgr, 4, PhoneFeature.ENABLE_DUN);
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return new RadioConnectionImpl(this.mConnMgr, 3, PhoneFeature.ENABLE_SUPL);
            default:
                return new RadioConnectionImpl(this.mConnMgr, 2, PhoneFeature.ENABLE_MMS);
        }
    }

    public RadioConnection createNewConnection() {
        if (!((Boolean) USE_RADIO.get()).booleanValue()) {
            return NULL_RADIO_CONNECTION;
        }
        ConnectionType connectionType = ConnectionType.parse((String) G.vendingDcbConnectionType.get());
        FinskyLog.d("Creating new RadioConnection of type " + connectionType.name(), new Object[0]);
        return new StateHandleRadioConnection(createNewConnectionByType(connectionType));
    }
}
