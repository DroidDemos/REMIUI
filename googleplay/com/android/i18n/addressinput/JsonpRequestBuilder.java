package com.android.i18n.addressinput;

import android.os.Process;
import com.google.android.finsky.utils.Utils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

class JsonpRequestBuilder {

    interface AsyncCallback<T> {
        void onFailure(Throwable th);

        void onSuccess(T t);
    }

    private static class AsyncHttp extends Thread {
        private static final HttpClient CLIENT;
        private AsyncCallback<JsoMap> mCallback;
        private HttpUriRequest mRequest;

        static {
            CLIENT = new DefaultHttpClient();
        }

        protected AsyncHttp(HttpUriRequest request, AsyncCallback<JsoMap> callback) {
            this.mRequest = request;
            this.mCallback = callback;
        }

        public void run() {
            Process.setThreadPriority(10);
            try {
                String response;
                synchronized (CLIENT) {
                    response = (String) CLIENT.execute(this.mRequest, new BasicResponseHandler());
                }
                this.mCallback.onSuccess(JsoMap.buildJsoMap(response));
            } catch (Exception e) {
                this.mCallback.onFailure(e);
            }
        }
    }

    JsonpRequestBuilder() {
    }

    void setTimeout(int timeout) {
        HttpParams params = AsyncHttp.CLIENT.getParams();
        HttpConnectionParams.setConnectionTimeout(params, timeout);
        HttpConnectionParams.setSoTimeout(params, timeout);
    }

    void requestObject(String url, AsyncCallback<JsoMap> callback) {
        new AsyncHttp(new HttpGet(encodeUrl(url)), callback).start();
    }

    private static String encodeUrl(String url) {
        int length = url.length();
        StringBuilder tmp = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            int j = i;
            char c = '\u0000';
            while (j < length) {
                c = url.charAt(j);
                if (c == ':' || c == '/') {
                    break;
                }
                j++;
            }
            if (j == length) {
                tmp.append(Utils.urlEncode(url.substring(i)));
                break;
            }
            if (j > i) {
                tmp.append(Utils.urlEncode(url.substring(i, j)));
                tmp.append(c);
                i = j;
            } else {
                tmp.append(c);
            }
            i++;
        }
        return tmp.toString();
    }
}
