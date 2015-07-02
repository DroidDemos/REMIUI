package com.google.android.vending.remoting.api;

import android.util.Base64;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.protos.VendingProtos.PendingNotificationsProto;
import com.google.android.finsky.protos.VendingProtos.RequestProto;
import com.google.android.finsky.protos.VendingProtos.ResponseProto;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.dfe.utils.NanoProtoHelper;
import com.google.protobuf.nano.MessageNano;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class VendingRequest<T extends MessageNano, U extends MessageNano> extends Request<ResponseProto> {
    protected final VendingApiContext mApiContext;
    private boolean mAvoidBulkCancel;
    private Map<String, String> mExtraHeaders;
    private final Listener<U> mListener;
    private final T mRequest;
    private final Class<T> mRequestClass;
    private final Class<U> mResponseClass;
    private final boolean mUseSecureAuthToken;

    public static <T extends MessageNano, U extends MessageNano> VendingRequest<T, U> make(String url, Class<T> requestClass, T request, Class<U> responseClass, Listener<U> listener, VendingApiContext apiContext, ErrorListener errorListener) {
        return new VendingRequest(url, requestClass, request, responseClass, listener, apiContext, errorListener);
    }

    protected VendingRequest(String url, Class<T> requestClass, T request, Class<U> responseClass, Listener<U> listener, VendingApiContext apiContext, ErrorListener errorListener) {
        super(1, url, errorListener);
        this.mAvoidBulkCancel = false;
        this.mUseSecureAuthToken = url.startsWith("https");
        this.mRequest = request;
        this.mRequestClass = requestClass;
        this.mResponseClass = responseClass;
        this.mListener = listener;
        this.mApiContext = apiContext;
        setRetryPolicy(new VendingRetryPolicy(this.mApiContext, this.mUseSecureAuthToken));
    }

    public void setAvoidBulkCancel() {
        this.mAvoidBulkCancel = true;
    }

    public boolean getAvoidBulkCancel() {
        return this.mAvoidBulkCancel;
    }

    public void addExtraHeader(String header, String value) {
        if (this.mExtraHeaders == null) {
            this.mExtraHeaders = Maps.newHashMap();
        }
        this.mExtraHeaders.put(header, value);
    }

    protected Response<ResponseProto> parseNetworkResponse(NetworkResponse response) {
        try {
            ResponseProto proto = ResponseProto.parseFrom(Utils.readBytes(new GZIPInputStream(new ByteArrayInputStream(response.data), response.data.length)));
            if (proto.response.length != 1) {
                return Response.error(new ServerError());
            }
            if (proto.response[0].responseProperties.result != 0) {
                return Response.error(new ServerError());
            }
            handlePendingNotifications(proto, true);
            return Response.success(proto, null);
        } catch (IOException ioe) {
            FinskyLog.w("Cannot parse Vending ResponseProto: " + ioe, new Object[0]);
            return Response.error(new VolleyError());
        }
    }

    protected boolean handlePendingNotifications(ResponseProto response, boolean allowCancellation) {
        if (response.pendingNotifications == null) {
            return false;
        }
        PendingNotificationsProto pendingNotifications = response.pendingNotifications;
        PendingNotificationsHandler handler = FinskyApp.get().getPendingNotificationsHandler();
        if (handler == null || !handler.handlePendingNotifications(FinskyApp.get().getApplicationContext(), this.mApiContext.getAccount().name, pendingNotifications, allowCancellation)) {
            return false;
        }
        return true;
    }

    public Map<String, String> getHeaders() {
        Map<String, String> headers = this.mApiContext.getHeaders();
        if (this.mExtraHeaders == null || this.mExtraHeaders.isEmpty()) {
            return headers;
        }
        Map<String, String> combinedHeaders = Maps.newHashMap();
        combinedHeaders.putAll(headers);
        combinedHeaders.putAll(this.mExtraHeaders);
        return combinedHeaders;
    }

    public Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> parameters = Maps.newHashMap();
        parameters.put("request", serializeRequestProto(this.mRequest));
        parameters.put("version", String.valueOf(2));
        return parameters;
    }

    protected void deliverResponse(ResponseProto response) {
        this.mListener.onResponse(NanoProtoHelper.getParsedResponseFromWrapper(response.response[0], ResponseProto.Response.class, this.mResponseClass));
    }

    public void deliverError(VolleyError error) {
        if (error instanceof AuthFailureError) {
            this.mApiContext.invalidateAuthToken(this.mUseSecureAuthToken);
        }
        super.deliverError(error);
    }

    String serializeRequestProto(T request) throws AuthFailureError {
        NanoProtoHelper.setRequestInWrapper(new RequestProto.Request(), RequestProto.Request.class, request, this.mRequestClass);
        RequestProto batchRequest = new RequestProto();
        batchRequest.requestProperties = this.mApiContext.getRequestProperties(this.mUseSecureAuthToken);
        batchRequest.request = new RequestProto.Request[]{requestWrapper};
        return Base64.encodeToString(MessageNano.toByteArray(batchRequest), 11);
    }

    public String toString() {
        return super.toString() + " " + this.mRequestClass.getSimpleName();
    }
}
