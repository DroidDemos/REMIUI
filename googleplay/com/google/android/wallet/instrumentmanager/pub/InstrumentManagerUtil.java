package com.google.android.wallet.instrumentmanager.pub;

import android.content.Context;
import com.google.android.wallet.instrumentmanager.common.util.PaymentUtils;
import com.google.commerce.payments.orchestration.proto.api.integratorbuyflow.DataTokens.ClientToken;
import com.google.protobuf.nano.MessageNano;

public final class InstrumentManagerUtil {
    public static byte[] createClientToken(Context context) {
        ClientToken clientToken = new ClientToken();
        clientToken.requestContext = PaymentUtils.createRequestContext(context, null);
        return MessageNano.toByteArray(clientToken);
    }
}
